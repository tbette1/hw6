package cs3500.music.model;

/**
 * Created by torybettencourt on 3/14/16.
 */
public enum Attribute {
    Play, Sustain, Rest;

    /**
     * @return a String representation of this NoteAction.
     */
    public String toString() {
        switch(this.name()) {
            case "Play":
                return "  X  ";
            case "Sustain":
                return "  |  ";
            default:
                return "     ";
        }
    }
}
