package cs3500.music.view;
import cs3500.music.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class GraphicView extends JFrame implements GUIView {
    MusicPlayer mModel;
    MeasurePanel[][] grid;
    private JPanel displayPanel;
    private static final int LINE_SEPARATOR_WIDTH = 3; // probably about right actually
    private static final int TEXT_WIDTH = 20;
    private static final int TEXT_HEIGHT = 25;


    public GraphicView(MusicPlayer m) {
        this.mModel = m;
        initializeBaseGrid(2, 2);
        this.displayPanel = baseGridPanel();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(displayPanel);
        this.pack();
        this.setSize(1000, 500);
    }


    /**
     * @param songLength length of song
     * @param numNotes number of notes in song
     * @return a Base grid representing the possibilities of where notes can be
     *         in the graphic view.
     */
    private void initializeBaseGrid (int songLength, int numNotes) {
        int cols = (int) (songLength/4 + Math.ceil(songLength % 4));
        grid = new MeasurePanel[numNotes][cols];
        for (int j = 0; j < numNotes; j++) {
            int vLoc = j * (MeasurePanel.RECTANGLE_HEIGHT + LINE_SEPARATOR_WIDTH) + LINE_SEPARATOR_WIDTH;

            for (int i = 0; i < cols; i ++) {
                int hLoc = i * (MeasurePanel.RECTANGLE_WIDTH + LINE_SEPARATOR_WIDTH) + LINE_SEPARATOR_WIDTH ;
                MeasurePanel mp = new MeasurePanel(hLoc, vLoc);
                grid[j][i] = mp;
            }
        }
    }

    /**
     * Get base grid panel
     */
    private JPanel baseGridPanel() {
        JPanel basePanel = new JPanel();
        basePanel.setSize(200, 200);
        basePanel.setBackground(Color.BLACK);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                MeasurePanel m = grid[i][j];
                m.setBounds(m.x, m.y, MeasurePanel.RECTANGLE_WIDTH, MeasurePanel.RECTANGLE_HEIGHT);
                basePanel.add(m);

            }
        }
        return basePanel;
    }

    /**
     * @return a panel containing the beat numbers for the song.
     */
    private JPanel beatDisplay(){
        JPanel beatPanel = new JPanel();
        int l = mModel.getSong().getLength();
        if (!(l % 4 == 0)) {
            l = l + (4 - l % 4);
        }

        int x = 0;
        for (int i = 0; i < l; i += 4) {
            if (i % 16 == 0) {
                JLabel numLabel = new JLabel(i + "");
                numLabel.setSize(TEXT_WIDTH, TEXT_HEIGHT);
                numLabel.setLocation(x, 0);
                beatPanel.add(numLabel);
            }
            x += MeasurePanel.RECTANGLE_WIDTH + LINE_SEPARATOR_WIDTH;
        }
        return beatPanel;
    }

    /**
     * @return a panel containing all note names in this song.
     */
    private JPanel noteNameDisplay() {
        JPanel namePanel = new JPanel();
        ArrayList<String> notes = mModel.getSong().getNoteNames(mModel.getSong().getAllNotesInRange());
        namePanel.setSize(MeasurePanel.RECTANGLE_HEIGHT, notes.size() * (MeasurePanel.RECTANGLE_HEIGHT + LINE_SEPARATOR_WIDTH));
        int y = LINE_SEPARATOR_WIDTH;
        for (int i = 0; i < notes.size(); i++) {
            JLabel nameLabel = new JLabel(notes.get(i));
            nameLabel.setSize(TEXT_WIDTH, TEXT_HEIGHT);
            nameLabel.setLocation(0, y);
            namePanel.add(nameLabel);

            y += MeasurePanel.RECTANGLE_HEIGHT + LINE_SEPARATOR_WIDTH;
        }
        return namePanel;
    }

    /**
     * @param noteNum Note number in the list of notes in this song.
     * @param beatNum beat number of the panel we wish to get.
     * @return a point representing the location of the top left corner of the panel.
     */
    private static Point locOfPanel(int noteNum, int beatNum) {
        int y = noteNum * (MeasurePanel.RECTANGLE_HEIGHT + LINE_SEPARATOR_WIDTH) + LINE_SEPARATOR_WIDTH;
        int x = (beatNum / 4) * (MeasurePanel.RECTANGLE_WIDTH + LINE_SEPARATOR_WIDTH) + LINE_SEPARATOR_WIDTH;
        return new Point(x, y);
    }

    /**
     *
     */
    private void addNotesToGrid() throws IllegalStateException {
        if (this.grid == null) {
            throw new IllegalStateException("Grid not initialized");
        }
        ArrayList<Note> notes = this.mModel.getSong().getAllNotesInRange();
        for (Note n : notes) {
            ArrayList<ArrayList<Integer>> plays = n.getPlays();
            for (ArrayList<Integer> play : plays) {
                int startBeat = play.get(0);
                int dur = play.get(1);

                int noteNum = notes.indexOf(n);
                try {
                    grid[noteNum][startBeat/4].addNote((startBeat % 4), dur);
                }
                catch (IllegalStateException e) {
                    grid[noteNum][startBeat/4].addNote((startBeat % 4), 4 - (startBeat % 4));
                    grid[noteNum][startBeat/4 + 1].addNote(-1, dur - (4 - startBeat % 4));
                }
            }
        }
    }

    @Override
    public void initialize(){
        this.setVisible(true);
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(100, 100);
    }


    public void display() {
        initialize();
    }

}
