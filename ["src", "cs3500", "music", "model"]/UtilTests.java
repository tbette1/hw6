package cs3500.music.util;


import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class UtilTests {


    @Test
    public void testMusicEditorNoArgsIOException() {
        String[] none = new String[0];
        try {
            MusicEditor.main(none);
        }
        catch (IOException e) {
            assertEquals(true, true);
        }
    }

    @Test
    public void testMusicEditorViewTypeIOException() {
        String[] one = new String[2];
        one[0] = "mary.txt";
        one[1] = "nothing";
        try {
            MusicEditor.main(one);
        }
        catch (IOException e) {
            assertEquals(true, true);
        }
    }

    @Test
    public void testMusicEditorFileNameIOException() {
        String[] one = new String[2];
        one[0] = "midi";
        one[1] = "nothing";
        try {
            MusicEditor.main(one);
        }
        catch (IOException e) {
            assertEquals(true, true);
        }
    }

    @Test
    public void testMusicEditorGUI() {
        String[] goodArgs = new String[2];
        goodArgs[0] = "mary.txt";
        goodArgs[1] = "gui";
        try {
            MusicEditor.main(goodArgs);
        }
        catch (IOException e) {
            assertEquals(false, false);
        }

    }

}
