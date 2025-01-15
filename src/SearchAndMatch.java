import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class SearchAndMatch {

    public static Boolean sam(char[] input, JLabel[][] label, int y, String answer, boolean cheatingMode) {
        JLabel[] label2D = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            label2D[i] = label[i][y];
        }
        String inputString = new String(input);
        if (!cheatingMode) {
            File wordsLib = new File("words.txt");
            String validWord;
            try {
                Scanner scanner = new Scanner(wordsLib);
                for (int i = 0; i < 5757; i++) {
                    validWord = scanner.nextLine().toUpperCase();
                    if (inputString.equals(validWord) || inputString.equals(answer)) {
                        match(inputString, label2D, answer);
                        return true;
                    }
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }else {
            match(inputString, label2D, answer);
            return true;
        }
        return false;
    }

    public static void match(String inputString, JLabel[] label, String answerString) {
        char[] answer = answerString.toCharArray();
        char[] input = inputString.toCharArray();
        HashSet<Character> answerSet = new HashSet<>();

        //note green
        for (int j = 0; j < 5; j++) {
            if (answer[j] == input[j]) {
                label[j].setBackground(Color.GREEN);
                input[j] = 0;
                answer[j] = 0;
            }
        }

        //note yellow
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                if ((input[j] != 0) && input[j] == answer[k]) {
                    label[j].setBackground(Color.YELLOW);
                    input[j] = 0;
                    answer[k] = 0;
                }
            }
        }

        //note gray
        for (int j = 0; j < 5; j++) {
            if (input[j] != 0) {
                label[j].setBackground(Color.GRAY);
            }
        }
    }
}
