package main.java.ru.nsu.ccfit.voytenko.lab1;
import java.lang.StringBuilder;
import java.io.*;
import java.util.*;

public class ReadFile {

    private int counter = 0;
    TreeMap<String, WordStat> myMap;

    public void read(String fileName) throws IOException {

        final int BLOCK_SIZE = 2048;
        int size;
        char[] buffer = new char[BLOCK_SIZE];
        myMap = new TreeMap<>();

        StringBuilder str = new StringBuilder();

        try (Reader reader = new InputStreamReader(new FileInputStream(fileName))) {

            for (; ; ) {
                size = reader.read(buffer);
                if (size < 0) {
                    break;
                }
                for (int j = 0; j <= size; ++j) {

                    if (Character.isLetterOrDigit(buffer[j])) {
                        str.append(buffer[j]);
                    } else if (str.length() > 0) {
                        String currentWord = str.toString();
                        WordStat wordInfo = new WordStat(1, currentWord);
                        myMap.merge(currentWord, wordInfo, (a, b) -> new WordStat(myMap.get(currentWord).getWordCounter() + 1, currentWord ));
                        str.delete(0, str.length());
                        counter++;
                    }
                }

                if(str.length() > 0){
                    str = str.append(str.length());
                }
            }

        } catch (IOException exept) {
            System.err.println("Error while reading file: " + exept.getLocalizedMessage());
        }

    }

    public int getWordsCounter() {
        return counter;
    }

    public TreeSet<WordStat> getMyTreeSet(TreeMap<String, WordStat> freqMap) {
        TreeSet<WordStat> wordStats = new TreeSet<>(freqMap.values());
        return wordStats;
    }

    public TreeMap<String, WordStat> getMyMap() {
        return myMap;
    }


}