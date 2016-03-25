package cs3500.music.view;

import cs3500.music.model.Note;

import javax.swing.*;
import java.awt.*;
public final class BeatPanel extends JPanel {
    int beatInMeasure;
    Color c;

    public BeatPanel(int beatInMeasure) {
        if (beatInMeasure != -1) {
            this.beatInMeasure = beatInMeasure;

            this.setSize(30, 30);
        }

        else {
            this.beatInMeasure = beatInMeasure;
            this.setLayout(new BorderLayout());
            c = Color.GRAY;
        }
    }

    public void setLabel(Note n) {
        if (beatInMeasure == -1) {
            add(new JLabel(n.toString()), BorderLayout.CENTER);
        }
        else {
            throw new IllegalArgumentException("Cannot add label to note.");
        }
    }





}
