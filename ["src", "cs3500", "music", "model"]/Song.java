package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;
public interface Song {


    /**
     * Adds all Attributes of the given note to the corresponding note in this song.
     * @param n Note to be added.
     * @param actions Attributes of the note.
     * @param startBeat beat on which to start adding.
     */
    void addNote(Note n, List<Attribute> actions, int startBeat);

    /**
     * Deletes the note playing at the given beat.
     * If the given note contains a Rest at that beat, does nothing.
     * If the given note contains a Sustain at that beat, deletes the
     * entirety of that played note (all Sustains linked to the selected
     * sustain as well as the Play at the head of the note.)
     * @param n note to delete plays from
     * @param beats specific beats to delete plays or sustains from. NOTE:
     *              If any one of the beats contains a rest (it is not playing
     *              there to begin with), nothing will happen, it will simply
     *              remain a rest.
     */
    void delete(Note n, ArrayList<Integer> beats);

    /**
     * @return the length of this song in beats played.
     */
    int getLength();

    /**
     * @return the note played first in this Song.
     */
    Note firstNote();

    /**
     * @return the note played last in this Song.
     */
    Note lastNote();

    /**
     * gets the tempo from a track
     * @return
     */
    int getTempo();

    /**
     * @return an ArrayList of all notes played in this song.
     */
    ArrayList<Note> getNotes();

    /**
     * @return an ArrayList of all notes in the range of this song.
     */
    ArrayList<Note> getAllNotesInRange();

    /**
     * @return an ArrayList of all notes in the given ArrayList
     * converted to String representations.
     */
    ArrayList<String> getNoteNames();

    /**
     * returns the size of the musics notes
     */
    public int musicLength();

}
