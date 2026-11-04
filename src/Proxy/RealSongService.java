package Proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Real subject: simulates the slow server that holds all the songs metadata
public class RealSongService implements SongService {
    private final Map<Integer, Song> database = new HashMap<>();

    public void addSong(Song song) { database.put(song.getId(), song); }

    @Override
    public Song searchById(Integer songID) {
        try {Thread.sleep(1000); } catch (Exception e) {}
        return database.get(songID);
    }

    @Override
    public List<Song> searchByTitle(String title) {
        try {Thread.sleep(1000); } catch (Exception e) {}
        List<Song> results = new ArrayList<>();
        for (Song s: database.values())
            if (s.getTitle().equalsIgnoreCase(title)) results.add(s);
        return results;
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        try {Thread.sleep(1000); } catch (Exception e) {}
        List<Song> results = new ArrayList<>();
        for (Song s: database.values())
            if (s.getAlbum().equalsIgnoreCase(album)) results.add(s);
        return results;
    }
}
