package labTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import main.java.ru.nsu.ccfit.voytenko.lab1.ReadFile;

import java.io.IOException;

class ReadFileTest {

    @Test
    void getWordsCounter() {
        ReadFile r = new ReadFile();
        try {
            r.read("test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(r.getMyMap().get("bear").getWordCounter(), 1);
        Assertions.assertEquals(r.getMyMap().get("beer").getWordCounter(), 1);

        Assertions.assertEquals(2, r.getWordsCounter());
    }
}