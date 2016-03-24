package cs3500.music.model;
import java.util.ArrayList;


public class MusicPlayer {
    public Song track;

    /**
     * Constructs a new MusicPlayer object with no track.
     */
    public MusicPlayer() {
        track = new Track();
    }

    /**
     * Constructs a new MusicPlayer with a given track object.
     */
    public MusicPlayer(Song s) {
        this.track = new Track();
        addSong(s, 0);
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
        ArrayList<Attribute> toAdd = new ArrayList<>();
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
     * @param beats beats on which this note is playing that
     *              are to be deleted.
     */
    public void deleteNote(Note n, ArrayList<Integer> beats) {
        Note note = this.track.getNotes().get(this.track.getNotes().indexOf(n));
        track.delete(note, beats);
    }

    /**
     * gets the tempo of the song
     * @return: the tempo as an int
     */
    int getTempo(){
        return track.getTempo();
    }

    /**
     * returns the size of the musics notes
     */
    public int musicLength(){
        return this.track.getNotes().size();
    }

}