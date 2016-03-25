package cs3500.music.view;

import cs3500.music.model.MusicPlayer;
import cs3500.music.util.MusicReader;
import cs3500.music.util.TrackBuilder;

import javax.sound.midi.MidiUnavailableException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by torybettencourt on 3/24/16.
 */
public class TestMidiRN {

    public static void main(String[] args) throws IOException{
        Readable read = new FileReader("mary-little-lamb.txt");
        MusicPlayer m = new MusicPlayer();
        m.addSong(MusicReader.parseFile(read, new TrackBuilder()), 0);
        try {
            MIDIView mv = new MIDIView(m);
            mv.display();
        }
        catch (MidiUnavailableException e) {
            System.out.println("midi unavailable");
        }
    }
}
