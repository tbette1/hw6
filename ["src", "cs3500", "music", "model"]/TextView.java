package cs3500.music.view;

import cs3500.music.model.MusicPlayer;
import cs3500.music.model.Note;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by torybettencourt on 3/18/16.
 */
public class TextView implements View {
    private MusicPlayer mModel;


    /**
     * Constructs a new TextView object.
     * @param model the model to be represented by this TextView
     */
    public TextView(MusicPlayer model){
        this.mModel = Objects.requireNonNull(model);
    }

    /**
     * Outputs text representation of this model.
     */
    public void display() {
        if (mModel.musicLength() == 0) {
            throw new IllegalStateException("Nothing to output!");
        }
        for (String s : getLines()) {
            System.out.print(s);
        }

    }

    public ArrayList<String> getLines() {
        ArrayList<String> lines = new ArrayList<String>();
        String out = "   ";
        ArrayList<Note> rangeNotes = mModel.getSong().getAllNotesInRange();
        for (Note n : rangeNotes) {
            out += n.toString();
        }
        lines.add(out);

        for (int i = 0; i < mModel.getSong().getLength(); i++) {
            out = "\n" + i + " ";
            if (i < 10) {
                out += " ";
            }
            for (Note n : rangeNotes) {
                if (i >= n.getActions().size()) {
                    out += "     ";
                } else {
                    out += n.getActions().get(i).toString();
                }
            }
            lines.add(out);
        }
        return lines;
    }

}