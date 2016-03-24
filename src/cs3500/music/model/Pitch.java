package cs3500.music.model;

public enum Pitch {
    C, CSharp, D, DSharp, E, F, FSharp, G, GSharp, A, ASharp, B;

    /**
     * @return an integer value for this pitch.
     */
    public int getVal() {
        switch(this.name()) {
            case "C":
                return 0;
            case "CSharp":
                return 1;
            case "D":
                return 2;
            case "DSharp":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            case "FSharp":
                return 6;
            case "G":
                return 7;
            case "GSharp":
                return 8;
            case "A":
                return 9;
            case "ASharp":
                return 10;
            default:
                return 11;
        }
    }

    /**
     * Parses the given string to a pitch.
     * @param str the string to be parsed
     * @return the pitch represented by the given string
     */
    public static Pitch parsePitch(String str) throws IllegalArgumentException {
        switch(str) {
            case "C":
                return Pitch.C;
            case "C#":
                return Pitch.CSharp;
            case "D":
                return Pitch.D;
            case "D#":
                return Pitch.DSharp;
            case "E":
                return Pitch.E;
            case "F":
                return Pitch.F;
            case "F#":
                return Pitch.FSharp;
            case "G":
                return Pitch.G;
            case "G#":
                return Pitch.GSharp;
            case "A":
                return Pitch.A;
            case "A#":
                return Pitch.ASharp;
            case "B":
                return Pitch.B;
            default:
                throw new IllegalArgumentException("Invalid pitch.");
        }
    }

    /**
     * @return the pitch a half step up from this pitch.
     */
    public Pitch halfStepUp() {
        switch(this.name()) {
            case "C":
                return Pitch.CSharp;
            case "CSharp":
                return Pitch.D;
            case "D":
                return Pitch.DSharp;
            case "DSharp":
                return Pitch.E;
            case "E":
                return Pitch.F;
            case "F":
                return Pitch.FSharp;
            case "FSharp":
                return Pitch.G;
            case "G":
                return Pitch.GSharp;
            case "GSharp":
                return Pitch.A;
            case "A":
                return Pitch.ASharp;
            case "ASharp":
                return Pitch.B;
            default:
                return Pitch.C;
        }
    }

    /**
     * @return a String representation of this Pitch.
     */
    public String toString() {
        switch(this.name()) {
            case "ASharp":
                return "A#";
            case "CSharp":
                return "C#";
            case "DSharp":
                return "D#";
            case "FSharp":
                return "F#";
            case "GSharp":
                return "G#";
            default:
                return this.name();
        }
    }

    /**
     * @return the pitch corresponding to the given value
     * @param p [0,11] representing a pitch.
     */
    public static Pitch pitchFromVal(int p) {
        switch(p) {
            case 0:
                return Pitch.C;
            case 1:
                return Pitch.CSharp;
            case 2:
                return Pitch.D;
            case 3:
                return Pitch.DSharp;
            case 4:
                return Pitch.E;
            case 5:
                return Pitch.F;
            case 6:
                return Pitch.FSharp;
            case 7:
                return Pitch.G;
            case 8:
                return Pitch.GSharp;
            case 9:
                return Pitch.A;
            case 10:
                return Pitch.ASharp;
            default:
                return Pitch.B;
        }
    }
}