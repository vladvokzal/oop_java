package main.java.ru.nsu.ccfit.voytenko.lab1;

public class WordStat implements Comparable<WordStat>{

    public int wordCounter;
    public String word;

    WordStat(int wordCounter, String word){
        this.wordCounter = wordCounter;
        this.word = word;
    }

    public int getWordCounter(){
        return wordCounter;
    }

    public String getWord(){
        return word;
    }

    public void incrementCount(){
        ++wordCounter;
    }

    @Override
    public int compareTo(WordStat o) {
        return o.getWordCounter() - wordCounter;
    }

}
