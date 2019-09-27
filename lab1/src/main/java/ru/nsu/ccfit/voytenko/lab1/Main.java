package main.java.ru.nsu.ccfit.voytenko.lab1;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        ReadFile r = new ReadFile();
        WriteFile w = new WriteFile();

        r.read("input.txt");

        w.write("output.txt", r.getMyTreeSet(r.getMyMap()), r.getWordsCounter());
    }
}
