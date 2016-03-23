package cs3500.music.view;

/**
 * Created by torybettencourt on 3/22/16.
 */
import javax.swing.*;
import java.awt.*;

public class MeasurePanel extends JPanel {
    static int RECTANGLE_WIDTH = 900;
    static int RECTANGLE_HEIGHT = 50;
    static int QUARTER_WIDTH = RECTANGLE_WIDTH / 4;
    int x;
    int y;

    /**
     * Constructs a new MeasurePanel.
     * @param x X-Coordinate of this MeasurePanel's location.
     * @param y Y-Coordinate of this MeasurePanel's location.
     */
    public MeasurePanel(int x, int y) {
        this.x = x;
        this.y = y;
        this.setBounds(x, y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
        this.setBackground(Color.GRAY);

    }

    /**
     * Adds a note to this MeasurePanel.
     * @param startBeat the beat on which the note starts [0,3] IF this panel
     *                  is created as a continuation note where the note head occurred
     *                  in the previous panel, startBeat should be -1.
     * @param duration the length of this note > 0 IF this panel is created as a
     *                 continuation note where the note head occurred in the previous
     *                 panel, duration should be
     *                 (originalDuration - numberOfBeatsExpressedInPreviousPanel).
     * @throws IllegalStateException if duration is longer than (4 - startBeat)
     *         to signal that the measurePanel directly to the right of this one
     *         must continue the note.
     */
    public MeasurePanel addNote(int startBeat, int duration) throws IllegalStateException {
        if (startBeat + duration > 4) {
            throw new IllegalStateException("Remaining beats cannot be expressed in this panel.");
        }

        JPanel noteHead = new JPanel();
        noteHead.setSize(QUARTER_WIDTH, RECTANGLE_HEIGHT);
        noteHead.setBackground(Color.BLACK);
        noteHead.setLocation(QUARTER_WIDTH * startBeat, 0);
        this.add(noteHead);

        JPanel noteTail = new JPanel();
        noteTail.setSize(QUARTER_WIDTH * (duration - 1), RECTANGLE_HEIGHT);
        noteTail.setBackground(Color.GREEN);
        noteTail.setLocation(QUARTER_WIDTH * (startBeat + 1), 0);
        this.add(noteTail);

        return this;
    }
}
