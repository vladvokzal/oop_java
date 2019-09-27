package main.java.ru.nsu.ccfit.voytenko.lab1;

import java.io.*;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final String SEPARATOR = ", ";

    public static void main(String[] args) {

        String fileIn;
        String fileOut;
        AtomicInteger atomicInteger = new AtomicInteger();

        if(args.length == 2){
            fileIn = args[0];
            fileOut = args[1];
        }
        else{
            fileIn = "input.txt";
            fileOut = "output.txt";
        }

        try (Stream<String> lines = Files.lines(Paths.get(fileIn));
             Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOut)));
             ) {
            lines
                 .map(line -> line.split("[^\\p{L}0-9]+"))//split string by non-separators
                 .flatMap(Arrays::stream)//get Stream<String>
                 .collect(Collectors.toMap(Function.identity(), s -> 1, (x, y) -> x + y))
                 .entrySet()
                 .stream()
                 .peek(e -> atomicInteger.addAndGet(e.getValue()))
                 .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                 .forEach((x) -> {
                     try {
                         writer.write(x.getKey() + SEPARATOR + x.getValue() + SEPARATOR +
                                 String.format("%.2f", x.getValue() * 1.0 / atomicInteger.get() * 100) + "%\n");
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 });
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
