package cs3500.music.model;
import java.util.ArrayList;
import java.util.List;

public class Track implements Song {
    private ArrayList <Note> notes;
    private int tempo;

    public Track() {
        this.notes = new ArrayList<>();
        this.tempo = 1;

    }
//
//            /**
//             * Constructs a new builder object.
//             */
//
//            public Builder() {
//                buildNotes = new ArrayList<Note>();
//            }
//
//            /**
//             * Adds a new note to this composition builder.
//             * @return this builder object.
//             */
//            public Builder addNote(int start, int end, int instrument, int pitch, int volume) {
//                Note n = Note.parseNoteFromInt(pitch, instrument, volume);
//                if (!buildNotes.contains(n)) {
//                    buildNotes.add(n);
//                }
//                Note toChange = buildNotes.get(buildNotes.indexOf(n));
//                toChange.actions.set(start, Attribute.Play);
//                for (int i = start + 1; i <= end; i++) {
//                    toChange.actions.set(i, Attribute.Sustain);
//                }
//
//                return this;
//            }
//
//            /**
//             * Sets the tempo for this composition builder.
//             * @return the builder object
//             */
//            public Builder setTempo(int tempo) {
//                this.tempo = tempo;
//                return this;
//            }
//
//            /**
//             * @return a new Track object with all of the notes added to this builder.
//             */
//            public Track build() {
//                return new Track(buildNotes, this.tempo);
//            }



    /**
     * Constructs a new Track object with the given set of notes.
     */
    public Track(ArrayList<Note> notes, int tempo) {
        this.notes = notes;
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
    }

    @Override
    public ArrayList<Note> getNotes() {
        return new ArrayList(this.notes);
    }

    @Override
    public ArrayList<Note> getAllNotesInRange() {
        ArrayList<Note> fullList = new ArrayList<Note>();
        if (this.notes.size() == 0) {
            return fullList;
        }
        fullList.add(this.firstNote());
        while (!fullList.get(fullList.size() - 1).equals(this.lastNote())) {
            Note n = fullList.get(fullList.size() - 1).getHalfStepUp();
            if (this.notes.contains(n)) {
                fullList.add(this.notes.get(this.notes.indexOf(n)));
            }
            else {
                fullList.add(n);
            }
        }
        return fullList;
    }

    @Override
    public ArrayList<String> getNoteNames() {
        ArrayList<String> nameList = new ArrayList<String>();
        for (Note n : getAllNotesInRange()) {
            nameList.add(n.toString());
        }
        return nameList;
    }

    @Override
    public int musicLength() {
        return this.getNotes().size();
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

    @Override
    public Note firstNote() {
        if (this.notes.size() == 0) {
            throw new IllegalArgumentException("No first note to return.");
        }
        Note first = notes.get(0);
        for (Note n : this.notes) {
            if (n.compareTo(first) < 0) {
                first = n;
            }
        }
        return first;
    }

    @Override
    public Note lastNote() {
        if (this.notes.size() == 0) {
            throw new IllegalArgumentException("No last note to return.");
        }
        Note last = notes.get(0);
        for (Note n : this.notes) {
            if (n.compareTo(last) > 0) {
                last = n;
            }
        }
        return last;
    }


    public int getTempo(){
        return this.tempo;
    }

}
