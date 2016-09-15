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

    relevance_count = open("Relevance.txt")
    qids = relevance_count.read().split("\n")

    Rel_docs = dict()
    for line in qids:
        key = int(line.split(" ")[0])
        if key not in Rel_docs.keys():
            Rel_docs[key] = []
            doc = str(line.split(" ")[2])
            Rel_docs[key].append(doc)
        else:
            doc = str(line.split(" ")[2])
            Rel_docs[key].append(doc)
    Rel_docs[1] = Rel_docs.pop(12)
    Rel_docs[2] = Rel_docs.pop(13)
    Rel_docs[3] = Rel_docs.pop(19)
    queries = open(q)
    query_lines = queries.read().split("\n")
    Q = dict()
    for i in range(1,len(query_lines)):
        Q[i] = query_lines[i-1].split(" ")
        Q[i].remove('')
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
    #constant k1 determines how tf changes when fi increases
    k1 = 1.2
    #constant k2 determines how qtf changes when fi increases
    k2 = 100
    # normalizes k
    b = 0.75
    result = []
    result_metrics = []
    avg_precision = []
    doc_lengths = 0
    p20 = []
    for q_id, query_tokens in Q.iteritems():
        sum_precision = 0
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
                result.append(str(q_id) + " Q0 " + str(score[0]) + " " + str(score[1]) + " System_DJ")
        if q_id < 4:
            rank = 1
            traversal_count = 0
            dcg = 0
            idcg = 0
            id_limit = len(Rel_docs[q_id])
            rel1 = 0
            reli = 0
            logi = 0
            relbylog = 0
            relidbylog = 0
            result_metrics.append("Rank,Document ID, Document Score,Relevance Level,Precision,Recall,NDCG")
            for score in Sorted_list[:100]:
                doc_id = "CACM-" + str(score[0])
                if doc_id in Rel_docs[q_id]:
                    traversal_count += 1
                    if rank == 1:
                        rel1 = 1
                        ndcg = 1.0
                        sum_precision += float(traversal_count)/rank
                        result_metrics.append(str(rank) + "," + str(score[0]) + "," + str(score[1]) + "," + "1" + "," + str(float(traversal_count)/rank) + "," + str(float(traversal_count)/len(Rel_docs[q_id])) + "," + str(ndcg))
                    else:
                        reli = 1
                        logi = math.log(rank,2)
                        relbylog += float(reli) / logi
                        relidbylog += float(1)/ logi
                        dcg = float(rel1) + relbylog
                        if rank <= id_limit:
                            idcg = float(1) + relidbylog
                        ndcg = float(dcg)/float(idcg)
                        sum_precision += float(traversal_count)/rank
                        result_metrics.append(str(rank) + "," + str(score[0]) + "," + str(score[1]) + "," + "1" + "," + str(float(traversal_count)/rank) + "," + str(float(traversal_count)/len(Rel_docs[q_id])) + "," + str(ndcg))
                else:
                    if rank == 1:
                        rel1 = 0
                        ndcg = 0.0
                        result_metrics.append(str(rank) + "," + str(score[0]) + "," + str(score[1]) + "," + "0" + "," + str(float(traversal_count)/rank) + "," + str(float(traversal_count)/len(Rel_docs[q_id])) + "," + str(ndcg))
                    else:
                        reli = 0
                        logi = math.log(rank,2)
                        relbylog += 0
                        dcg = float(rel1) + relbylog
                        relidbylog += float(1)/ logi
                        if rank <= id_limit:
                            idcg = float(1) + relidbylog
                        ndcg = float(dcg)/float(idcg)
                        result_metrics.append(str(rank) + "," + str(score[0]) + "," + str(score[1]) + "," + "0" + "," + str(float(traversal_count)/rank) + "," + str(float(traversal_count)/len(Rel_docs[q_id])) + "," + str(ndcg))
                if rank == 20:
                    p20.append(float(traversal_count)/rank)
                rank += 1
            the_filename = "query_" + str(q_id) + "_metrics.csv"
            with open(the_filename, 'w') as f:
                for line in result_metrics:
                    f.write(line + '\n')
            avg_precision.append(float(sum_precision)/len(Rel_docs[q_id]))
        result_metrics = []

    map = float(sum(avg_precision))/3

    the_filename = "MAP_P@20.txt"
    with open(the_filename, 'w') as f:
        f.write("1 P@20 = " + str(p20[0])+ '\n')
        f.write("2 P@20 = " + str(p20[1])+ '\n')
        f.write("3 P@20 = " + str(p20[2])+ '\n')
        f.write("MAP = " + str(map)+ '\n')

    the_filename = "result.eval"
    with open(the_filename, 'w') as f:
         for line in result:
            f.write(line + '\n')
    return the_filename

BM25_Ranking(index,queries,100)