package cs3500.music.model;


import java.util.ArrayList;
import org.junit.*;

public class TestMusicPlayer {
    
    public void testPitchGetVal() {
        assert(Pitch.C.getVal() == 0);
        assert(Pitch.CSharp.getVal() == 1);
        assert(Pitch.D.getVal() == 2);
        assert(Pitch.DSharp.getVal() == 3);
        assert(Pitch.E.getVal() == 4);
        assert(Pitch.F.getVal() == 5);
        assert(Pitch.FSharp.getVal() == 6);
        assert(Pitch.G.getVal() == 7);
        assert(Pitch.GSharp.getVal() == 8);
        assert(Pitch.A.getVal() == 9);
        assert(Pitch.ASharp.getVal() == 10);
        assert(Pitch.B.getVal() == 11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParsePitchExceptionLetter() {
        Pitch.parsePitch("Y");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParsePitchExceptionSymbol() {
        Pitch.parsePitch("A&");
    }

    @Test
    public void testParsePitch() {
        assert(Pitch.parsePitch("C") == Pitch.C);
        assert(Pitch.parsePitch("C#") == Pitch.CSharp);
        assert(Pitch.parsePitch("D") == Pitch.D);
        assert(Pitch.parsePitch("D#") == Pitch.DSharp);
        assert(Pitch.parsePitch("E") == Pitch.E);
        assert(Pitch.parsePitch("F") == Pitch.F);
        assert(Pitch.parsePitch("F#") == Pitch.FSharp);
        assert(Pitch.parsePitch("G") == Pitch.G);
        assert(Pitch.parsePitch("G#") == Pitch.GSharp);
        assert(Pitch.parsePitch("A") == Pitch.A);
        assert(Pitch.parsePitch("A#") == Pitch.ASharp);
        assert(Pitch.parsePitch("B") == Pitch.B);
    }

    @Test
    public void testPitchToString() {
        assert(Pitch.C.toString() == "C");
        assert(Pitch.CSharp.toString() == "C#");
        assert(Pitch.D.toString() == "D");
        assert(Pitch.DSharp.toString() == "D#");
        assert(Pitch.E.toString() == "E");
        assert(Pitch.F.toString() == "F");
        assert(Pitch.FSharp.toString() == "F#");
        assert(Pitch.G.toString() == "G");
        assert(Pitch.GSharp.toString() == "G#");
        assert(Pitch.A.toString() == "A");
        assert(Pitch.ASharp.toString() == "A#");
        assert(Pitch.B.toString() == "B");
    }

    @Test
    public void testPitchHalfStepUp() {
        assert(Pitch.C.halfStepUp() == Pitch.CSharp);
        assert(Pitch.CSharp.halfStepUp() == Pitch.D);
        assert(Pitch.D.halfStepUp() == Pitch.DSharp);
        assert(Pitch.DSharp.halfStepUp() == Pitch.E);
        assert(Pitch.E.halfStepUp() == Pitch.F);
        assert(Pitch.F.halfStepUp() == Pitch.FSharp);
        assert(Pitch.FSharp.halfStepUp() == Pitch.G);
        assert(Pitch.G.halfStepUp() == Pitch.GSharp);
        assert(Pitch.GSharp.halfStepUp() == Pitch.A);
        assert(Pitch.A.halfStepUp() == Pitch.ASharp);
        assert(Pitch.ASharp.halfStepUp() == Pitch.B);
        assert(Pitch.B.halfStepUp() == Pitch.C);
    }

    @Test
    public void testNoteActionToString() {
        Attribute p = Attribute.Play;
        Attribute r = Attribute.Rest;
        Attribute s = Attribute.Sustain;

        assert(p.toString() == "  X  ");
        assert(r.toString() == "     ");
        assert(s.toString() == "  |  ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoteConstructLowOctave() {
        Note n = new Note(Pitch.C, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoteConstructHighOctave() {
        Note n = new Note(Pitch.CSharp, 10);
    }

    @Test
    public void testNoteConstructActionInitialization() {
        Note n = new Note(Pitch.D, 3);
        assertNotNull(n.actions);
        assert(n.actions.size() == 32);
    }



    @Test
    public void testNoteEquals() {
        Note c4 = new Note(Pitch.C, 4);
        Note cSharp4 = new Note(Pitch.CSharp, 4);
        Note c3 = new Note(Pitch.C, 3);
        Note c5 = new Note(Pitch.C, 5);

        //assert(c4.equals(c4), true);
        assert(c4.equals(cSharp4) == false);
        assert(cSharp4.equals(c4) == false);
        assert(c4.equals(c3) == false);
        assert(c3.equals(c4) == false);
        assert(c4.equals(c5) == false);
        assert(c5.equals(c4) == false);
    }

    @Test
    public void testNoteToString() {
        Note c1 = new Note(Pitch.C, 1);
        Note d2 = new Note(Pitch.D, 2);
        Note cSharp4 = new Note(Pitch.CSharp, 4);
        Note dSharp3 = new Note(Pitch.DSharp, 3);

        assert(c1.toString() == "  C1 ");
        assert(d2.toString() == "  D2 ");
        assert(cSharp4.toString() == " C#4 ");
        assert(dSharp3.toString() == " D#3 ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseNoteException1() {
        Note.parseNote("X");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseNoteException2() {
        Note.parseNote("XX");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseNoteException3() {
        Note.parseNote("E#7");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseNoteException4() {
        Note.parseNote("H5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseNoteException5() {
        Note.parseNote("F#T");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseNoteException6() {
        Note.parseNote("G#10");
    }

    @Test
    public void testParseNote() {
        Note cSharp4Note = new Note(Pitch.CSharp, 4);
        Note d5Note = new Note(Pitch.D, 5);
        Note fSharp7Note = new Note(Pitch.FSharp, 7);

        String cSharp4 = "C#4";
        String d5 = "D5";
        String fSharp7 = "F#7";

        assert(Note.parseNote(cSharp4) == cSharp4Note);
        assert(Note.parseNote(d5) == d5Note);
        assert(Note.parseNote(fSharp7) == fSharp7Note);
    }

    /**
     * @param n Note to calculate plays.
     * @return the indices as a comma-separated list
     * where this note plays.
     */
    private String playsAt(Note n) {
        ArrayList<Integer> plays = new ArrayList<Integer>();
        for (int i = 0; i < n.actions.size(); i++) {
            if (n.actions.get(i).equals(Attribute.Play)) {
                plays.add(i);
            }
        }
        String out = plays.get(0) + "";
        for (int i = 1; i < plays.size(); i++) {
            out += ", " + plays.get(i);
        }
        return out;
    }

    @Test
    public void testNoteAdd() {
        Note n = new Note(Pitch.C, 4);
        n.add(2, 2);
        n.add(4, 4);
        n.add(10, 2);

        assert(playsAt(n) == "2, 4, 10");

        n.add(14, 1);
        assert(playsAt(n) == "2, 4, 10, 14");

        n.add(13, 1);
        assert(playsAt(n) == "2, 4, 10, 13, 14");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoteDeleteException() {
        Note n = new Note(Pitch.C, 4);
        n.delete(2);
    }

    @Test
    public void testNoteDelete() {
        Note n = new Note(Pitch.C, 4);
        n.add(2, 2);
        n.add(4, 4);
        n.add(10, 2);
        n.add(14, 1);
        n.add(13, 1);
        assert(playsAt(n) == "2, 4, 10, 13, 14");

        n.delete(13);
        assert(playsAt(n) == "2, 4, 10, 14");

        n.delete(10);
        assert(playsAt(n) == "2, 4, 14");

        n.add(10, 2);
        n.delete(11);
        assert(playsAt(n) == "2, 4, 14");
    }

    @Test
    public void testNotePlays() {
        Note n = new Note(Pitch.C, 4);
        assert(n.plays() == false);
        n.add(0, 4);
        assert(n.plays() == true);
        n.delete(2);
        assert(n.plays() == false);
    }

    @Test
    public void testNoteGetPlayLength() {
        Note n = new Note(Pitch.C, 4);
        assert(n.getPlayLength() == 0);

        n.add(10, 4);
        assert(n.getPlayLength() == 14);

        n.add(2, 1);
        assert(n.getPlayLength() == 14);
    }
    

}
