import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class SearchAndMatch {


    public static Boolean sam(char[] input, int y) {
        System.out.println(input[0] + " " + y);
        JLabel[] label2D = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            label2D[i] = WordGuessing.label[i][y];
        }
        String inputString = new String(input);
        if (!WordleFrame.cheatingMode) {
            File wordsLib = new File("words.txt");
            String validWord;
            try {
                Scanner scanner = new Scanner(wordsLib);
                for (int i = 0; i < 5757; i++) {
                    validWord = scanner.nextLine().toUpperCase();
                    if (inputString.equals(validWord) || inputString.equals(WordleFrame.answer)) {
                        match(inputString, label2D, WordleFrame.answer);
                        return true;
                    }
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }else {
            match(inputString, label2D, WordleFrame.answer);
            return true;
        }
        return false;
    }

    public static void match(String inputString, JLabel[] label, String answerString) {
        char[] answer = answerString.toCharArray();
        char[] input = inputString.toCharArray();

        //note green
        for (int j = 0; j < 5; j++) {
            if (answer[j] == input[j]) {
                label[j].setBackground(Color.GREEN);
                MiniKeyboard.buttons[MiniKeyboard.letterToNumber(input[j])].setBackground(Color.GREEN);
//                MiniKeyboard.buttons[MiniKeyboard.letterToNumber(input[j])].setForeground(Color.GREEN);
                input[j] = 0;
                answer[j] = 0;
            }
        }

        //note yellow
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                if ((input[j] != 0) && input[j] == answer[k]) {
                    label[j].setBackground(Color.YELLOW);
                    MiniKeyboard.buttons[MiniKeyboard.letterToNumber(input[j])].setBackground(Color.YELLOW);
//                    MiniKeyboard.buttons[MiniKeyboard.letterToNumber(input[j])].setForeground(Color.YELLOW);
                    input[j] = 0;
                    answer[k] = 0;
                }
            }
        }

        //note gray
        for (int j = 0; j < 5; j++) {
            if (input[j] != 0) {
                label[j].setBackground(Color.GRAY);
                MiniKeyboard.buttons[MiniKeyboard.letterToNumber(input[j])].setBackground(Color.GRAY);
//                MiniKeyboard.buttons[MiniKeyboard.letterToNumber(input[j])].setForeground(Color.GRAY);
            }
        }
    }
}
