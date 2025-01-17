import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WordGuessing extends JPanel {
    public static JLabel[][] label = new JLabel[5][6];
    Dimension wordGuessingDimension, labelDimension;
    char key;
    static ChangeReceive changeReceive;

    WordGuessing() {
        changeReceive = new ChangeReceive();
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBounds(0, 0 , 380, 450);
        this.setFocusable(true);
        this.requestFocusInWindow();

        wordGuessingDimension = new Dimension(380, 450);
        this.setPreferredSize(wordGuessingDimension);
        this.setMinimumSize(wordGuessingDimension);
        this.setMaximumSize(wordGuessingDimension);

        labelDimension = new Dimension(60, 60);


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



        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                key = e.getKeyChar();
                System.out.println(key);
                switch (e.getKeyCode()) {
                    case 10: key = '1'; break;
                    case 8: key = '0';
                }
                changeReceive.core(key);
            }
        });
    }
}