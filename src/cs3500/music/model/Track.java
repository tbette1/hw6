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
    public void addNote(Note n, List<Attribute> attributes, int startBeat) {
        int ind = notes.indexOf(n);
        if (ind == -1) {
            ArrayList<Attribute> toAddActions = new ArrayList<Attribute>();
            for (int i = 0; i < startBeat; i++) {
                toAddActions.add(Attribute.Rest);
            }
            for (int i = 0; i < attributes.size(); i++) {
                toAddActions.add(startBeat + i, attributes.get(i));
            }
            n.actions = toAddActions;
        }
        else {
            Note temp = notes.get(ind);
            int diff = startBeat - temp.getPlayLength();
            if (diff > 0) {
                for (int i = 0; i < diff; i++) {
                    temp.actions.add(Attribute.Rest);
                }
                for (int i = 0; i < attributes.size(); i++) {
                    temp.actions.add(attributes.get(i));
                }
            }
            else {
                for (int i = 0; i < attributes.size() - diff; i++) {
                    temp.actions.add(Attribute.Rest);
                }
                int i = startBeat;
                for (Attribute a : attributes) {
                    if (!a.equals(Attribute.Rest)) {
                        temp.actions.set(i, a);
                        i++;
                    }
                }
            }
        }
    }

    @Override
    public ArrayList<Note> getNotes() {
        return new ArrayList(this.notes);
    }

    @Override
    public void delete(Note n, ArrayList<Integer> beats) throws IllegalArgumentException {
        int ind = notes.indexOf(n);
        if (ind == -1) {
            throw new IllegalArgumentException("The given note is not contained in this track.");
        }
        else {
            for (int i : beats) {
                notes.get(ind).actions.set(i, Attribute.Rest);
            }
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
