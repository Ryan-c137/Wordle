import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordleFrame extends JFrame implements ActionListener {

    static String answer;
    WordGuessing wordGuessing;
    MiniKeyboard miniKeyboard;
    JFrame frame;
    public final File wordsLib = new File("answerlib");
    int randomTime;
    static boolean cheatingMode;


    WordleFrame() {
        cheatingMode = false;
        frame = new JFrame("Wordle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //add menu
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        //word menu
        JMenu wordMenu = new JMenu("Word");
        menuBar.add(wordMenu);
        JMenuItem selfSettingItem = new JMenuItem("Set by self");
        wordMenu.add(selfSettingItem);
        JMenuItem randomItem = new JMenuItem("Random mode");
        wordMenu.add(randomItem);
        //mode menu
        JMenu modeMenu = new JMenu("Mode");
        menuBar.add(modeMenu);
        JMenuItem cheatingItem = new JMenuItem("Cheating mode");
        modeMenu.add(cheatingItem);
        JMenuItem originItem = new JMenuItem("Origin mode");
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

        //set random answer
        randomTime = (int) (Math.random()*3103);
        try {
            Scanner scanner = new Scanner(wordsLib);
            for (int i = 0; i < randomTime; i++) {
                answer = scanner.nextLine().toUpperCase();
            }
        }catch (FileNotFoundException ea){
            ea.printStackTrace();
        }
        wordGuessing = new WordGuessing();
        frame.add(wordGuessing);
        wordGuessing.requestFocusInWindow();
        System.out.println(answer+" "+randomTime);

        miniKeyboard = new MiniKeyboard();
        frame.add(miniKeyboard);
        frame.setLayout(null);
        frame.setSize(380, 680);
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
            case "Set by self": {
                answer = JOptionPane.showInputDialog("Please enter the word you would like to guess: ").toUpperCase();

                if (!(answer.length() == 5) && answer.chars().allMatch(Character::isLetter)) {
                    JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a valid word.");
                    return;
                }
                reload();
            }break;
            case "Random mode": {
                randomTime = (int) (Math.random()*3103);
                try {
                    Scanner scanner = new Scanner(wordsLib);
                    for (int i = 0; i < randomTime; i++) {
                        answer = scanner.nextLine().toUpperCase();
                    }
                }catch (FileNotFoundException ea){
                    ea.printStackTrace();
                }
                reload();
            }break;
            case "Cheating mode": {
                cheatingMode = true;
                reload();
            }break;
            case "Origin mode": {
                cheatingMode = false;
                reload();
            }break;
            case "Generated from friends": {
                String input = JOptionPane.showInputDialog("Please enter the code generated by friend who would like you to guess: ");
                answer = EncryptAndDecrypt.decrypt(input);
                reload();
            }break;
            case "Generate for friends": {
                String input = JOptionPane.showInputDialog("Please input the word that you would like to let your friends guess: ").toUpperCase();
                String cipherText = EncryptAndDecrypt.encrypt(input);
                JOptionPane.showMessageDialog(null, new JTextArea(cipherText), "Code for the word", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    void reload() {
        miniKeyboard = new MiniKeyboard();
        wordGuessing = new WordGuessing();
        frame.getContentPane().removeAll();
        frame.add(miniKeyboard);
        frame.add(wordGuessing);
        frame.revalidate();
        frame.repaint();
        wordGuessing.requestFocusInWindow();
    }
}