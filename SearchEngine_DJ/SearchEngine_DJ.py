__author__ = 'DJ'

################################################################################################

# LIBRARIES:

import collections
from collections import Counter
from collections import defaultdict
from numbers import Number
import math
import json

################################################################################################

# CONSTANTS:

##########################################
#Input Files

tokens = "tccorpus.txt"
queries = "queries.txt"

#Output Files
index = "index.out"
result = "result.eval"

def indexer(tokens):

########################################################################################
#Parameters needed for BM25 Ranking

#term frequency
# word -> (docid,tf)

########################################################################################
#Docwise tokens
    tokens_file = open(tokens)
    lines = tokens_file.read().split("\n")
    docwise_tokens = dict()
    list_of_tokens = []
    tokens = []

    for line in lines:
        if "#" in line:
            doc_id = line.split(" ")[1]
            docwise_tokens[doc_id] = []
        else:
            tokens = line.split(' ')
            tokens = [x for x in tokens if not (x.isdigit()) and x != '']
            docwise_tokens[doc_id].extend(tokens)

    for doc_id, vals in docwise_tokens.iteritems() :
        docwise_tokens[doc_id] = Counter(vals).most_common()

    index = collections.defaultdict(list)
    for d, vals in docwise_tokens.items():
        doc_id = d
        for token,cnt in vals:
                index[token].append([doc_id,cnt])
    
    the_filename = "index.out"
    with open(the_filename, 'w') as fp:
        json.dump(index, fp)
    return the_filename

indexer(tokens)

def BM25_Ranking(ind,q,n):
    queries = open(q)
    query_lines = queries.read().split("\n")
    Q = dict()
    for i in range(1,len(query_lines)):
        Q[i] = query_lines[i-1].split(" ")
        Q[i].remove('')
    print Q
    I = dict()
    
    with open(ind) as data_file:
        I = json.load(data_file)

    C= dict()
    C = defaultdict(lambda: 0, C)
    for df in I.itervalues():
        for d, f in df:
                C[d] += f

    # total number of documents
    N = len(C.keys())

    #avg doc length
    avgdl = float(sum(C.itervalues())) / N
    print avgdl
    #constant k1 determines how tf changes when fi increases
    k1 = 1.2
    #constant k2 determines how qtf changes when fi increases
    k2 = 100
    # normalizes k
    b = 0.75
    result = []
    for q_id, query_tokens in Q.iteritems():
        Scores = dict()
        Scores = defaultdict(lambda: 0.0, Scores)
        #query frequency
        temp = Counter(query_tokens)
        for token,qf in temp.iteritems():
                #number of documents in which the query occurs
                ni = len(I[token])
                for doc,f in I[token]:
                    #document length
                    dl = C[doc]
                    # k1* ((1-b) + b* dl/avdl)
                    K = k1 * ((1-b) + ((b * dl)/avgdl))
                    Scores[doc] += math.log(((N - ni + 0.5) / (ni + 0.5) ))  * (((k1 + 1) * f)/ (K + f)) * (((k2 + 1) * qf) / (k2 + qf))
        Sorted_list = Scores.iteritems()
        Sorted_list = sorted(Sorted_list,key=lambda l:l[1], reverse=True)

        for score in Sorted_list:
            result.append(str(q_id) + " Q0 " + str(score[0]) + " : " + str(score[1]) + " System_DJ")

    the_filename = "result.eval"
    with open(the_filename, 'w') as f:
         for line in result:
            f.write(line + '\n')
    return the_filename

BM25_Ranking(index,queries,100)