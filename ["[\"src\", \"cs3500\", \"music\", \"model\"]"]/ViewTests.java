package cs3500.music.view;

import cs3500.music.model.MusicPlayer;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.util.MusicReader;
import cs3500.music.util.TrackBuilder;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
public class ViewTests {

    public static void main(String[] args) throws IOException{
        Note c = new Note(Pitch.C, 4, 1, 0);
        Note g = new Note(Pitch.G, 4, 1, 0);
        Note a = new Note(Pitch.A, 4, 1, 0);
        MusicPlayer m  = new MusicPlayer();
        m.addNote(c, 2, 0);
        m.addNote(c, 2, 2);
        m.addNote(g, 2, 4);
        m.addNote(g, 2, 6);
        m.addNote(a, 2, 8);
        m.addNote(a, 2, 10);
        m.addNote(g, 4, 12);
        TextView t = new TextView(m);
        t.display();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBeatPanelLabelException() {
        BeatPanel bp = new BeatPanel(2);
        bp.setLabel(new Note(Pitch.C, 2, 0, 0));
    }

    @Test (expected = NullPointerException.class)
    public void testGraphicViewConstructException() {
        GraphicView g = new GraphicView(null);
    }


    @Test (expected = NullPointerException.class)
    public void testTextViewConstructException() {
        TextView t = new TextView(null);
    }

    @Test (expected = IllegalStateException.class)
    public void testTextViewLengthException() {
        TextView t = new TextView(new MusicPlayer());
        t.display();
    }

    @Test
    public void testTextViewMary() throws IOException {
        Readable read = new FileReader("mary-little-lamb.txt");
        MusicPlayer m = new MusicPlayer();
        m.addSong(MusicReader.parseFile(read, new TrackBuilder()), 0);
        TextView t = new TextView(m);
        String mary = "     G4  F#4   F4   E4  D#4   D4  C#4   C4   B3  A#3   A3  G#3   G3  F#3   F3   E3 \n" +
                "0                   X                                            X                 \n" +
                "1                   |                                            |                 \n" +
                "2                             X                                  |                 \n" +
                "3                             |                                  |                 \n" +
                "4                                       X                        |                 \n" +
                "5                                       |                        |                 \n" +
                "6                             X                                  |                 \n" +
                "7                             |                                                    \n" +
                "8                   X                                            X                 \n" +
                "9                   |                                            |                 \n" +
                "10                  X                                            |                 \n" +
                "11                  |                                            |                 \n" +
                "12                  X                                            |                 \n" +
                "13                  |                                            |                 \n" +
                "14                  |                                            |                 \n" +
                "15                                                                                 \n" +
                "16                            X                                  X                 \n" +
                "17                            |                                  |                 \n" +
                "18                            X                                  |                 \n" +
                "19                            |                                  |                 \n" +
                "20                            X                                  |                 \n" +
                "21                            |                                  |                 \n" +
                "22                            |                                  |                 \n" +
                "23                            |                                  |                 \n" +
                "24                  X                                            X                 \n" +
                "25                  |                                            |                 \n" +
                "26   X                                                                             \n" +
                "27   |                                                                             \n" +
                "28   X                                                                             \n" +
                "29   |                                                                             \n" +
                "30   |                                                                             \n" +
                "31   |                                                                             \n" +
                "32                  X                                            X                 \n" +
                "33                  |                                            |                 \n" +
                "34                            X                                  |                 \n" +
                "35                            |                                  |                 \n" +
                "36                                      X                        |                 \n" +
                "37                                      |                        |                 \n" +
                "38                            X                                  |                 \n" +
                "39                            |                                  |                 \n" +
                "40                  X                                            X                 \n" +
                "41                  |                                            |                 \n" +
                "42                  X                                            |                 \n" +
                "43                  |                                            |                 \n" +
                "44                  X                                            |                 \n" +
                "45                  |                                            |                 \n" +
                "46                  X                                            |                 \n" +
                "47                  |                                            |                 \n" +
                "48                            X                                  X                 \n" +
                "49                            |                                  |                 \n" +
                "50                            X                                  |                 \n" +
                "51                            |                                  |                 \n" +
                "52                  X                                            |                 \n" +
                "53                  |                                            |                 \n" +
                "54                            X                                  |                 \n" +
                "55                            |                                  |                 \n" +
                "56                                      X                                       X  \n" +
                "57                                      |                                       |  \n" +
                "58                                      |                                       |  \n" +
                "59                                      |                                       |  \n" +
                "60                                      |                                       |  \n" +
                "61                                      |                                       |  \n" +
                "62                                      |                                       |  \n" +
                "63                                      |                                       |  ";
        ArrayList<String> outArr = t.getLines();
        String output = "";
        for (String s : outArr) {
            output += s;
        }
        assertEquals(output, mary);
    }

    @Test
    public void testTextViewStarArr() {
        Note c = new Note(Pitch.C, 4, 1, 0);
        Note g = new Note(Pitch.G, 4, 1, 0);
        Note a = new Note(Pitch.A, 4, 1, 0);
        MusicPlayer m  = new MusicPlayer();
        m.addNote(c, 2, 0);
        m.addNote(c, 2, 2);
        m.addNote(g, 2, 4);
        m.addNote(g, 2, 6);
        m.addNote(a, 2, 8);
        m.addNote(a, 2, 10);
        m.addNote(g, 4, 12);
        TextView t = new TextView(m);

        assertEquals(t.getLines().size(), 17);
    }

    @Test
    public void testTextViewStarOutput() {
        Note c = new Note(Pitch.C, 4, 1, 0);
        Note g = new Note(Pitch.G, 4, 1, 0);
        Note a = new Note(Pitch.A, 4, 1, 0);
        MusicPlayer m  = new MusicPlayer();
        m.addNote(c, 2, 0);
        m.addNote(c, 2, 2);
        m.addNote(g, 2, 4);
        m.addNote(g, 2, 6);
        m.addNote(a, 2, 8);
        m.addNote(a, 2, 10);
        m.addNote(g, 4, 12);
        TextView t = new TextView(m);

        String twinkle = "     A4  G#4   G4  F#4   F4   E4  D#4   D4  C#4   C4 \n" +
                "0                                                 X  \n" +
                "1                                                 |  \n" +
                "2                                                 X  \n" +
                "3                                                 |  \n" +
                "4              X                                     \n" +
                "5              |                                     \n" +
                "6              X                                     \n" +
                "7              |                                     \n" +
                "8    X                                               \n" +
                "9    |                                               \n" +
                "10   X                                               \n" +
                "11   |                                               \n" +
                "12             X                                     \n" +
                "13             |                                     \n" +
                "14             |                                     \n" +
                "15             |                                     ";
        ArrayList<String> outArr = t.getLines();
        String out = "";
        for (String s : outArr) {
            out += s;
        }
        assertEquals(out, twinkle);
    }

    @Test
    public void testTextViewOneNote() {
        Note c = new Note(Pitch.C, 4, 1, 0);
        MusicPlayer m = new MusicPlayer();
        m.addNote(c, 4, 0);

        TextView t = new TextView(m);

        assertEquals(t.getLines().size(), 5);
        assertEquals(t.getLines().get(0).length(), 8);
        assertEquals(t.getLines().get(0).trim().length(), 2);
    }
}
