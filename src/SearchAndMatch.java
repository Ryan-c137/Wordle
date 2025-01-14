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

    public static Boolean sam(char[] input, JLabel[][] label, int y, String answer) {
        JLabel[] label2D = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            label2D[i] = label[i][y];
        }
        String inputString = new String(input);
        File wordsLib = new File("words.txt");
        String validWord;
        try {
            Scanner scanner = new Scanner(wordsLib);
            for (int i = 0; i < 5757; i++) {
                validWord = scanner.nextLine().toUpperCase();
                if (inputString.equals(validWord)) {
                    match(inputString, label2D, answer);
                    return true;
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    public static void match(String inputString, JLabel[] label, String answerString) {
        char[] answer = answerString.toCharArray();
        char[] input = inputString.toCharArray();
        HashSet<Character> answerSet = new HashSet<>();

        for (int j = 0; j < 5; j++) {
            if (answer[j] == input[j]) {
                label[j].setBackground(Color.GREEN);
                input[j] = 0;
            }else {
                answerSet.add(answer[j]);
            }
        }

        for (int j = 0; j < 5; j++) {
            if ((!(input[j] == 0)) && answerSet.contains(input[j])) {
                label[j].setBackground(Color.YELLOW);
            }else if (!(input[j] == 0)) {
                label[j].setBackground(Color.GRAY);
            }
        }
    }
}
