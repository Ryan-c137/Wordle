import javax.swing.*;
import java.awt.*;

public class ChangeReceive {
    int x, y, i;
    char[] inputWord;
    ChangeReceive() {
        x = y = i = 0;
        inputWord = new char[5];
    }

    void core(char key) {
        if (Character.isLetter(key)) {
            key = Character.toUpperCase(key);
            if (i <= 4) {
                inputWord[i] = key;
                System.out.println(inputWord[i] + " " + key);
                WordGuessing.label[x][y].setText(" " + key + " ");
                WordGuessing.label[x][y].repaint();
                i++;
                x++;
            }else {
                JOptionPane.showMessageDialog(null, "You can not input more letter to this word");
            }
        }
        switch (key) {
            case '0': { //delete
                if (i > 0) {
                    x--;
                    i--;
                }
                WordGuessing.label[x][y].setText("   ");
                WordGuessing.label[x][y].repaint();
                inputWord[i] = '0';
            }break;
            case '1': { //enter
                if (i == 5) {
                    if (SearchAndMatch.sam(inputWord, y)) { // search and match
                        for (i = 0; i < 5; i++) {
                            WordGuessing.label[i][y].repaint();
                            MiniKeyboard.buttons[MiniKeyboard.letterToNumber(inputWord[i])].repaint();
                        }
                        if (WordleFrame.answer.equals(new String(inputWord))) {
                            JOptionPane.showMessageDialog(null, "You found the word " + WordleFrame.answer);
                        } else if (y >= 5) {
                            JOptionPane.showMessageDialog(null, "The correct answer is:  " + WordleFrame.answer + "\n YOU FUCKING PATHETIC IDIOT");
                        }
                        y++;
                        x = 0;
                        i = 0;
                    }else {
                        JOptionPane.showMessageDialog(null, "Invalid word");
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "You need to fill the whole word");
                }
            }
        }
    }
}
