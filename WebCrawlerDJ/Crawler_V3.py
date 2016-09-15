__author__ = 'DJ'

from bs4 import BeautifulSoup
from urllib2 import HTTPError
import requests
import numpy

crawledList = []
depthCount = 1
linksInDepth = []
pagesToVisit = None


def crawler_DJ(url, searchWord):
    uniquelist = []
    pagesToVisit = [url]
    numberVisited = 0
    foundPrefix = False
    depthCount = 1
    while len(uniquelist) < 1000 or depthCount <= 5 :
        try:
            foundWord = False
            pagesToVisit[0] = pagesToVisit[0].encode('utf-8')
            print(numberVisited, "Visting:", pagesToVisit[0])

            r = requests.get(pagesToVisit[0])
            data = r.text
            soup = BeautifulSoup(data.encode('ascii','ignore'),'html.parser')
            numberVisited = numberVisited + 1
            prev = len(pagesToVisit)
            fieldnames = ['links']
            for link in soup.find_all('a'):
                temp = str(link.get('href'))
                if temp not in uniquelist:
                    if ":" not in temp[6:]:
                        if "wiki" in temp[:6]:
                                if "wikipedia" in temp[:6]:
                                    pagesToVisit.append("http:" + temp)
                                    #uniquelist.append("http:" + temp)
                                elif "http://en.wiki" in temp[:6]:
                                    pagesToVisit.append(temp)
                                    #uniquelist.append(temp)
                                elif "/wiki" in temp[:6]:
                                    pagesToVisit.append("http://en.wikipedia.org" + temp)
                                    #uniquelist.append("http://en.wikipedia.org" + temp)
                                else:
                                    continue
            thefile = open('links'+str(depthCount)+'.txt', 'a+')
            for item in numpy.unique(uniquelist):
                thefile.write("%s\n" % item)

            if data.find(searchWord) >= 1 :
                foundWord = True
                crawledList.insert(numberVisited, pagesToVisit[0])

            linksInDepth.append(len(pagesToVisit) - prev)

            if numberVisited > sum(linksInDepth[:depthCount]):
                depthCount = depthCount + 1
            pagesToVisit = pagesToVisit[1:]
            print("Success")
            print(len(crawledList))
        except IndexError:
            #print("Link need not be crawled!")
            numberVisited = numberVisited + 1
            pagesToVisit = pagesToVisit[1:]
        except HTTPError, err:
            if err.code == 404:
                print("Link could not be loaded",err.msg)
                numberVisited = numberVisited + 1
                pagesToVisit = pagesToVisit[1:]
            elif err.code == 403:
                numberVisited = numberVisited + 1
                pagesToVisit = pagesToVisit[1:]
                print "Access denied!"
            else:
                numberVisited = numberVisited + 1
                pagesToVisit = pagesToVisit[1:]
                print "Something happened! Error code", err.code
    print(len(uniquelist))
    thefile = open('crawled.txt', 'a+')
    for item in crawledList:
        thefile.write("%s\n" % item)

crawler_DJ("http://en.wikipedia.org/wiki/Hugh_of_Saint-Cher","concordance")