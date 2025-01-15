import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class WordleFrame extends JFrame implements ActionListener {

    String answer;
    WordGuessing wordGuessing;
    JFrame frame;
    public final File wordsLib = new File("answerlib");
    int randomTime;
    boolean cheatingMode;
    SecretKey key;
//    String cipherText;
    IvParameterSpec ivParameterSpec;
    String algorithm;

    WordleFrame() {
        //AES thing
        try {
            key = EncryptAndDecrypt.generateKey(128);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
        ivParameterSpec = EncryptAndDecrypt.generateIv();
        algorithm = "AES/CBC/PKCS5Padding";

        cheatingMode = false;
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
        wordGuessing = new WordGuessing(answer, cheatingMode);
        frame.add(wordGuessing);
        wordGuessing.requestFocusInWindow();
        System.out.println(answer+" "+randomTime);
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

                if (!(answer.length() == 5) && answer.chars().allMatch(Character::isLetter)) {
                    JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a valid word.");
                    return;
                }
                wordGuessing = new WordGuessing(answer, cheatingMode);
                frame.getContentPane().removeAll();
                frame.add(wordGuessing);
                frame.revalidate();
                frame.repaint();
                wordGuessing.requestFocusInWindow();
                System.out.println("New answer set: " + answer);
            }break;
            case "Random": {
                randomTime = (int) (Math.random()*3103);
                try {
                    Scanner scanner = new Scanner(wordsLib);
                    for (int i = 0; i < randomTime; i++) {
                        answer = scanner.nextLine().toUpperCase();
                    }
                }catch (FileNotFoundException ea){
                    ea.printStackTrace();
                }
                wordGuessing = new WordGuessing(answer, cheatingMode);
                frame.getContentPane().removeAll();
                frame.add(wordGuessing);
                frame.revalidate();
                frame.repaint();
                wordGuessing.requestFocusInWindow();
                System.out.println(answer+" "+randomTime);
            }break;
            case "JesseMode": {
                cheatingMode = true;
                wordGuessing = new WordGuessing(answer, cheatingMode);
                frame.getContentPane().removeAll();
                frame.add(wordGuessing);
                frame.revalidate();
                frame.repaint();
                wordGuessing.requestFocusInWindow();
                System.out.println("is cheating mode: " + cheatingMode);
            }break;
            case "OriginMode": {
                cheatingMode = false;
                wordGuessing = new WordGuessing(answer, cheatingMode);
                frame.getContentPane().removeAll();
                frame.add(wordGuessing);
                frame.revalidate();
                frame.repaint();
                wordGuessing.requestFocusInWindow();
                System.out.println("is cheating mode: " + cheatingMode);
            }break;
            case "Generated from friends": {
                String input = JOptionPane.showInputDialog("Please enter the code generated by friend who would like you to guess: ");
                String plainText;
                try {
                    plainText = EncryptAndDecrypt.decrypt(algorithm, input, key, ivParameterSpec);
                    answer = plainText;
                    System.out.println(answer);
                    JOptionPane.showMessageDialog(null, "Setting successfully");
                } catch (NoSuchPaddingException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                } catch (InvalidAlgorithmParameterException ex) {
                    throw new RuntimeException(ex);
                } catch (InvalidKeyException ex) {
                    throw new RuntimeException(ex);
                } catch (BadPaddingException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalBlockSizeException ex) {
                    throw new RuntimeException(ex);
                }
            }break;
            case "Generate for friends": {
                String input = JOptionPane.showInputDialog("Please input the word that you would like to let your friends guess: ").toUpperCase();
                try {
                   String cipherText = EncryptAndDecrypt.encrypt(algorithm, input, key, ivParameterSpec);
                    JOptionPane.showMessageDialog(null, new JTextArea(cipherText), "Code for the word", JOptionPane.INFORMATION_MESSAGE);
                } catch (NoSuchPaddingException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                } catch (InvalidAlgorithmParameterException ex) {
                    throw new RuntimeException(ex);
                } catch (InvalidKeyException ex) {
                    throw new RuntimeException(ex);
                } catch (BadPaddingException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalBlockSizeException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    }
}