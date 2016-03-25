package cs3500.music.view;
import cs3500.music.model.Attribute;
import cs3500.music.model.MusicPlayer;
import cs3500.music.model.Note;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class GraphicView extends JFrame implements GUIView {
    MusicPlayer mModel;
    private JPanel displayPanel;

    /**
     * Constructs a new GraphicView object.
     * @param m model to be represented by this view.
     */
    public GraphicView(MusicPlayer m) {
        this.mModel = Objects.requireNonNull(m);
        this.displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(displayPanel);
        this.pack();
        this.setSize(1100, 300);
        this.setResizable(false);
    }

    /**
     *  Creates the editor view grid.
     */
    private JPanel createEditorGrid() {
        JPanel basePanel = new JPanel();
        basePanel.setBackground(Color.BLACK);
        System.out.println(this.mModel.getSong().getAllNotesInRange().size());
        int rows = this.mModel.getSong().getAllNotesInRange().size();
        int cols = this.mModel.getSong().getLength() + (this.mModel.getSong().getLength() % 4);
        basePanel.setLayout(new GridLayout(rows, cols + 1, 0, 0));
        ArrayList<Note> allNotes = this.mModel.getSong().getAllNotesInRange();
        for (int i = 0; i < rows; i++) {
            ArrayList<Attribute> actions = allNotes.get(i).getActions();
            for (int j = 0; j < cols; j++) {
                Border border = null;
                if (j % 4 == 0) {
                    border = new MatteBorder(1, 1, 1, 0, Color.BLACK);
                }
                else if (j % 4 == 3) {
                    border = new MatteBorder(1, 0, 1, 1, Color.BLACK);
                }
                else {
                    border = new MatteBorder(1, 0, 1, 0, Color.BLACK);
                }
                BeatPanel x = new BeatPanel(j % 4);
                if (!this.mModel.getSong().getAllNotesInRange().get(i).plays()) {
                    x.setBackground(Color.LIGHT_GRAY);
                }
                else if (actions == null) {
                    x.setBackground(Color.LIGHT_GRAY);
                }
                else if (actions.size() <= j) {
                    x.setBackground(Color.LIGHT_GRAY);
                }
                else if (actions.get(j).equals(Attribute.Play)) {
                    x.setBackground(Color.BLACK);
                }
                else if (actions.get(j).equals(Attribute.Sustain)) {
                    x.setBackground(Color.GREEN);
                }
                else {
                    x.setBackground(Color.LIGHT_GRAY);
                }
                x.setBorder(border);
                basePanel.add(x);

            }
        }
        return basePanel;
    }

    /**
     * @return a panel containing the beat numbers for the song.
     */
    private JPanel beatDisplay(){
        JPanel beatsPanel = new JPanel();
        int length = this.mModel.getSong().getLength() + (this.mModel.getSong().getLength() % 4);
        beatsPanel.setLayout(new GridLayout(1, length));
        beatsPanel.add(new BeatPanel(-1));
        for (int i = 0; i < length + 1; i++) {
            BeatPanel b = new BeatPanel(-1);
            b.setLayout(new BorderLayout());
            if (i % 16 == 0) {
                JLabel beatLabel = new JLabel(i + "");
                String font = beatLabel.getFont().getFontName();
                beatLabel.setFont(new Font(font, Font.PLAIN, 10));
                b.add(beatLabel, BorderLayout.SOUTH);
            }
            beatsPanel.add(b);
        }
        return beatsPanel;
    }

    /**
     * @return a panel containing all note names in this song.
     */

    private JPanel noteNameDisplay() {
        JPanel namePanel = new JPanel();
        ArrayList<Note> notes = this.mModel.getSong().getAllNotesInRange();
        namePanel.setLayout(new GridLayout(notes.size(), 1));
        for (int i = 0; i < notes.size(); i++) {
            BeatPanel b = new BeatPanel(-1);
            b.setLabel(notes.get(i));
            b.setBorder(new MatteBorder(1, 0, 1, 0, Color.BLACK));
            namePanel.add(b);
        }
        return namePanel;
    }

    /**
     * Initializes this graphic view to have an editor grid, note name displays,
     * and beat counts.
     */
    public void initialize(){
        displayPanel.add(createEditorGrid(), BorderLayout.CENTER);
        displayPanel.add(noteNameDisplay(), BorderLayout.WEST);
        displayPanel.add(beatDisplay(), BorderLayout.NORTH);
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
