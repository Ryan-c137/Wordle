import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordleFrame extends JFrame implements ActionListener {

    String answer;
    WordGuessing wordGuessing;
    JFrame frame;

    WordleFrame() {
        answer = new String();
        frame = new JFrame("Wordle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //add menu
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        //word menu
        JMenu wordMenu = new JMenu("Word");
        menuBar.add(wordMenu);
        JMenuItem selfSettingItem = new JMenuItem("SelfSetting");
        wordMenu.add(selfSettingItem);
        JMenuItem randomItem = new JMenuItem("Random");
        wordMenu.add(randomItem);
        //mode menu
        JMenu modeMenu = new JMenu("Mode");
        menuBar.add(modeMenu);
        JMenuItem cheatingItem = new JMenuItem("JesseMode");
        modeMenu.add(cheatingItem);
        JMenuItem originItem = new JMenuItem("OriginMode");
        modeMenu.add(originItem);
        //friends menu
        JMenu friendsMenu = new JMenu("Friends");
        menuBar.add(friendsMenu);
        JMenuItem inputItem = new JMenuItem("Generated from friends");
        friendsMenu.add(inputItem);
        JMenuItem outputItem = new JMenuItem("Generate for friends");
        friendsMenu.add(outputItem);

        selfSettingItem.addActionListener(this);
        randomItem.addActionListener(this);
        cheatingItem.addActionListener(this);
        originItem.addActionListener(this);
        inputItem.addActionListener(this);
        outputItem.addActionListener(this);

        wordGuessing = new WordGuessing(answer);
        frame.add(wordGuessing);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WordleFrame();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "SelfSetting": {
                answer = JOptionPane.showInputDialog("Please enter the word you would like to guess: ").toUpperCase();

                // Validate the input
                if (!(answer.length() == 5) && answer.chars().allMatch(Character::isLetter)) {
                    JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a valid word.");
                    return;
                }

                wordGuessing = new WordGuessing(answer);
                frame.getContentPane().removeAll();
                frame.add(wordGuessing);
                frame.revalidate();
                frame.repaint();

                System.out.println("New answer set: " + answer);
                break;
            }
        }
    }
}