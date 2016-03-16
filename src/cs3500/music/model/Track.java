package cs3500.music.model;
import java.util.*;

public class Track implements Song {
    private List <Note> notes;

    /**
     * Constructs a new Track object with an empty set of notes.
     */
    public Track() {
        notes = new ArrayList<Note>();
    }


    @Override
    public void add(Note n, int beat, int duration) {
        int ind = notes.indexOf(n);
        if (ind == -1) {
            n.add(beat, duration);
            notes.add(n);
        }
        else {
            notes.get(ind).add(beat, duration);
        }
    }


    @Override
    public void delete(Note n, int beat) throws IllegalArgumentException {
        int ind = notes.indexOf(n);
        if (ind == -1) {
            throw new IllegalArgumentException("The given note is not contained in this track.");
        }
        else {
            notes.get(ind).delete(beat);
        }
    }

    @Override
    public int getLength() {
        int longest = 0;
        for (Note n : this.notes) {
            if (n.getPlayLength() > longest) {
                longest = n.getPlayLength();
            }
        }
        return longest;
    }

    @Override
    public Note firstNote() {
        int earliestBeat = 10000;
        Note first = null;
        for (Note n : this.notes) {
            if (n.getFirstPlay() < earliestBeat && n.getFirstPlay() > -1) {
                first = n;
                earliestBeat = n.getFirstPlay();
            }
        }
        return first;
    }

    @Override
    public Note lastNote() {
        int longest = 0;
        Note last = null;
        for (Note n : this.notes) {
            if (n.getPlayLength() > longest) {
                longest = n.getPlayLength();
                last = n;
            }
        }
        return last;
    }
}

