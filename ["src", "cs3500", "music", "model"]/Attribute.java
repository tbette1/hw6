package cs3500.music.model;


public enum Attribute {
    Play, Sustain, Rest;

    /**
     * @return a String representation of this Note Attribute.
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
