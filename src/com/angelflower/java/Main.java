package com.angelflower.java;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    public static String SSID, MAC, Pass;

    public Main() {
        this.setTitle("GUP");
        this.setSize(400,300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel textTitle = new JLabel("GUP - Get Ubee Password");
        textTitle.setBounds(10,5,300,20);
        add(textTitle);

        JLabel textSSID = new JLabel("SSID:");
        textSSID.setBounds(80,60,70,20);
        add(textSSID);

        JLabel textMAC = new JLabel("MAC address:");
        textMAC.setBounds(80,95,100,20);
        add(textMAC);

        JTextField txtSSID = new JTextField();
        txtSSID.setBounds(180,60,130,25);
        add(txtSSID);

        JTextField txtMAC = new JTextField();
        txtMAC.setBounds(180,95,130,25);
        add(txtMAC);

        JButton btnGetPass = new JButton("Get Password");
        btnGetPass.setBounds(60,130,270,25);
        add(btnGetPass);
        btnGetPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SSID = txtSSID.getText();
                MAC = txtMAC.getText();

                //SSID = input.nextLine();
                char[] ArraySSID = SSID.toCharArray();
                char[] ArraySSIDFiltered = getCharsSSID(ArraySSID);

                //System.out.print("Enter MAC: ");
                //MAC = input.nextLine();
                char[] ArrayMac = MAC.toCharArray();
                char[] ArrayMacFiltered = FilterMAC(ArrayMac);

                Pass = getPassword(ArrayMacFiltered, ArraySSIDFiltered);

                String[] options = new String[]{"Copy", "OK"};
                JDialog JDshowPass = new JDialog();
                JDshowPass.setSize(200, 200);
                JDshowPass.setLocationRelativeTo(null);
                JDshowPass.setLayout(null);
                JDshowPass.setTitle(SSID+" - Ready");
                JDshowPass.setModal(true);

                JLabel textShowSSID = new JLabel("SSID: "+SSID);
                textShowSSID.setBounds(30,25,100,25);
                JDshowPass.add(textShowSSID);

                JLabel textPass = new JLabel("Password: "+Pass);
                textPass.setBounds(30,60,150,25);
                JDshowPass.add(textPass);

                JButton btnCopyPass = new JButton("Copy");
                btnCopyPass.setBounds(10,130,84,25);
                JDshowPass.add(btnCopyPass);
                btnCopyPass.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        copyPass();
                    }
                });

                JButton btnOK = new JButton("Ok");
                btnOK.setBounds(93,130,80,25);
                JDshowPass.add(btnOK);
                btnOK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JDshowPass.dispose();
                    }
                });

                JDshowPass.setVisible(true);
                System.out.println("Password: "+Pass);
            }
        });

        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace ();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Main window = new Main();
    }

    public static char[] FilterMAC(char[] ArrayMac){
        char[] Filtered = new char[12];
        int index = 0;
        for (int i = 2; i < ArrayMac.length; i++) {
            if(!(ArrayMac[i] == ':')){
                Filtered[index] = ArrayMac[i];
                index ++;
            }
        }
        return Filtered;
    }

    public static char[] getCharsSSID(char [] ArraySSID){
        char[] Chars = new char[4];
        int index = 0;
        for (int i = 4; i < 8 ; i++) {
            Chars[index] = ArraySSID[i];
            index++;
        }
        return Chars;
    }

    public static String getPassword(char[] MACFiltered, char[] SSIDFiltered){
        char[] Pass = new char[10];
        for (int i = 0; i < 6 ; i++) {
            Pass[i] = MACFiltered[i];
        }
        for (int i = 6; i < 10 ; i++) {
            Pass[i] = SSIDFiltered[i-6];
        }
        return String.copyValueOf(Pass);
    }

    private void copyPass() {
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection ss = new StringSelection(Pass);
        System.out.println(Pass);
        cb.setContents(ss, ss);

    }

}
