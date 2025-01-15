import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WordGuessing extends JPanel {
    JLabel[][] label;
    Dimension wordGuessingDimension, labelDimension;
    char key;
    char[] inputWord;
    int x = 0, y = 0, i = 0;

    WordGuessing(String answer, boolean cheatingMode) {
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setFocusable(true);
        this.requestFocusInWindow();

        wordGuessingDimension = new Dimension(380, 450);
        this.setPreferredSize(wordGuessingDimension);
        this.setMinimumSize(wordGuessingDimension);
        this.setMaximumSize(wordGuessingDimension);

        labelDimension = new Dimension(60, 60);

        label = new JLabel[5][6];
        int padding = 10;
        int startX = 20, startY = 20;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                label[i][j] = new JLabel("   ", SwingConstants.CENTER);
                label[i][j].setBackground(Color.LIGHT_GRAY);
                label[i][j].setOpaque(true);
                label[i][j].setBorder(BorderFactory.createRaisedBevelBorder());
                label[i][j].setFont(new Font("Serif", Font.BOLD, 30));
                label[i][j].setBounds(startX + i * (labelDimension.width + padding),
                        startY + j * (labelDimension.height + padding),
                        labelDimension.width, labelDimension.height);
                this.add(label[i][j]);
            }
        }

        inputWord = new char[5];

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                key = e.getKeyChar();
                System.out.println(e.getKeyCode());
                if (answer.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please set the mode then play");
                    key = '0';
                }
                if (Character.isLetter(key)) {
                    key = Character.toUpperCase(key);
                    if (i <= 4) {
                        inputWord[i] = key;
                        label[x][y].setText(" " + key + " ");
                        label[x][y].repaint();
                        i++;
                        x++;
                    }else {
                        JOptionPane.showMessageDialog(null, "You can not input more letter to this word");
                    }
                }

                switch (e.getKeyCode()) {
                    case 8: { //delete
                        if (i > 0) {
                            x--;
                            i--;
                        }
                        label[x][y].setText("   ");
                        label[x][y].repaint();
                        inputWord[i] = '0';
                    }break;
                    case 10: { //enter
                        if (i == 5) {
                            if (SearchAndMatch.sam(inputWord, label, y, answer, cheatingMode)) { // search and match
                                for (i = 0; i < 5; i++) {
                                    label[i][y].repaint();
                                }
                                if (answer.equals(new String(inputWord))) {
                                    JOptionPane.showMessageDialog(null, "You found the word " + answer);
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
        });
    }
}