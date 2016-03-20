package cs3500.music.view;

import cs3500.music.model.*;

import java.util.Collections;
import java.util.ArrayList;

/**
 * Created by torybettencourt on 3/18/16.
 */
public class TextView implements View {
    MusicPlayer mModel;
    /**
     * /**
     * @return contents of this music model as a string.

    public String output() {
        String out = "";
        for (Note n : this.trimSong()) {
            out += n.toString();
        }

        for (int i = 0; i < this.getSongLength(); i++) {
            out += "\n" + i;
            for (Note n : this.trimSong()) {
                out += n.actions.get(i).toString();
            }
        }
        return out;
    }
     */

    public String output() {
        String out = "";

        ArrayList<Note> rangeNotes = mModel.getSong().getAllNotesInRange();
        ArrayList<String> names = mModel.getSong().getNoteNames(rangeNotes);
        Collections.sort(names);
        for (String s : names) {
            if (s.length() == 2) {
                out += "  " + s + " ";
            }
            else {
                out += " " + s + " ";
            }
        }

        for (int i = 0; i < mModel.getSong().getLength(); i++) {
            out += "\n" + i;
            for (Note n : rangeNotes) {
                out += n.getActions().get(i).toString();
            }
        }
        return out;
    }

    @Override
    public void display() {
        System.out.print(output());
    }
}
