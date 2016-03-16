package cs3500.music.model;

public class MusicPlayer {
    Song track;
    static int beat;

    /**
     * Constructs a new MusicPlayer object.
     */
    public MusicPlayer() {
        track = null;
        beat = -1;
    }

    /**
     * Returns true if the playing song is over, returns
     * false otherwise.
     * throws IllegalStateException if called while the song is not playing.
     */
    public boolean isSongOver() throws IllegalStateException {
        if (beat == -1) {
            throw new IllegalStateException("Song has not started playing");
        }
        else {
            return beat >= track.getLength();
        }
    }

    /**
     * Adds the given song to this MusicPlayer to start at the
     * given beat. If another song exists at that beat, the song
     * to be added is layered over it.
     */
    public void add(Song s, int beat) {

    }

    /**
     * Starts play for this MusicPlayer's song.
     */
    public void play() {

    }


    /**
     * Moves this MusicPlayers metrinomic beat one beat forward.
     */
    public void tick() {
        beat ++;
    }
}
