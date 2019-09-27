package labTest;
import main.java.ru.nsu.ccfit.voytenko.lab1.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Main mylab;
    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    public void isCorrectCounting(){
        mylab.main(new String[]{"input.txt", "input.txt"});
    }
}