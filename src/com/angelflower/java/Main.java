package com.angelflower.java;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String SSID, MAC;
        System.out.print("Enter SSID: ");
        SSID = input.nextLine();
        char[] ArraySSID = SSID.toCharArray();
        char[] ArraySSIDFiltered = getCharsSSID(ArraySSID);

        System.out.print("Enter MAC: ");
        MAC = input.nextLine();
        char[] ArrayMac = MAC.toCharArray();
        char[] ArrayMacFiltered = FilterMAC(ArrayMac);

        System.out.println("Password: "+getPassword(ArrayMacFiltered, ArraySSIDFiltered));
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
        int index = 0;
        char[] Pass = new char[10];
        for (int i = 0; i < 6 ; i++) {
            Pass[index] = MACFiltered[i];
            index++;
        }
        for (int i = 6; i < 10 ; i++) {
            Pass[i] = SSIDFiltered[i-6];
        }
        return String.copyValueOf(Pass);
    }
}
