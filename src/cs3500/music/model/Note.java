package cs3500.music.model;


import java.util.ArrayList;

public class Note {
    Pitch pitch;
    int octave;
    int instrument;
    int volume;
    ArrayList<Attribute> actions;

    /**
     * Constructs a new Note object
     * @param p the pitch of this note
     * @param octave the octave of this note
     */
    public Note(Pitch p, int octave, int instrument, int volume) throws IllegalArgumentException {
        if (octave < 0) {
            throw new IllegalArgumentException("Octaves cannot be less than 0.");
        }
        if (octave > 9) {
            throw new IllegalArgumentException("Highest note possible is B9");
        }

        this.pitch = p;
        this.octave = octave;

        actions = new ArrayList<Attribute>();
        for (int i = 0; i < 32; i++) {
            actions.add(Attribute.Rest);
        }
    }


    /**
     * @param o object to compare to this note.
     * @return true if this note is equal to the given note,
     *         returns false otherwise.
     */
    public boolean equals(Object o) {
        if (o instanceof Note) {
            return (o.hashCode() == this.hashCode() &&
                        this.instrument == ((Note) o).instrument);
        }
        return false;
    }

    /**
     * @return the hashCode for this Note.
     */
    public int hashCode() {
        return ((this.octave - 1) * 8) +
                this.pitch.getVal();
    }

    /**
     * @return a string representation of this note
     */
    public String toString() {
        String out = this.pitch.toString() + this.octave;
        if (out.length() == 2) {
            return "  " + out + " ";
        }
        else {
            return " " + out + " ";
        }
    }

    /**
     * @param str String to be parsed into a note.
     * @return the correct note according to the given String.
     */
    public static Note parseNote(String str) throws IllegalArgumentException {
        String pitch = str.substring(0, str.length() - 1);
        if (pitch.length() > 2 || pitch.length() < 1) {
            throw new IllegalArgumentException("Invalid note");
        }
        Pitch p = null;
        try {
            p = Pitch.parsePitch(pitch);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid note.");
        }
        int octave = -1;
        try {
            octave = Integer.parseInt(str.substring(str.length() - 1));
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid note");
        }
        try {
            //return new Note(p, octave);   *******TODO FIX
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid note");
        }
        return null; // TODO fix this pls
    }

    /**
     * Parses a note from the given value.
     * @param pitch [0,127] where 60 represents C4.
     * @param instrument the instrument of this note
     */
    public static Note parseNoteFromInt(int pitch, int instrument, int volume) {
        int pitchVal = pitch % 8;
        int octave = pitch / 8;

        return new Note(Pitch.pitchFromVal(pitchVal), octave, instrument, volume);
    }

    /**
     * @return true if this note's actions have anything other than rests, returns
     * false otherwise.
     */
    public boolean plays() {
        for (Attribute a : actions) {
            if (!a.equals(Attribute.Rest)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the beat number where this note plays for the last time.
     */
    public int getPlayLength() {
        if (!this.plays()) {
            return 0;
        }
        int i = this.actions.size() - 1;
        while (this.actions.get(i).equals(Attribute.Rest) && i > 0) {
            i--;
        }
        return i + 1;
    }

    /**
     * @return a new note, a half step up from this one.
     */
    public Note getHalfStepUp() {
        int o = -1;
        if (this.pitch.halfStepUp().equals(Pitch.C)) {
            o = this.octave + 1;
        }
        else {
            o = this.octave;
        }
        return new Note(this.pitch.halfStepUp(), o, this.instrument, this.volume);
    }

    /**
     * @return the beat on which this note plays for the first time.
     */
    public int getFirstPlay() {
        if (!this.plays()) {
            return -1;
        }
        int i = 0;
        if (this.actions.get(i).equals(Attribute.Rest)) {
            i++;
        }
        return i;
    }

    /**
     * @return this note's list of actions
     */
    public ArrayList<Attribute> getActions() {
        return this.actions;
    }

    /**
     * @return an ArrayList of ArrayLists of size 2, where the 0th element represents
     * the start beat of a play of this note, and the 1st element represents the duration.
     * Every play of this note will be represented in its own ArrayList, held in the larger ArrayList.
     */
    public ArrayList<ArrayList<Integer>> getPlays() {
        ArrayList<ArrayList<Integer>> notesPlayed = new ArrayList<ArrayList<Integer>>();
        int index = 0;
        int playing = 0;
        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i).equals(Attribute.Play)) {
                notesPlayed.get(index).add(i);
                playing = 1;
            }
            if (actions.get(i).equals(Attribute.Sustain)) {
                playing ++;
            }
            if (actions.get(i).equals(Attribute.Rest) && playing != 0) {
                notesPlayed.get(index).add(playing);
                index ++;
                playing = 0;
            }
        }
        return notesPlayed;
    }
}


