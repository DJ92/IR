__author__ = 'DJ'

import fnmatch
import os
import string
import math
from collections import Counter

train_dir = "textcat/train/"
dev_dir = "textcat/dev/"
test_dir = "textcat/test/"
model_file = "model.csv"
pos_file_list = []
neg_file_list = []
chars_to_remove = ['.', '!', '?','[',']',';',':','_','!','$',',','-',"'","/",'%','>','<','+','*','=','`','#','&','(',')','\\','"']

def nbtrain(training_dir):
    pos_files = []
    neg_files = []
    pos_reviews_dev_train = []
    neg_reviews_dev_train = []
    for root, dirnames, filenames in os.walk(training_dir):
        for filename in fnmatch.filter(filenames, '*.txt'):
            dirname = root.split(os.path.sep)[-1]
            if dirname == "pos":
                pos_files.append(os.path.join(root, filename))
            if dirname == "neg":
                neg_files.append(os.path.join(root, filename))
    for file in pos_files:
        f = open(file)
        lines = f.read().split("\n")
        for line in lines:
            line = line.translate(None,''.join(chars_to_remove))
            line = str(line).lstrip(" ")
            line = str(line).rstrip(" ")
            #line = str(line).lstrip("0123456789")
            pos_reviews_dev_train.extend(line.split(" "))
    for file in neg_files:
        f = open(file)
        lines = f.read().split("\n")
        for line in lines:
            line = line.translate(None,''.join(chars_to_remove))
            line = str(line).lstrip(" ")
            line = str(line).rstrip(" ")
            #line = str(line).lstrip("0123456789")
            neg_reviews_dev_train.extend(line.split(" "))
    total_files = pos_files + neg_files
    if '' in pos_reviews_dev_train:
        pos_reviews_dev_train.remove('')
    pos_reviews_dev_train = [x for x in pos_reviews_dev_train if not (x.isdigit()) and x != '']
    pos_reviews_dev_train = [filter(lambda s: s in string.printable, x) for x in pos_reviews_dev_train]
    if '' in neg_reviews_dev_train:
        neg_reviews_dev_train.remove('')
    neg_reviews_dev_train = [x for x in neg_reviews_dev_train if not (x.isdigit()) and x != '']
    neg_reviews_dev_train = [filter(lambda s: s in string.printable, x) for x in neg_reviews_dev_train]
    total_reviews = pos_reviews_dev_train + neg_reviews_dev_train
    total_reviews_dict = Counter(total_reviews)
    lst_less_than_5 = []
    for t,c in total_reviews_dict.iteritems():
        if c < 5:
            lst_less_than_5.append(t)
    total_reviews_dict = Counter({k: c for k, c in total_reviews_dict.items() if c >= 5})

    pos_reviews = Counter(pos_reviews_dev_train)
    if '' in pos_reviews.keys():
        pos_reviews.pop('', None)
    for t in lst_less_than_5:
        if t in pos_reviews.keys():
            pos_reviews.pop(t,None)

    neg_reviews = Counter(neg_reviews_dev_train)
    if '' in neg_reviews.keys():
        neg_reviews.pop('', None)
    for t in lst_less_than_5:
        if t in neg_reviews.keys():
            neg_reviews.pop(t,None)

    pos_reviews_prob = dict()
    neg_reviews_prob = dict()

    pos_class_prob = len(pos_files) / len(pos_files) + len(neg_files)
    neg_class_prob = len(neg_files) / len(pos_files) + len(neg_files)
    log_pos_class_prob = math.log(pos_class_prob,2)
    log_neg_class_prob = math.log(neg_class_prob,2)

    pos_word_prob = dict()
    neg_word_prob = dict()
    for t,c in pos_reviews.iteritems():
        prob = (float(c) + 1)/(sum(pos_reviews.values()) + len(total_reviews_dict.keys()))
        pos_word_prob[t] = prob
        pos_reviews_prob[t] = math.log(prob,2)
    #print len(pos_reviews_prob.keys())
    for t,c in neg_reviews.iteritems():
        prob = (float(c) + 1)/(sum(neg_reviews.values()) + len(total_reviews_dict.keys()))
        neg_word_prob[t] = prob
        neg_reviews_prob[t] = math.log(prob,2)
    #print len(neg_reviews_prob.keys())
    ratio_pos_vs_neg = dict()
    ratio_neg_vs_pos = dict()

    for t,lp in pos_word_prob.iteritems():
        if t in neg_word_prob.keys():
            ratio_pos_vs_neg[t] = math.log((pos_word_prob[t] / neg_word_prob[t]),2)

    for t,lp in neg_word_prob.iteritems():
        if t in pos_word_prob.keys():
            ratio_neg_vs_pos[t] = math.log((neg_word_prob[t] / pos_word_prob[t]),2)

    sorted_ratio_pos_vs_neg = ratio_pos_vs_neg.iteritems()
    sorted_ratio_neg_vs_pos = ratio_neg_vs_pos.iteritems()
    sorted_ratio_pos_vs_neg = sorted(sorted_ratio_pos_vs_neg,key=lambda l:l[1], reverse=True)
    sorted_ratio_neg_vs_pos = sorted(sorted_ratio_neg_vs_pos,key=lambda l:l[1], reverse=True)

    the_filename = "Ratio_Pos_vs_Neg.csv"
    with open(the_filename, 'w') as f:
        f.write("Type,Token,Log(Probability)" + '\n')
        for ratio in sorted_ratio_pos_vs_neg:
            f.write("Positive" + "," +str(ratio[0]) + "," + str(ratio[1]) +'\n')

    the_filename = "Ratio_Neg_vs_Pos.csv"
    with open(the_filename, 'w') as f:
        f.write("Type,Token,Log(Probability)" + '\n')
        for ratio in sorted_ratio_neg_vs_pos:
            f.write("Negative" + "," +str(ratio[0]) + "," + str(ratio[1]) +'\n')

    the_filename = "model.csv"
    with open(the_filename, 'w') as f:
        f.write("Type,Token,Log(Probability)" + '\n')
        for k,v in pos_reviews_prob.iteritems():
            f.write("Positive" + "," +str(k) + "," + str(v) +'\n')
        for k,v in neg_reviews_prob.iteritems():
            f.write("Negative" + "," +str(k) + "," + str(v) +'\n')
        f.write("Positive Log(Probability)" + "," + str(log_pos_class_prob) + '\n')
        f.write("Negative Log(Probability)" + "," + str(log_neg_class_prob) + '\n')

    print "training done"
    return the_filename

def predict_class(text_dict,class_dict,class_prob):
    prediction = 0.0
    for word in text_dict.keys():
        if word in class_dict.keys():
            prediction +=  float(text_dict[word]) * (class_dict[word])
        else:
            prediction +=  float(text_dict[word]) * 0
    return prediction + class_prob

def nbdev(dev_dir,model):
    pos_files = []
    neg_files = []
    pos_reviews = dict()
    neg_reviews = dict()
    prob_pos_review = 0
    prob_neg_review = 0

    f = open(model)
    lines = f.read().split("\n")
    for line in lines:
        review = line.split(",")
        if review[0] == "Positive":
            pos_reviews[review[1]] = float(review[2])
        if review[0] == "Negative":
            neg_reviews[review[1]]= float(review[2])
        if review[0] == "Positive Log(Probability)":
            prob_pos_review = float(review[1])
        if review[0] == "Negative Log(Probability)":
            prob_neg_review = float(review[1])

    dev_files = []
    for root, dirnames, filenames in os.walk(dev_dir):
        for filename in fnmatch.filter(filenames, '*.txt'):
            dev_files.append(os.path.join(root, filename))

    for root, dirnames, filenames in os.walk(dev_dir):
        for filename in fnmatch.filter(filenames, '*.txt'):
            dirname = root.split(os.path.sep)[-1]
            if dirname == "pos":
                pos_files.append(filename)
            if dirname == "neg":
                neg_files.append(filename)

    dev_scores = dict()
    for text_file in dev_files:
        f = open(text_file)
        lines = f.read().split("\n")
        text_list=[]
        prediction_str = ""
        for line in lines:
            line = line.translate(None,''.join(chars_to_remove))
            line = str(line).lstrip(" ")
            line = str(line).rstrip(" ")
            text_list.extend(str(line).split(' '))
        if '' in text_list:
            text_list.remove('')
        text_list = [x for x in text_list if not (x.isdigit()) and x != '']
        text_list = [filter(lambda s: s in string.printable, x) for x in text_list]
        text_dict = Counter(text_list)
        if '' in text_dict.keys():
            text_dict.pop('', None)

        negative_prediction = predict_class(text_dict, neg_reviews, prob_neg_review)
        positive_prediction = predict_class(text_dict, pos_reviews, prob_pos_review)
        if positive_prediction > negative_prediction:
            prediction_str = "Positive"
        else:
            prediction_str = "Negative"
        dev_scores[text_file] = str(positive_prediction) + "," + str(negative_prediction)+ "," + prediction_str

    count_correct_pos = 0
    count_pos = 0
    count_correct_neg = 0
    count_neg = 0
    for f,pred in dev_scores.iteritems():
        prediction = pred.split(",")[len(pred.split(","))-1:len(pred.split(","))]
        if prediction == ["Positive"]:
            count_pos += 1
        if prediction == ["Negative"]:
            count_neg += 1
        file = f.split("/")[len(f.split("/"))-1: len(f.split("/"))]
        if prediction == ["Positive"] and str(file[0]) in pos_files:
            count_correct_pos += 1
        if prediction == ["Negative"] and str(file[0]) in neg_files:
            count_correct_neg += 1

    print str(float(count_correct_pos)/len(pos_files))
    print str(float(count_correct_neg)/len(neg_files))

    the_filename = "dev_scores.csv"
    with open(the_filename, 'w') as f:
        f.write("File,Positive Score,Negative Score,Prediction" + '\n')
        for k,v in dev_scores.iteritems():
            f.write(str(k) + "," + str(v) +'\n')
    print "dev done"
    return filename

def nbtest(test_dir,model):
    pos_reviews = dict()
    neg_reviews = dict()
    prob_pos_review = 0
    prob_neg_review = 0

    f = open(model)
    lines = f.read().split("\n")
    for line in lines:
        review = line.split(",")
        if review[0] == "Positive":
            pos_reviews[review[1]] = float(review[2])
        if review[0] == "Negative":
            neg_reviews[review[1]]= float(review[2])
        if review[0] == "Positive Log(Probability)":
            prob_pos_review = float(review[1])
        if review[0] == "Negative Log(Probability)":
            prob_neg_review = float(review[1])

    mixed_files = []
    for root, dirnames, filenames in os.walk(test_dir):
        for filename in fnmatch.filter(filenames, '*.txt'):
            mixed_files.append(os.path.join(root, filename))
    #model variables
    test_scores = dict()
    for text_file in mixed_files:
        f = open(text_file)
        lines = f.read().split("\n")
        text_list=[]
        prediction_str = ""
        for line in lines:
            line = line.translate(None,''.join(chars_to_remove))
            line = str(line).lstrip(" ")
            line = str(line).rstrip(" ")
            text_list.extend(str(line).split(' '))
        if '' in text_list:
            text_list.remove('')
        text_list = [x for x in text_list if not (x.isdigit()) and x != '']
        text_list = [filter(lambda s: s in string.printable, x) for x in text_list]
        text_dict = Counter(text_list)
        if '' in text_dict.keys():
            text_dict.pop('', None)

        negative_prediction = predict_class(text_dict, neg_reviews, prob_neg_review)
        positive_prediction = predict_class(text_dict, pos_reviews, prob_pos_review)
        if positive_prediction > negative_prediction:
            prediction_str = "Positive"
        else:
            prediction_str = "Negative"
        test_scores[text_file] = str(positive_prediction) + "," + str(negative_prediction) + "," + prediction_str
    the_filename = "test_scores.csv"
    with open(the_filename, 'w') as f:
        f.write("File,Positive Score,Negative Score,Prediction" + '\n')
        for k,v in test_scores.iteritems():
            f.write(str(k) + "," + str(v) +'\n')
    print "testing done"
    return filename

    # We assign a classification based on which probability is greater.

nbtrain(train_dir)
nbdev(dev_dir,model_file)
nbtest(test_dir,model_file)
