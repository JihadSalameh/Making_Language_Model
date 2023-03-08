package com.aiproject3.aiproject3;

import java.util.HashMap;

public class BiAndTriGram {

    HashMap<String, Row> lastWord;

    public BiAndTriGram(HashMap<String, Row> lastWord) {
        this.lastWord = lastWord;
    }

    public HashMap<String, Row> getLastWord() {
        return lastWord;
    }

    public void setLastWord(HashMap<String, Row> lastWord) {
        this.lastWord = lastWord;
    }

    @Override
    public String toString() {
        return "BiAndTriGram{" +
                "lastWord=" + lastWord +
                '}';
    }

}
