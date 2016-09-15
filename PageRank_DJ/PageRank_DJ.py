__author__ = 'DJ'

################################################################################################

# LIBRARIES:

from itertools import islice
import collections
from collections import Counter
import math

################################################################################################

# CONSTANTS:

##########################################
#Input Files

#inlinks_file = open("inlinks_demo.txt")
inlinks_file = open("wt2g_inlinks.txt")

##########################################
# Lines of a File
lines = inlinks_file.read().split("\n")
##########################################
#Pages array
pages = []
#########################################
#ListOfNodes for Inlinks
listOfNodes = dict()
#########################################
# List of all Outlinking nodes
listOfInlinkNodes = []
#########################################
# initial PR
pageRanks = dict()

########################################################################################

# P is the set of all pages; |P| = N check
P = []
# S is the set of sink nodes, i.e., pages that have no out links check
S = []
# M(p) is the set (without duplicates) of pages that link to page p
M = []
# L(q) is the number of out-links (without duplicates) from page q check
L= []
# Z is the set of pages with no IN-links
Z=[]
# d is the PageRank damping/teleportation factor; use d = 0.85 as a fairly typical value
d = 0.85

################################################################################################

# FUNCTIONS :

# get all the pages from the inlinks dump file
def getPages(lines):
    for i in range(0,len(lines)):
            pages.append(str(lines[i]).split(" ")[0])
    return pages

# get the number of outlinks for all pages that are inlinks
def getOutLinks(M,lines):
    listOfAllInlinks = []
    for p,n in M.iteritems():
        for i in range(0,len(n)):
            listOfAllInlinks.append(n[i])
    counts = Counter(listOfAllInlinks)
    return counts

# gets all the Zero Outlink nodes
def getSinkNodes(M,L):
    Sink=[]
    Sink = list(set(M.iterkeys()) - set(L.iterkeys()))
    return Sink

# get the list of inlink pages for all pages
def getListOfInlinks(lines):
    for i in range(0,len(lines)):
        page = str(lines[i]).split(" ")[0]
        currentInlinks = str(lines[i]).split(" ")[1:]
        innerNodes = []
        for j in range (0,len(currentInlinks)):
            if not currentInlinks[j] in innerNodes:
                if not str(currentInlinks[j]) == "":
                    innerNodes.append(currentInlinks[j])
        if(len(innerNodes) > 0):
            listOfNodes[page] = innerNodes
        else:
            listOfNodes[page] = []
    return listOfNodes

# get the list of zero inlink pages
def getZeroInlinks(M):
    listOfZeroInlinks = []
    for p,n in M.iteritems():
        if M[p] == []:
            listOfZeroInlinks.append(p)
    return listOfZeroInlinks

# initial page rank of all pages (1/N)
def initializePageRank(P):
    for p in P:
        pageRanks[p] = float(1)/len(P) #initial PR value
    return pageRanks

# calculate total page rank of sink nodes
def calSinkPR(S):
    totalSinkPR = 0
    if(len(S)>0):
        for p in S:
            totalSinkPR += PR[p] #total sinkPR
        return totalSinkPR
    else:
        return totalSinkPR

# calculate new page rank of all pages
def calNewPageRank(PR,S):
    newPR = dict()
    for p,inlinks in M.iteritems():
        newPR[p] = float(1-d)/len(PR) #teleportation
        newPR[p] += float(d) * (TOTAL_SINK_PR/len(PR)) #spread sinkPR evenly
        for node in inlinks:
            newPR[p] += d * PR[node]/L[node]
    return newPR

# calculate Shannon Entropy of current PR
def calShannonEntropy(PR):
    entropy = 0
    for pr in PR.itervalues():
        entropy += float(pr) * math.log(pr,2)
    return entropy

# Slicing Function to get top 50 pages
def take(n, iterable):
    "Return first n items of the iterable as a list"
    return list(islice(iterable, n))

################################################################################
#Set of pages
P = getPages(lines)
#print len(P)

#ListOfInLinks for each page
M = getListOfInlinks(lines)
#print len(M)

#OutLinks Count
L = getOutLinks(M,lines)
#print len(L)

#Sink Nodes
S = getSinkNodes(M,L)
#print len(S)

#Zero Inlinks
Z = getZeroInlinks(M)

#InitialPageRank of all pages
PR = dict()

# PR = initializePageRank(P)
# # TOTAL SINK PR
# TOTAL_SINK_PR = calSinkPR(S)
# # Page Rank after 1 Iteration
# PR = calNewPageRank(PR,S)

#print PR

##############################
# CONVERGENCE

#Perplexity Difference
ppl_diff = 2

#Perplexity values
Perplexity = []

initial_flag = 0
while (ppl_diff > 1):
    if initial_flag == 0:
        PR = initializePageRank(P)
        #Total PR of sink nodes
        TOTAL_SINK_PR = calSinkPR(S)
        #print TOTAL_SINK_PR
        #  Shannon Entropy
        SE = calShannonEntropy(PR) * -1
        #print SE
        # Perplexity
        PPL = math.pow(2,SE)
        #print "Perplexity : ",PPL
        Perplexity.append(PPL)
        initial_flag = 1
    else:
        PR = calNewPageRank(PR,S)
        TOTAL_SINK_PR = calSinkPR(S)
        #  Shannon Entropy
        SE = calShannonEntropy(PR) * -1
        #print SE
        # Perplexity
        PPL = math.pow(2,SE)
        #print "Perplexity : ",PPL
        Perplexity.append(PPL)
    if len(Perplexity) >= 4:
        ppl_diff = 0
        ppl_diff = abs(Perplexity[len(Perplexity)-1] - Perplexity[len(Perplexity)-2])
        if ppl_diff < 1:
            ppl_diff = abs(Perplexity[len(Perplexity)-2] - Perplexity[len(Perplexity)-3])
            if ppl_diff < 1:
                ppl_diff = abs(Perplexity[len(Perplexity)-3] - Perplexity[len(Perplexity)-4])
                if ppl_diff < 1:
                    ppl_diff = 0

the_filename = "PR_After_Convergence.txt"
with open(the_filename, 'w') as f:
    for p,pr in PR.iteritems():
        f.write(str(p) + ' : ' + str(pr) + '\n')

the_filename = "Perplexities_Until_Convergence.txt"
with open(the_filename, 'w') as f:
    for p in range(0,len(Perplexity)):
        f.write("Perplexity " + str(p) +' : ' + str(Perplexity[p]) + '\n')

# the_filename = "PR_After_1_Iteration.txt"
# with open(the_filename, 'w') as f:
#     for p,pr in PR.iteritems():
#         f.write(str(p) + ' : ' + str(pr) + '\n')

the_filename = "PR_Inlinks.txt"
with open(the_filename, 'w') as f:
    for p,pr in M.iteritems():
        f.write(str(p) + ' : ' + str(pr) + '\n')

the_filename = "PR_Sinks.txt"
with open(the_filename, 'w') as f:
    for i in range(0,len(S)):
        f.write(str(S[i])  + '\n')

the_filename = "PR_Zero_Inlinks.txt"
with open(the_filename, 'w') as f:
    for i in range(0,len(Z)):
        f.write(str(Z[i])  + '\n')

the_filename = "PR_Outlinks.txt"
with open(the_filename, 'w') as f:
    for p,n in L.iteritems():
        f.write(str(p)  + ":" + str(n) + '\n')

# #Page Rank after 10 Iterations
# for i in range(1,10):
#       PR = calNewPageRank(PR,S)
# the_filename = "PR_After_10_Iterations.txt"
# with open(the_filename, 'w') as f:
#      for p,pr in PR.iteritems():
#        f.write(str(p) + ' : ' + str(pr) + '\n')
#
# # Page Rank after 100 Iterations
# for i in range(1,100):
#      PR = calNewPageRank(PR,S)
# the_filename = "PR_After_100_Iterations.txt"
# with open(the_filename, 'w') as f:
#     for p,pr in PR.iteritems():
#         f.write(str(p) + ' : ' + str(pr) + '\n')

# Sort PR after convergence based on values
sorted_PR = Counter(PR)

# top 50 pages according to PR
top_50_pages = sorted_PR.most_common(50)

the_filename = "Top_50_pages_after_convergence.txt"
with open(the_filename, 'w') as f:
    for p,n in sorted_PR.most_common(50):
        f.write(str(p)  + " : " + str(n) + '\n')

#top 50 pages according to inlink count
pages_with_inlink_count = dict()
for p,n in M.iteritems():
    pages_with_inlink_count[p] = len(n)

sorted_pages_with_inlink_count = Counter(pages_with_inlink_count)

# top 50 pages according to inlink count
top_50_pages_inlink_count = sorted_pages_with_inlink_count.most_common(50)

the_filename = "Top_50_pages_based_on_inlink_count.txt"
with open(the_filename, 'w') as f:
    for p,n in sorted_pages_with_inlink_count.most_common(50):
        f.write(str(p) + " : " + str(n) + '\n')

# proportion of pages with zero inlinks
prop_pages_with_zero_inlinks = float(len(Z))/len(P) * 100
print prop_pages_with_zero_inlinks

#proportion of pages with zero outlinks
prop_pages_with_zero_outlinks = float(len(S))/len(P) * 100
print prop_pages_with_zero_outlinks

# proportion of pages with PR < initial PR
initial_PR = dict()
initial_PR = initializePageRank(P)
less_than_initial_PR = []
for p,pr in PR.iteritems():
    if PR[p] < initial_PR[p]:
        less_than_initial_PR.append(p)

# proportion of pages with PR < initial PR
prop_pages_with_PR_less_than_initial_PR = float(len(less_than_initial_PR))/len(P) * 100
print prop_pages_with_PR_less_than_initial_PR

# write to file proportions x3
the_filename = "Proportions_of_pages_x3.txt"
with open(the_filename, 'w') as f:
    f.write("Proportion of pages with zero inlinks"  + ":" + str(prop_pages_with_zero_inlinks) + '\n')
    f.write("Proportion of pages with zero outlinks"  + ":" + str(prop_pages_with_zero_outlinks) + '\n')
    f.write("Proportion of pages with PR < initial PR"  + ":" + str(prop_pages_with_PR_less_than_initial_PR) + '\n')

