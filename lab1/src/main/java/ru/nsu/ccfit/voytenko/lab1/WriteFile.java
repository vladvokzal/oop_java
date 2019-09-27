package main.java.ru.nsu.ccfit.voytenko.lab1;

import java.io.*;
import java.util.*;

public class WriteFile {

    public void write(String fileName, TreeSet<WordStat> set, int counter) throws IOException{

        try(Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)))){

            for(WordStat cur : set){
                writer.write(cur.getWord() + ", ");
                writer.write(cur.getWordCounter() + ", ");
                writer.write(String.format("%.2f", cur.getWordCounter() * 1.0 / counter * 100));
                writer.write("%" + "\n");
            }

        }
        catch(IOException exept){
            System.err.println("Error in writing file: " + exept.getLocalizedMessage());
        }


    }

}