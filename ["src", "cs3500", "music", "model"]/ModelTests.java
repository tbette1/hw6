package cs3500.music.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ModelTests {

    @Test
    public void testPitchGetVal() {
        assertEquals(Pitch.C.getVal(), 0);
        assertEquals(Pitch.CSharp.getVal(), 1);
        assertEquals(Pitch.D.getVal(), 2);
        assertEquals(Pitch.DSharp.getVal(), 3);
        assertEquals(Pitch.E.getVal(), 4);
        assertEquals(Pitch.F.getVal(), 5);
        assertEquals(Pitch.FSharp.getVal(), 6);
        assertEquals(Pitch.G.getVal(), 7);
        assertEquals(Pitch.GSharp.getVal(), 8);
        assertEquals(Pitch.A.getVal(), 9);
        assertEquals(Pitch.ASharp.getVal(), 10);
        assertEquals(Pitch.B.getVal(), 11);
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
        assertEquals(Pitch.parsePitch("C"), Pitch.C);
        assertEquals(Pitch.parsePitch("C#"), Pitch.CSharp);
        assertEquals(Pitch.parsePitch("D"), Pitch.D);
        assertEquals(Pitch.parsePitch("D#"), Pitch.DSharp);
        assertEquals(Pitch.parsePitch("E"), Pitch.E);
        assertEquals(Pitch.parsePitch("F"), Pitch.F);
        assertEquals(Pitch.parsePitch("F#"), Pitch.FSharp);
        assertEquals(Pitch.parsePitch("G"), Pitch.G);
        assertEquals(Pitch.parsePitch("G#"), Pitch.GSharp);
        assertEquals(Pitch.parsePitch("A"), Pitch.A);
        assertEquals(Pitch.parsePitch("A#"), Pitch.ASharp);
        assertEquals(Pitch.parsePitch("B"), Pitch.B);
    }

    @Test
    public void testPitchToString() {
        assertEquals(Pitch.C.toString(), "C");
        assertEquals(Pitch.CSharp.toString(), "C#");
        assertEquals(Pitch.D.toString(), "D");
        assertEquals(Pitch.DSharp.toString(), "D#");
        assertEquals(Pitch.E.toString(), "E");
        assertEquals(Pitch.F.toString(), "F");
        assertEquals(Pitch.FSharp.toString(), "F#");
        assertEquals(Pitch.G.toString(), "G");
        assertEquals(Pitch.GSharp.toString(), "G#");
        assertEquals(Pitch.A.toString(), "A");
        assertEquals(Pitch.ASharp.toString(), "A#");
        assertEquals(Pitch.B.toString(), "B");
    }

    @Test
    public void testPitchHalfStepUp() {
        assertEquals(Pitch.C.halfStepUp(), Pitch.CSharp);
        assertEquals(Pitch.CSharp.halfStepUp(), Pitch.D);
        assertEquals(Pitch.D.halfStepUp(), Pitch.DSharp);
        assertEquals(Pitch.DSharp.halfStepUp(), Pitch.E);
        assertEquals(Pitch.E.halfStepUp(), Pitch.F);
        assertEquals(Pitch.F.halfStepUp(), Pitch.FSharp);
        assertEquals(Pitch.FSharp.halfStepUp(), Pitch.G);
        assertEquals(Pitch.G.halfStepUp(), Pitch.GSharp);
        assertEquals(Pitch.GSharp.halfStepUp(), Pitch.A);
        assertEquals(Pitch.A.halfStepUp(), Pitch.ASharp);
        assertEquals(Pitch.ASharp.halfStepUp(), Pitch.B);
        assertEquals(Pitch.B.halfStepUp(), Pitch.C);
    }

    @Test
    public void testPitchFromVal() {
        assertEquals(Pitch.pitchFromVal(0), Pitch.C);
        assertEquals(Pitch.pitchFromVal(1), Pitch.CSharp);
        assertEquals(Pitch.pitchFromVal(2), Pitch.D);
        assertEquals(Pitch.pitchFromVal(3), Pitch.DSharp);
        assertEquals(Pitch.pitchFromVal(4), Pitch.E);
        assertEquals(Pitch.pitchFromVal(5), Pitch.F);
        assertEquals(Pitch.pitchFromVal(6), Pitch.FSharp);
        assertEquals(Pitch.pitchFromVal(7), Pitch.G);
        assertEquals(Pitch.pitchFromVal(8), Pitch.GSharp);
        assertEquals(Pitch.pitchFromVal(9), Pitch.A);
        assertEquals(Pitch.pitchFromVal(10), Pitch.ASharp);
        assertEquals(Pitch.pitchFromVal(11), Pitch.B);
    }


    @Test
    public void testAttributeToString() {
        Attribute p = Attribute.Play;
        Attribute r = Attribute.Rest;
        Attribute s = Attribute.Sustain;

        assertEquals(p.toString(), "  X  ");
        assertEquals(r.toString(), "     ");
        assertEquals(s.toString(), "  |  ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoteConstructLowOctave() {
        Note n = new Note(Pitch.C, -1, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoteConstructHighOctave() {
        Note n = new Note(Pitch.CSharp, 10, 0, 0);
    }

    @Test
    public void testNoteConstructActionInitialization() {
        Note n = new Note(Pitch.D, 3, 0, 0);
        assertNotNull(n.actions);
        assertEquals(n.actions.size(), 32);
    }

    @Test
    public void testNoteEquals() {
        Note c4 = new Note(Pitch.C, 4, 0, 0);
        Note cSharp4 = new Note(Pitch.CSharp, 4, 0, 0);
        Note c3 = new Note(Pitch.C, 3, 0, 0);
        Note c5 = new Note(Pitch.C, 5, 0, 0);

        assertEquals(c4.equals(cSharp4), false);
        assertEquals(cSharp4.equals(c4), false);
        assertEquals(c4.equals(c3), false);
        assertEquals(c3.equals(c4), false);
        assertEquals(c4.equals(c5), false);
        assertEquals(c5.equals(c4), false);

        Note c41 = new Note(Pitch.C, 4, 1, 0);
        Note c42 = new Note(Pitch.C, 4, 2, 0);
        Note c43 = new Note(Pitch.C, 4, 1, 0);
        c43.actions = null;

        assertEquals(c41.equals(c42), false);
        assertEquals(c42.equals(c43), false);
        assertEquals(c41.equals(c43), true);
    }

    @Test
    public void testNoteToString() {
        Note c1 = new Note(Pitch.C, 1, 0, 0);
        Note d2 = new Note(Pitch.D, 2, 0, 0);
        Note cSharp4 = new Note(Pitch.CSharp, 4, 0, 0);
        Note dSharp3 = new Note(Pitch.DSharp, 3, 0, 0);

        assertEquals(c1.toString(), "  C1 ");
        assertEquals(d2.toString(), "  D2 ");
        assertEquals(cSharp4.toString(), " C#4 ");
        assertEquals(dSharp3.toString(), " D#3 ");
    }

    @Test
    public void testParseNoteFromInt() {
        Note c4 = new Note(Pitch.C, 4, 2, 2);
        Note d4 = new Note(Pitch.D, 4, 2, 3);

        assertEquals(Note.parseNoteFromInt(60, 2, 2), c4);
        assertEquals(Note.parseNoteFromInt(62, 2, 3), d4);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testParseNoteExceptionTooLow() {
        Note.parseNoteFromInt(-1, 0, 0);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testParseNoteExceptionTooHigh() {
        Note.parseNoteFromInt(128, 0, 0);
    }

    @Test
    public void testNoteGetPlayLength() {
        Note c2 = new Note(Pitch.C, 2, 0, 0);
        ArrayList<Attribute> list1 = new ArrayList<Attribute>(Arrays.asList(Attribute.Play));
        ArrayList<Attribute> list2 = new ArrayList<Attribute>(Arrays.asList(Attribute.Play, Attribute.Sustain));
        ArrayList<Attribute> list3 = new ArrayList<Attribute>(Arrays.asList(Attribute.Rest,
                                                    Attribute.Rest, Attribute.Play));
        assertEquals(c2.getPlayLength(), 0);

        c2.actions = list1;
        assertEquals(c2.getPlayLength(), 1);

        c2.actions = list2;
        assertEquals(c2.getPlayLength(), 2);

        c2.actions = list3;
        assertEquals(c2.getPlayLength(), 3);
    }

    @Test
    public void testNoteGetHalfStepUp() {
        Note e3 = new Note(Pitch.E, 3, 2, 2);
        Note f3 = new Note(Pitch.F, 3, 2, 2);
        Note fSharp3 = new Note(Pitch.FSharp, 3, 2, 2);

        assertEquals(e3.getHalfStepUp(), f3);
        assertEquals(f3.getHalfStepUp(), fSharp3);
    }


    @Test
    public void testTrackAddNote() {
        Track t = new Track();
        Note midC = new Note(Pitch.C, 4, 0, 0);
        assertEquals(midC.plays(), false);
        ArrayList<Attribute> toPlay = new ArrayList<Attribute>(Arrays.asList(Attribute.Play, Attribute.Sustain,
                Attribute.Sustain, Attribute.Rest));
        t.addNote(midC, toPlay, 2);

        ArrayList<Attribute> isPlaying = new ArrayList<Attribute>(Arrays.asList(Attribute.Rest, Attribute.Rest,
                Attribute.Play, Attribute.Sustain, Attribute.Sustain, Attribute.Rest));

        assertEquals(t.getNotes().size(), 1);
        assertEquals(t.getNotes().contains(midC), true);
        assertEquals(midC.getActions(), isPlaying);


        Note cSharp = new Note(Pitch.CSharp, 4, 0, 0);
        t.addNote(cSharp, toPlay, 7);
        assertEquals(t.getNotes().size(), 2);
        assertEquals(t.getNotes().contains(midC), true);
        assertEquals(t.getNotes().contains(cSharp), true);
    }

    @Test
    public void testTrackGetFullRange() {
        Note midC = new Note(Pitch.C, 4, 0, 0);
        Note cSharp4 = new Note(Pitch.CSharp, 4, 0, 0);
        Note d4 = new Note(Pitch.D, 4, 0, 0);
        Note dSharp4 = new Note(Pitch.DSharp, 4, 0, 0);
        Note e4 = new Note(Pitch.E, 4, 0, 0);

        ArrayList<Note> range = new ArrayList<Note>();

        Track t = new Track();
        t.addNote(midC, new ArrayList<Attribute>(), 1);
        range.add(midC);

        assertEquals(t.getAllNotesInRange().size(), 1);
        assertEquals(t.getAllNotesInRange().contains(midC), true);

        t.addNote(e4, new ArrayList<Attribute>(), 1);
        range.add(cSharp4);
        range.add(d4);
        range.add(dSharp4);
        range.add(e4);

        assertEquals(t.getAllNotesInRange().size(), 5);
        assertEquals(t.getAllNotesInRange(), range);
    }

    @Test
    public void testTrackGetNoteNames() {
        Note midC = new Note(Pitch.C, 4, 0, 0);
        Note e4 = new Note(Pitch.E, 4, 0, 0);

        ArrayList<String> range = new ArrayList<String>();

        Track t = new Track();
        t.addNote(midC, new ArrayList<Attribute>(), 1);
        range.add("  C4 ");

        assertEquals(t.getNoteNames().size(), 1);
        assertEquals(t.getNoteNames(), range);

        t.addNote(e4, new ArrayList<Attribute>(), 1);
        range.add(" C#4 ");
        range.add("  D4 ");
        range.add(" D#4 ");
        range.add("  E4 ");

        assertEquals(t.getNoteNames().size(), 5);
        assertEquals(t.getNoteNames(), range);
    }

    @Test
    public void testTrackMusicLength() {
        Track t = new Track();
        assertEquals(t.musicLength(), 0);

        t.addNote(new Note(Pitch.C, 4, 0, 0), new ArrayList<Attribute>(Arrays.asList(Attribute.Play)), 0);
        assertEquals(t.musicLength(), 1);

        t.addNote(new Note(Pitch.C, 4, 0, 0), new ArrayList<Attribute>(), 0);
        assertEquals(t.musicLength(), 1);

        t.addNote(new Note(Pitch.D, 4, 0, 0), new ArrayList<Attribute>(), 0);
        assertEquals(t.musicLength(), 2);
    }

    @Test
    public void testTrackDelete() {
        Track t = new Track();
        Note midC = new Note(Pitch.C, 4, 0, 0);
        assertEquals(midC.plays(), false);
        ArrayList<Attribute> toPlay = new ArrayList<Attribute>(Arrays.asList(Attribute.Play, Attribute.Sustain,
                Attribute.Sustain, Attribute.Rest, Attribute.Play));
        t.addNote(midC, toPlay, 2);

        Note cSharp = new Note(Pitch.CSharp, 4, 0, 0);
        t.addNote(cSharp, new ArrayList<Attribute>(Arrays.asList(Attribute.Play)), 7);

        t.delete(cSharp, new ArrayList<Integer>(Arrays.asList(7)));
        assertEquals(cSharp.plays(), false);

        t.delete(midC, new ArrayList<Integer>(Arrays.asList(2, 3, 4, 6)));
        assertEquals(midC.plays(), false);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testTrackDeleteException1() {
        Track t = new Track();
        Note midC = new Note(Pitch.C, 4, 0, 0);
        assertEquals(midC.plays(), false);
        ArrayList<Attribute> toPlay = new ArrayList<Attribute>(Arrays.asList(Attribute.Play, Attribute.Sustain,
                Attribute.Sustain, Attribute.Rest, Attribute.Play));
        t.addNote(midC, toPlay, 2);

        Note cSharp = new Note(Pitch.CSharp, 4, 0, 0);
        t.addNote(cSharp, new ArrayList<Attribute>(Arrays.asList(Attribute.Play)), 7);

        t.delete(new Note(Pitch.D, 2, 0, 0), new ArrayList<Integer>());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testTrackDeleteException2() {
        Track t = new Track();
        t.delete(new Note(Pitch.C, 4, 0, 0), new ArrayList<Integer>());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testTrackDeleteException3() {
        Track t = new Track();
        Note midC = new Note(Pitch.C, 4, 0, 0);
        assertEquals(midC.plays(), false);
        ArrayList<Attribute> toPlay = new ArrayList<Attribute>(Arrays.asList(Attribute.Play, Attribute.Sustain,
                Attribute.Sustain, Attribute.Rest, Attribute.Play));
        t.addNote(midC, toPlay, 2);

        t.delete(midC, new ArrayList<Integer>(Arrays.asList(2)));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testTrackDeleteException4() {
        Track t = new Track();
        Note midC = new Note(Pitch.C, 4, 0, 0);
        assertEquals(midC.plays(), false);
        ArrayList<Attribute> toPlay = new ArrayList<Attribute>(Arrays.asList(Attribute.Play, Attribute.Sustain,
                Attribute.Sustain, Attribute.Rest, Attribute.Play));
        t.addNote(midC, toPlay, 2);

        t.delete(midC, new ArrayList<Integer>(Arrays.asList(3)));
    }

    @Test
    public void testTrackGetLength() {
        Track t = new Track();
        Note midC = new Note(Pitch.C, 4, 0, 0);
        assertEquals(midC.plays(), false);
        ArrayList<Attribute> toPlay = new ArrayList<Attribute>(Arrays.asList(Attribute.Play, Attribute.Sustain,
                Attribute.Sustain, Attribute.Rest, Attribute.Play));
        t.addNote(midC, toPlay, 2);

        Note cSharp = new Note(Pitch.CSharp, 4, 0, 0);
        t.addNote(cSharp, new ArrayList<Attribute>(Arrays.asList(Attribute.Play)), 7);

        assertEquals(t.getLength(), 8);
        t.delete(cSharp, new ArrayList<Integer>(Arrays.asList(7)));
        assertEquals(t.getLength(), 7);

        Track emptyT = new Track();
        assertEquals(emptyT.getLength(), 0);
    }

    @Test
    public void testTrackFirstNote() {
        Track t = new Track();
        Note midC = new Note(Pitch.C, 4, 0, 0);
        assertEquals(midC.plays(), false);
        ArrayList<Attribute> toPlay = new ArrayList<Attribute>(Arrays.asList(Attribute.Play, Attribute.Sustain,
                Attribute.Sustain, Attribute.Rest, Attribute.Play));
        t.addNote(midC, toPlay, 2);

        Note cSharp = new Note(Pitch.CSharp, 4, 0, 0);
        t.addNote(cSharp, new ArrayList<Attribute>(Arrays.asList(Attribute.Play)), 7);

        assertEquals(t.firstNote(), midC);

        Note lowA = new Note(Pitch.A, 2, 0, 0);
        t.addNote(lowA, new ArrayList<Attribute>(Arrays.asList(Attribute.Play)), 2);
        assertEquals(t.firstNote(), lowA);
    }

    @Test
    public void testTrackLastNote() {
        Track t = new Track();
        Note midC = new Note(Pitch.C, 4, 0, 0);
        assertEquals(midC.plays(), false);
        ArrayList<Attribute> toPlay = new ArrayList<Attribute>(Arrays.asList(Attribute.Play, Attribute.Sustain,
                Attribute.Sustain, Attribute.Rest, Attribute.Play));
        t.addNote(midC, toPlay, 2);

        Note cSharp = new Note(Pitch.CSharp, 4, 0, 0);
        t.addNote(cSharp, new ArrayList<Attribute>(Arrays.asList(Attribute.Play)), 7);

        assertEquals(t.lastNote(), cSharp);

        Note lowA = new Note(Pitch.A, 2, 0, 0);
        t.addNote(lowA, new ArrayList<Attribute>(Arrays.asList(Attribute.Play)), 2);
        assertEquals(t.lastNote(), cSharp);

        t.delete(cSharp, new ArrayList<Integer>(Arrays.asList(7)));
        assertEquals(t.lastNote(), midC);
    }

    @Test
    public void testMusicPlayerAddSong() {
        Track t = new Track();
        Note midC = new Note(Pitch.C, 4, 0, 0);
        assertEquals(midC.plays(), false);
        ArrayList<Attribute> toPlay = new ArrayList<Attribute>(Arrays.asList(Attribute.Play, Attribute.Sustain,
                Attribute.Sustain, Attribute.Rest, Attribute.Play));
        t.addNote(midC, toPlay, 2);

        Note cSharp = new Note(Pitch.CSharp, 4, 0, 0);
        t.addNote(cSharp, new ArrayList<Attribute>(Arrays.asList(Attribute.Play)), 7);

        Note lowA = new Note(Pitch.A, 2, 0, 0);
        t.addNote(lowA, new ArrayList<Attribute>(Arrays.asList(Attribute.Play)), 2);

        MusicPlayer m = new MusicPlayer();
        m.addSong(t, 0);
        assertEquals(m.getSong().getNotes(), t.getNotes());
        for (Note n : m.getSong().getNotes()) {
            assertEquals(n.actions, (t.getNotes().get(t.getNotes().indexOf(n)).actions));
        }
    }

    @Test
    public void testMusicPlayerAddNote() {
        MusicPlayer m = new MusicPlayer();
        Note midC = new Note(Pitch.C, 4, 0, 0);
        Note e4 = new Note(Pitch.E, 4, 0, 0);
        m.addNote(midC, 4, 0);

        assertEquals(m.getSong().getNotes().size(), 1);
        assertEquals(m.getSong().getNotes().contains(midC), true);
        assertEquals(m.getSong().getLength(), 4);

        m.addNote(midC, 4, 4);
        assertEquals(m.getSong().getNotes().size(), 1);
        assertEquals(m.getSong().getNotes().contains(midC), true);
        assertEquals(m.getSong().getLength(), 8);

        m.addNote(e4, 4, 2);
        assertEquals(m.getSong().getNotes().size(), 2);
        assertEquals(m.getSong().getNotes().contains(midC), true);
        assertEquals(m.getSong().getNotes().contains(e4), true);
        assertEquals(m.getSong().getLength(), 8);
    }

    @Test
    public void testMusicPlayerDeleteNote() {
        MusicPlayer m = new MusicPlayer();
        Note midC = new Note(Pitch.C, 4, 0, 0);
        Note e4 = new Note(Pitch.E, 4, 0, 0);
        m.addNote(midC, 4, 0);
        m.addNote(midC, 4, 4);
        m.addNote(e4, 4, 2);

        m.deleteNote(midC, new ArrayList<Integer>(Arrays.asList(4, 5, 6, 7)));
        assertEquals(m.getSong().getLength(), 6);
        assertEquals(m.getSong().getNotes().contains(midC), true);
        assertEquals(m.getSong().getNotes().contains(e4), true);
    }

    @Test
    public void testMusicBuilder() {

    }
}




