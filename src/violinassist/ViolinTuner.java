/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package violinassist;

/**
 *
 * @author adas
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ViolinTuner {
    private static enum Note {
        Pl(57), Dll (58), Dlh(59), Nll(60), Nlh(61), Sa(62), Rel(63), Re(64), Gal(65), Ga(66), Ma (67), Mah(68), Pa(69), Dhal(70), Dha(71), Nil(72), Ni(73), SA(74), Rehl(75), Rehh(76), Gahl(77), Gahh(78), Mahl(79), Mahh(80), Pah(81);

        public final int midiNoteNumber;

        Note(int midiNoteNumber) {
            this.midiNoteNumber = midiNoteNumber;
        }
    }

    private static final int violin = 41;

    private final MidiChannel channel;
    private final AbstractButton[] buttons = new AbstractButton[Note.values().length];

    public ViolinTuner() throws MidiUnavailableException {
        this.channel = initMidiChannel(violin);

        JFrame frame = new JFrame("Violin Tuner");

        // buttons to panel
        JPanel notePanel = new JPanel(new GridLayout(2, 0, 8, 4));
        notePanel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        int i = 0;
        for (Note note : Note.values()) {
            notePanel.add(buttons[i++] = makeButton(note));
        }

        // Component to impose a minimum width on the JFrame to ensure that the
        // window title is not truncated
        JPanel widener = new JPanel();
        widener.setPreferredSize(new Dimension(250, 0));

        // Panels to frame
        frame.setLayout(new BorderLayout());
        frame.add(notePanel, BorderLayout.CENTER);
        frame.add(widener, BorderLayout.SOUTH);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static MidiChannel initMidiChannel(int instrument) throws MidiUnavailableException {
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        MidiChannel channel = synth.getChannels()[0];
        // MIDI instruments are traditionally numbered from 1,
        // but the javax.midi API numbers them from 0
        channel.programChange(instrument - 1);
        channel.setChannelPressure(5);  // optional vibrato
        return channel;
    }

    private AbstractButton makeButton(final Note note) {
        AbstractButton button = new JToggleButton(note.name());
        button.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (((AbstractButton)e.getSource()).isSelected()) {
                    for (AbstractButton b : ViolinTuner.this.buttons) {
                        // Turn off any note that might be being played
                        if (b != e.getSource()) {
                            b.setSelected(false);
                        }
                    }
                    ViolinTuner.this.play(note);
                } else {
                    ViolinTuner.this.silence();
                }
            }
        });
        return button;
    }

    public void play(Note note) {
        this.channel.noteOn(note.midiNoteNumber, 80); 
    }

    public void silence() {
        this.channel.allNotesOff();
    }

    public static void main(String[] args) {
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
}