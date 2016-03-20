package cs3500.music.model;

import java.util.ArrayList;
public class MusicPlayer {
    Song track;

    /**
     * Constructs a new MusicPlayer object.
     */
    public MusicPlayer() {
        track = null;
    }


    /**
     * Adds the given song to this MusicPlayer to start at the
     * given beat. If another song exists at that beat, the song
     * to be added is layered over it.
     * @param s song to be added
     * @param beat beat at which to start adding this song
     */
    public void addSong(Song s, int beat) {
        ArrayList<Note> notesToAdd = s.getNotes();

        for (Note n : notesToAdd) {
            this.track.addNote(n, n.actions, beat);
        }
    }

    /**
     * @return this MusicPlayer's song.
     */
    public Song getSong() {
        return this.track;
    }

    /**
     * Adds the given single note to this MusicPlayer.
     * @param n Note to be added
     * @param duration duration of note
     * @param beat beat on which to start playing this note
     */
    public void addNote(Note n, int duration, int beat) {
        ArrayList<Attribute> toAdd = new ArrayList<Attribute>();
        toAdd.add(Attribute.Play);
        for (int i = 0; i < duration - 1; i++) {
            toAdd.add(Attribute.Sustain);
        }
        this.track.addNote(n, toAdd, beat);
    }

    /**
     * Deletes an instruction for this note.
     * If there is no instruction at this index,
     * throws an IllegalArgumentException.
     * @param beat beat on which this note is playing.
     *             NOTE: will delete all plays and sustains
     *             surrounding the given beat. For example,
     *             if the note begins on beat 10 and sustains until
     *             beat 16, selecting any beat between 10 and 16
     *             will delete the entire note, play and sustain.
     */
    public void deleteNote(Note n, int beat) {
        int start = -1;
        int end = -1;

        if (n.actions.get(beat).equals(Attribute.Play)) {
            start = beat;
        }
        else {
            int i = beat;
            while (n.actions.get(i).equals(Attribute.Sustain) && i >= 0) {
                i--;
            }
            start = i;
        }

        end = start;
        while (!n.actions.get(end).equals(Attribute.Rest) && end < n.actions.size()) {
            end++;
        }
        end -= 1;

        ArrayList<Integer> beatsToDelete = new ArrayList<Integer>();
        for (int i = start; i <= end; i++) {
            beatsToDelete.add(i);
        }

        this.track.delete(n, beatsToDelete);
    }
}
