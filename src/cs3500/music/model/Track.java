package cs3500.music.model;
import java.util.*;

public class Track implements Song {
    Set<Note> notes;

    @Override
    public void add(Note n, int beat, int duration) {

    }


    @Override
    public void delete(Note n, int beat) {

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
