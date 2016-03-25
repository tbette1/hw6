package cs3500.music.model;
import cs3500.music.util.NoteComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Track implements Song {
    private ArrayList <Note> notes;
    private int tempo;

    /**
     * Constructs a new Track object with the given set of notes.
     */
    public Track(ArrayList<Note> notes, int tempo) {
        this.notes = notes;
        this.tempo = tempo;

    }

    @Override
    public int getTempo() {
        return this.tempo;
    }

    @Override
    public void setTempo(int tempo) {
        this.tempo = tempo;
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
            notes.add(n);
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
        Collections.sort(this.notes, new NoteComparator());
    }

    @Override
    public ArrayList<Note> getNotes() {
        return this.notes;
    }

    @Override
    public ArrayList<Note> getAllNotesInRange() {
        ArrayList<Note> fullList = new ArrayList<Note>();
        if (this.notes.size() == 0) {
            return fullList;
        }

        for (int i = 0; i < this.notes.size() - 1; i++) {
            fullList.add(this.notes.get(i));
            int notesAway = new NoteComparator().compare(this.notes.get(i + 1), this.notes.get(i));
            Note last = this.notes.get(i);
            for (int j = 0; j < notesAway - 1; j++) {
                Note next = last.getHalfStepUp();
                fullList.add(next);
                last = next;
            }
        }
        fullList.add(this.notes.get(this.notes.size() - 1));
        return reverseList(fullList);
    }

    /**
     * @param notes list of notes.
     * @return a reversed copy of the given list.
     */
    private ArrayList<Note> reverseList(ArrayList<Note> notes) {
        ArrayList<Note> reversed = new ArrayList<Note>();
        for (Note n : notes) {
            reversed.add(0, n);
        }
        return reversed;
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
            for (int i = 1; i < n.actions.size(); i++) {
                if (n.actions.get(i).equals(Attribute.Sustain) &&
                        (n.actions.get(i - 1).equals(Attribute.Rest))) {
                    throw new IllegalArgumentException("Cannot have sustains that do not start with a play.");
                }
            }
            if (!n.plays()) {
                notes.remove(n);
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

}
