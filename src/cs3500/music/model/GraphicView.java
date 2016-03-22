package cs3500.music.view;

/**
 * Created by torybettencourt on 3/18/16.
 */
import javax.swing.*;
import java.awt.*;
public class GraphicView implements GUIView {
    private final int RECTANGLE_HEIGHT = 20; // can definitely bechanged
    private final int RECTANGLE_WIDTH = 40; // can definitely be changed
    private final int LINE_SEPARATOR_WIDTH = 3; // probably about right actually

    public JPanel createBaseGrid(int songLength, int numNotes) {
        JPanel basePanel = new JPanel();
        basePanel.setSize(400, 400);
        basePanel.setBackground(Color.BLACK);

        for (int j = 0; j < numNotes; j++) {
            int vLoc = j * (RECTANGLE_HEIGHT + LINE_SEPARATOR_WIDTH) + LINE_SEPARATOR_WIDTH;

            for (int i = 0; i < songLength; i++) {
                JPanel p = new JPanel();
                p.setSize(RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
                p.setBackground(Color.GRAY);
                int hLoc = i * (RECTANGLE_WIDTH + LINE_SEPARATOR_WIDTH) + LINE_SEPARATOR_WIDTH ;
                p.setLocation(hLoc, vLoc);
                basePanel.add(p);
            }
        }
        return basePanel;
    }

    /**
     *
     * @param startBeat beat on which the note to be shown starts. IF this panel
     *                  is created as the continuation of a panel before, startbeat
     *                  should be -1.
     * @param duration  duration of the note to be shown. IF this panel is created as
     *                  the continuation of a panel before it, duration should be (the
     *                  original note duration - the number of beats the note played in
     *                  the measure before).
     * @return a single measure Panel
     * @throws IllegalStateException if the duration is greater than four. Meant to give instruction
     *                               to create a continuation panel.
     */
    public static JPanel measurePanel(int startBeat, int duration) throws IllegalStateException {
        if (startBeat + duration > 4) {
            throw new IllegalStateException("Remaining beats cannot be expressed in this panel.");
        }

        JPanel np = new JPanel();
        np.setSize(RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
        np.setBackground(Color.GRAY);
        if (startBeat != -1) {
            JPanel noteHead = new JPanel();
            noteHead.setSize(RECTANGLE_WIDTH / 4, RECTANGLE_HEIGHT);
            noteHead.setBackground(Color.BLACK);
            noteHead.setLocation(RECTANGLE_WIDTH * (startBeat / 4), 0);
            np.add(noteHead);

            JPanel noteTail = new JPanel();
            noteTail.setSize(RECTANGLE_WIDTH * ((duration - 1) / 4), RECTANGLE_HEIGHT);
            noteTail.setBackground(Color.GREEN);
            noteTail.setLocation(((RECTANGLE_WIDTH * (startBeat / 4)) * (duration - 1)), 0);
            np.add(noteTail);
        }
        else {
            JPanel note = new JPanel();
            note.setSize(RECTANGLE_WIDTH * (duration / 4), RECTANGLE_HEIGHT);
            note.setBackground(Color.GREEN);
            note.setLocation(0, 0);
            np.add(note);
        }

        return np;
    }

    public void display() {

    }
}
