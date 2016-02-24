/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package violinassist;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author adas
 */
public class PlayNotes {  
    int channel = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments
    int volume = 80; // between 0 et 127
    int duration = 3000; // in milliseconds
    int instrument = 41;
    int currentNote = 0;
    int cycles = 10;
    
    public PlayNotes(String filename, String tempo, int d) throws InterruptedException, FileNotFoundException{
        ArrayList notes = new ArrayList();
        /*String todiNotes[] = {"Dhal", "Dhal", "Pa", "Pa", "Mah", "Mah", "Pa", "Dhal", ",", "Mah", "Gal", ",","Rel", "Gal", "Mah", "Pa", ","};
        for (int i = 0; i < todiNotes.length;i++){
            playTheNote(todiNotes[i]);
        }*/
        switch (tempo){
            case "v":
                duration = 3000;
                break;
            case "d":
                duration = 1500;
                break;
            case "ad":
                duration = 750;
                break;
            case "aad":
                duration = 325;
                break;
            default:
                break;
        }
        
        cycles = d;
        
        String content = "";
        Scanner myTextFile = new Scanner(new FileReader(filename));
        while (myTextFile.hasNext()) {
            // All the contents of the Text File are added to the "content variable
            notes.add(myTextFile.next());
        }
        myTextFile.close();
        // Creating a String Array to store each individual note/pause
        for (int j =0; j < cycles; j++) {
            for (int i = 0; i < notes.size(); i++) {
                playTheNote( (String) notes.get(i));
            }
        }
    }
    
    void playTheNote(String n){
        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            MidiChannel channels = synth.getChannels()[0];
            channels.programChange(instrument - 1);
            channels.setChannelPressure(5);
            switch (n) {
                case "Pal":
                    channels.noteOn(57, volume );
                    currentNote = 57;
                    Thread.sleep( duration );
                    channels.noteOff( 56 );
                    break;
                case "Dll":
                    channels.noteOn(58, volume );
                    currentNote = 58;
                    Thread.sleep( duration );
                    channels.noteOff( 57 );
                    break;
                case "Dlh":
                    channels.noteOn(59, volume );
                    currentNote = 59;
                    Thread.sleep( duration );
                    channels.noteOff( 58 );
                    break;
                case "Nll":
                    channels.noteOn(60, volume );
                    currentNote = 60;
                    Thread.sleep( duration );
                    channels.noteOff( 60 );
                    break;
                case "Nlh":
                    channels.noteOn(61, volume );
                    currentNote = 61;
                    Thread.sleep( duration );
                    channels.noteOff( 61 );
                    break;
                case "Sa":
                    channels.noteOn(62, volume );
                    currentNote = 62;
                    Thread.sleep( duration );
                    channels.noteOff( 62 );
                    break;
                case "Rel":
                    channels.noteOn(63, volume );
                    currentNote = 63;
                    Thread.sleep( duration );
                    channels.noteOff( 63 );
                    break;
                case "Re":
                    channels.noteOn(64, volume );
                    currentNote = 64;
                    Thread.sleep( duration );
                    channels.noteOff( 64 );
                    break;
                case "Gal":
                    channels.noteOn(65, volume );
                    currentNote = 65;
                    Thread.sleep( duration );
                    channels.noteOff( 65 );
                    break;
                case "Ga":
                    channels.noteOn(66, volume );
                    currentNote = 66;
                    Thread.sleep( duration );
                    channels.noteOff( 66 );
                    break;
                case "Ma":
                    channels.noteOn(67, volume );
                    currentNote = 67;
                    Thread.sleep( duration );
                    channels.noteOff( 67 );
                    break;
                case "Mah":
                    channels.noteOn(68, volume );
                    currentNote = 68;
                    Thread.sleep( duration );
                    channels.noteOff( 68 );
                    break;
                case "Pa":
                    channels.noteOn(69, volume );
                    currentNote = 69;
                    Thread.sleep( duration );
                    channels.noteOff( 69 );
                    break;
                case "Dhal":
                    channels.noteOn(70, volume );
                    currentNote = 70;
                    Thread.sleep( duration );
                    channels.noteOff( 70 );
                    break;
                case "Dha":
                    channels.noteOn(71, volume );
                    currentNote = 71;
                    Thread.sleep( duration );
                    channels.noteOff( 71 );
                    break;
                case "Nil":
                    channels.noteOn(72, volume );
                    currentNote = 72;
                    Thread.sleep( duration );
                    channels.noteOff( 72 );
                    break;
                case "Ni":
                    channels.noteOn(73, volume );
                    currentNote = 73;
                    Thread.sleep( duration );
                    channels.noteOff( 73 );
                    break;
                case "SA":
                    channels.noteOn(74, volume );
                    currentNote = 74;
                    Thread.sleep( duration );
                    channels.noteOff( 74 );
                    break;
                case "Rehl":
                    channels.noteOn(75, volume );
                    currentNote = 75;
                    Thread.sleep( duration );
                    channels.noteOff( 75 );
                    break;
                case "Rehh":
                    channels.noteOn(76, volume );
                    currentNote = 76;
                    Thread.sleep( duration );
                    channels.noteOff( 76 );
                    break;
                case "Gahl":
                    channels.noteOn(77, volume );
                    currentNote = 77;
                    Thread.sleep( duration );
                    channels.noteOff( 77 );
                    break;
                case "Gahh":
                    channels.noteOn(78, volume );
                    currentNote = 78;
                    Thread.sleep( duration );
                    channels.noteOff( 78 );
                    break;
                case "Mahl":
                    channels.noteOn(79, volume );
                    currentNote = 79;
                    Thread.sleep( duration );
                    channels.noteOff( 79 );
                    break;
                case "Mahh":
                    channels.noteOn(80, volume );
                    currentNote = 80;
                    Thread.sleep( duration );
                    channels.noteOff( 80 );
                    break;
                case "Pah":
                    channels.noteOn(81, volume );
                    currentNote = 81;
                    Thread.sleep( duration );
                    channels.noteOff( 81 );
                    break;
                case ",":
                    channels.noteOn(currentNote, volume );
                    Thread.sleep( duration );
                    channels.noteOff( currentNote );
                    break;
                default:
                    break;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
