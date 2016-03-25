package cs3500.music.view;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import java.util.ArrayList;

/**
 * Created by Elijah on 3/24/2016.
 */
public class MockReciever implements Receiver {

    private StringBuilder log;
    private ArrayList<int []> on;
    private ArrayList<int []> off;
    private int tempo;
    private int currBeat;
    private int[] prev;

    /**
     * Creates an instance of MockReciever
     */
    public MockReciever(){
        this.log = new StringBuilder();
    }

    /**
     * Returns the log for testing purposes
     *
     * @returns the log activity within a reciever
     */

    public String getLog(){return this.log.toString();}


    /**
     * Sets the tempo for the receiver
     *
     * @param tempo tempo of the composition
     */
    public void setTempo(int tempo){
        this.tempo = tempo;
        log.append("tempo " + tempo + "\n");
    }

    public void setCurrentBeat(int currentBeat) {
        this.currBeat = currentBeat;
    }


    @Override
    public void send(MidiMessage message, long timeStamp) {
        ShortMessage t = ((ShortMessage) message);

        int[] info = new int[4];


        info[0] = (((int) timeStamp)/tempo) + currBeat;
        info[1] = t.getChannel() + 1;
        info[2] = t.getData1();
        info[3] = t.getData2();

        switch (t.getCommand()){
            case 144:
                this.prev = info;
                break;
            case 128:
                log.append("note " + prev[0] + " " + info[0] + " " + info[1] + " " + info[2] +
                        " " + info[3] + "\n");
                break;
            default:
                throw new IllegalStateException();
        }
    }


    @Override
    public void close() {

    }
}
