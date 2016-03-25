package cs3500.music.util;

import cs3500.music.model.*;
import cs3500.music.view.*;


import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Elijah on 3/22/2016.
 */
public final class MusicEditor{


    /**
     * Main Method for displaying a desired piece through the desired view/
     * Text: outputs the music file in text format
     * Graphic: Uses gui to output a representation of the view
     * Console: outputs the music file in audio format
     *
     * @param args: the name of the song and a view
     * @throws InvalidMidiDataException:
     * @throws IOException: invalid inputs
     *
     *
     */

    public static void main(String[] args) throws IOException {
        //Ensures that the inputs are valid
        if(args.length != 2){
            throw new IOException("Missing some form of input. piece name and" +
                    " view type are required");
        }
        String argsA =  args[0];
        String argsB = args[1];
// Checks to make sure that the inputs
        if (!(argsA.equals("midi") || argsB.equals("console") ||
                argsB.equals("gui") ||
                argsA.equals("midi") || argsB.equals("console") ||
                argsB.equals("gui"))) {
            throw new IOException("Invalid input; please enter a correct view type.");
        }
        if (!(argsA.equals("mary.txt") || argsA.equals("mystery-1.txt") ||
                argsA.equals("mystery-2.txt") ||
                argsB.equals("mary.txt") || argsB.equals("mystery-1.txt") ||
                argsB.equals("mystery-2.txt"))) {
            throw new IOException("Invalid input; please enter a correct piece name.");
        }

        // Defining the files and initial state of the model
        Readable mary = new FileReader("mary-little-lamb.txt");
        Readable mystery1 = new FileReader("mystery-1.txt");
        Readable mystery2 = new FileReader("mystery-2.txt");
        Readable mystery3 = new FileReader("mystery-3.txt");
        MusicPlayer model = new MusicPlayer();

        // Updates the model to the desired song
        if (argsA.equals("mary.txt") || argsB.equals("mary.txt")) {
            model.addSong(MusicReader.parseFile(mary, new TrackBuilder()), 0);
        } else if (argsA.equals("mystery-1.txt") || argsB.equals("mystery-1.txt")) {
            model.addSong(MusicReader.parseFile(mystery1, new TrackBuilder()), 0);
        } else if (argsA.equals("mystery-2.txt") || argsB.equals("mystery-2.txt")) {
            model.addSong(MusicReader.parseFile(mystery2, new TrackBuilder()), 0);
        }
        else if (argsA.equals("mystery-3.txt") || argsB.equals("mystery-3.txt")){
            model.addSong(MusicReader.parseFile(mystery3, new TrackBuilder()), 0);
        }

        final class ViewFactory {
            MusicPlayer m;

            public ViewFactory(MusicPlayer model) {
                this.m = model;
            }

            /**
             * @param s the type of view.
             * @return a new view of the type indicated by S.
             */
            View createView(String s) {
                switch(s) {
                    case "midi":
                        try {
                            return new MIDIView(m);
                        }
                        catch (MidiUnavailableException e) {
                            e.printStackTrace();
                        }
                    case "gui" :
                        return new GraphicView(m);
                    case "console" :
                        //return new TextView(m);
                    default :
                        throw new IllegalArgumentException("No view of such type.");
                }
            }
        }


        // Builds and runs the desired view
        if (argsA.equals("midi") || argsB.equals("midi")) {
            new ViewFactory(model).createView("midi").display();
        } else if (argsA.equals("console") || argsB.equals("console")) {
            new ViewFactory(model).createView("console").display();
        } else if (argsA.equals("gui") || argsB.equals("gui")) {
            new ViewFactory(model).createView("gui").display();
        }
    }
}
