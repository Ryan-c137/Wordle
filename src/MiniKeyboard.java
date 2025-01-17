import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiniKeyboard extends JPanel implements ActionListener {

    static JButton[] buttons = new JButton[26];
    Dimension miniKeyboardSize, buttonsSize;
    static JButton delete, enter;

    public MiniKeyboard() {
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setBounds(0, 450, 380, 180);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        miniKeyboardSize = new Dimension(380, 180);
        this.setPreferredSize(miniKeyboardSize);
        this.setMinimumSize(miniKeyboardSize);
        this.setMaximumSize(miniKeyboardSize);

        buttonsSize = new Dimension(30, 50);

        for (int i = 0; i < 26; i++) {
            buttons[i] = new JButton(keyboardLetters(i));
            buttons[i].setOpaque(true);
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setForeground(Color.BLACK);
            buttons[i].setSize(buttonsSize);
            buttons[i].setFocusable(false);
            if (i < 10) {
                buttons[i].setBounds(17 + i * (buttonsSize.width + 5),
                        10, buttonsSize.width, buttonsSize.height);
            } else if (i < 19) {
                buttons[i].setBounds(35 + (i-10) * (buttonsSize.width + 5),
                        buttonsSize.height + 15 , buttonsSize.width, buttonsSize.height);
            }else {
                buttons[i].setBounds(70 + (i-19) * (buttonsSize.width + 5),
                        (buttonsSize.height + 5)*2 + 10 , buttonsSize.width, buttonsSize.height);
            }

            buttons[i].addActionListener(this);
            this.add(buttons[i]);
        }

        delete = new JButton("Delete");
        delete.setOpaque(true);
        delete.setBackground(Color.WHITE);
        delete.setForeground(Color.BLACK);
        delete.setSize(60, 50);
        delete.setBounds(5, 120, 60, 50);
        delete.setFocusable(false);
        delete.addActionListener(this);
        this.add(delete);

        enter = new JButton("Enter");
        enter.setOpaque(true);
        enter.setBackground(Color.WHITE);
        enter.setForeground(Color.BLACK);
        enter.setSize(60, 50);
        enter.setBounds(315, 120, 60, 50);
        enter.setFocusable(false);
        enter.addActionListener(this);
        this.add(enter);

    }
    
    String keyboardLetters(int a) {
        return switch (a+1) {
            case 1 -> "Q";
            case 2 -> "W";
            case 3 -> "E";
            case 4 -> "R";
            case 5 -> "T";
            case 6 -> "Y";
            case 7 -> "U";
            case 8 -> "I";
            case 9 -> "O";
            case 10 -> "P";
            case 11 -> "A";
            case 12 -> "S";
            case 13 -> "D";
            case 14 -> "F";
            case 15 -> "G";
            case 16 -> "H";
            case 17 -> "J";
            case 18 -> "K";
            case 19 -> "L";
            case 20 -> "Z";
            case 21 -> "X";
            case 22 -> "C";
            case 23 -> "V";
            case 24 -> "B";
            case 25 -> "N";
            case 26 -> "M";
            default -> " ";
        };
    }

    public static int letterToNumber(char letter) {
        switch (letter) {
            case 'W': return 1;
            case 'E': return 2;
            case 'R': return 3;
            case 'T': return 4;
            case 'Y': return 5;
            case 'U': return 6;
            case 'I': return 7;
            case 'O': return 8;
            case 'P': return 9;
            case 'A': return 10;
            case 'S': return 11;
            case 'D': return 12;
            case 'F': return 13;
            case 'G': return 14;
            case 'H': return 15;
            case 'J': return 16;
            case 'K': return 17;
            case 'L': return 18;
            case 'Z': return 19;
            case 'X': return 20;
            case 'C': return 21;
            case 'V': return 22;
            case 'B': return 23;
            case 'N': return 24;
            case 'M': return 25;
            default : return 0;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        char key = e.getActionCommand().charAt(0);
        if (e.getActionCommand().equals("Delete")) {
            key = '0';
        }else if (e.getActionCommand().equals("Enter")) {
            key = '1';
        }
        WordGuessing.changeReceive.core(key);
    }
}
