/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package violinassist;

import javax.sound.midi.*;
import javax.swing.SwingUtilities;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adas
 */

public class ViolinAssist {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ViolinAssist va = new ViolinAssist();
        if (args.length > 0) {
            switch(args[0]) {
                case "tune":
                    va.startTuner();
                    break;
                case "play":
                    va.play(args[1], args[2], Integer.parseInt(args[3]));
                    break;
                case "help":
                    va.printHelp();
                default:
                    break;
            }
        } else {
            va.printHelp();
        }
    } 
    void startTuner() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ViolinTuner();
                } catch (MidiUnavailableException noMidi) {
                    noMidi.printStackTrace();
                    System.exit(1);
                }
            }
        });
    }
    
    void printHelp(){
        System.out.println("ViolinAssist v 0.1");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Arguments:");
        System.out.println("---------------------------------------------------------------");
        System.out.println("help                           - prints this");
        System.out.println("play <file> <tempo> <cycles>   - plays the notes of the <file>,");
        System.out.println("                                 fetches them from current dir.");
        System.out.println("                                 <tempo> can be v (vilambit), d");
        System.out.println("                                 (druta), ad (ati-druta) or aad");
        System.out.println("                                 (ati ati druta). <cycles> is  ");
        System.out.println("                                 the number of times to repeat!");
        System.out.println("tune                           - opens tuner");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Example usage: java -jar ViolinAssist.jar tune");
        System.out.println("               java -jar ViolinAssist.jar play yaman.txt v 10");
        System.out.println("---------------------------------------------------------------");
    }
    
    void play(String filename, String tempo, int duration){
        try {
            new PlayNotes(filename, tempo, duration);
        } catch (Exception muex){
            muex.printStackTrace();
            System.exit(1);
        }
    }
}
