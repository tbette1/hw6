package cs3500.music.model;


public interface Song {


    /**
     * @return this Song's list of notes.
     */
    Collection<Note> getNotes();
    
    /**
     * Adds the given note to this song at the given beat for the
     * given duration.
     * @param n Note to add.
     * @param beat Beat on which to start playing the note.
     * @param duration how long to sustain the note.
     */
    void add(Note n, int beat, int duration);

    /**
     * Deletes the note playing at the given beat.
     * If the given note contains a Rest at that beat, does nothing.
     * If the given note contains a Sustain at that beat, deletes the
     * entirety of that played note (all Sustains linked to the selected
     * sustain as well as the Play at the head of the note.)
     * @param n
     * @param beat
     */
    void delete(Note n, int beat);

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
}
