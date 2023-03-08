package com.aiproject3.aiproject3;

public class Answer implements Comparable<Answer>{

    private String word;
    private Double probability;

    public Answer(String word, Double probability) {
        this.word = word;
        this.probability = probability;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "word='" + word + '\'' +
                ", probability=" + probability +
                '}';
    }

    @Override
    public int compareTo(Answer o) {
        if(this.probability > o.probability) {
            return 1;
        } else if (o.probability > this.probability) {
            return -1;
        } else {
            return 0;
        }
    }

}
