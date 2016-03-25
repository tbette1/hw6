package cs3500.music.view;

import cs3500.music.model.Attribute;
import cs3500.music.model.MusicPlayer;
import cs3500.music.model.Note;

import javax.sound.midi.*;
import java.util.Timer;
import java.util.TimerTask;


/**
 * view responsible for the playback of audio based on the musical composition.
 */
public final class MIDIView implements GUIView {
    //synthesizer that deals with the interpretation of the MIDI data
    private Synthesizer synth;

    //receiver recieves a and interperates it for raw sound
    private Receiver receiver;

    private MusicPlayer model;

    //tempo of the track
    private int tempo;

    // choose which constructor
    private boolean test;

    // the current beat;
    private int currentBeat;

    public MIDIView(MusicPlayer mp) throws MidiUnavailableException {
        this.model = mp;
        this.tempo = mp.getSong().getTempo();
        test = false;
        try {
            this.synth = MidiSystem.getSynthesizer();
            this.receiver = synth.getReceiver();
            this.synth.open();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public MIDIView(MusicPlayer mp, boolean test) {
        test = true;
        try {
            //make mock midi device
            this.synth = MidiSystem.getSynthesizer();
            this.receiver = new MockReciever();
            this.synth.open();
            this.model = mp;
            this.tempo = model.getSong().getTempo();

            ((MockReciever) this.receiver).setTempo(this.tempo);
            playSong(0);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public String returnLog(){
        return ((MockReciever) this.receiver).getLog();
    }


    public void display(){;
        playSong(0);
    }

    public void playSong(int startBeat){
        currentBeat = startBeat;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run(){
                try {
                    if (test){
                        ((MockReciever) receiver).setCurrentBeat(currentBeat);
                    }

                    playNotesAtBeat(currentBeat);
                    currentBeat++;

                    if (currentBeat >= model.getSong().getLength()){
                        close();
                        timer.cancel();
                        timer.purge();
                    }

                } catch (InvalidMidiDataException e) {
                    e.printStackTrace();
                }
            }
        }, 1, this.tempo / 1000);
    }

    public void playNotesAtBeat(int beat) throws InvalidMidiDataException{
        for (Note n: this.model.getSong().getNotes()){
            if (n.getActions().size() > beat) {
                if (n.getActions().get(beat).equals(Attribute.Play)) {
                    int pitch = (n.midiValue());
                    playNote(n.getInstrument(), n.noteDuration(beat), pitch, n.getVolume());
                }
            }
        }
    }


    public void playNote(int instrument, int length, int pitch, int volume)
            throws InvalidMidiDataException {
        MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, instrument - 1, pitch, volume);
        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, instrument - 1, pitch, volume);
        this.receiver.send(start, 0);
        this.receiver.send(stop,
                this.synth.getMicrosecondPosition() + (tempo * length));

    }

    public void close(){
        this.receiver.close();
    }

}



