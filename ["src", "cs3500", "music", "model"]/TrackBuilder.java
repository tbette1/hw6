package cs3500.music.util;

import cs3500.music.model.Attribute;
import cs3500.music.model.Note;
import cs3500.music.model.Song;
import cs3500.music.model.Track;

import java.util.ArrayList;

/**
 * Builder object for Tracks.
 */
public final class TrackBuilder implements CompositionBuilder<Song> {
    int tempo;
    ArrayList<Note> buildNotes;


    public TrackBuilder(){
        buildNotes = new ArrayList<Note>();
    }

    @Override
    public CompositionBuilder<Song> setTempo(int temp) {
        System.out.println(temp + "(Builder setTempo) -->");
        this.tempo = temp;
        return this;
    }

    @Override
    public CompositionBuilder<Song> addNote(int start, int end, int instrument, int pitch, int volume) {
        Note n = Note.parseNoteFromInt(pitch, instrument, volume);
        if (!buildNotes.contains(n)) {
            buildNotes.add(n);
        }
        Note toChange = buildNotes.get(buildNotes.indexOf(n));
        while (toChange.getActions().size() - 1 < start) {
            toChange.getActions().add(Attribute.Rest);
        }
        toChange.getActions().set(start, Attribute.Play);
        for (int i = start + 1; i < end; i++) {
            if (toChange.getActions().size() - 1 < i) {
                toChange.getActions().add(Attribute.Rest);
            }
            toChange.getActions().set(i, Attribute.Sustain);
        }

        return this;
    }

    @Override
    public Track build() {
        System.out.println(tempo + "(build) -->");
        return new Track(buildNotes, tempo);
    }
}