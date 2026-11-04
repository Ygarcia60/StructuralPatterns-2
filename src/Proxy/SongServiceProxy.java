package Proxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Proxy: implements the same interface as the real service
// but only calls the real (slow) server on a cache miss
public class SongServiceProxy implements SongService {
    private final SongService realService;
    private final Map<Integer, Song> idCache = new HashMap<>();
    private final Map<String, List<Song>> titleCache = new HashMap<>();
    private final Map<String, List<Song>> albumCache = new HashMap<>();

    public SongServiceProxy(SongService realService) {
        this.realService = realService;
    }

    @Override
    public Song searchById(Integer songID) {
        if (idCache.containsKey(songID)) {
            System.out.println("  [CACHE HIT] id " + songID);
            return idCache.get(songID);
        }
        System.out.println("  [CACHE MISS] id " + songID + " -> calling server...");
        Song song = realService.searchById(songID);
        if (song != null) idCache.put(songID, song);
        return song;
    }

    @Override
    public List<Song> searchByTitle(String title) {
        String key = title.toLowerCase();
        if (titleCache.containsKey(key)) {
            System.out.println("  [CACHE HIT] title \"" + title + "\"");
            return titleCache.get(key);
        }
        System.out.println("  [CACHE MISS] title \"" + title + "\" -> calling server...");
        List<Song> songs = realService.searchByTitle(title);
        titleCache.put(key, songs);
        for (Song s : songs) idCache.put(s.getId(), s);   // bonus: warm the id cache
        return songs;
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        String key = album.toLowerCase();
        if (albumCache.containsKey(key)) {
            System.out.println("  [CACHE HIT] album \"" + album + "\"");
            return albumCache.get(key);
        }
        System.out.println("  [CACHE MISS] album \"" + album + "\" -> calling server...");
        List<Song> songs = realService.searchByAlbum(album);
        albumCache.put(key, songs);
        for (Song s : songs) idCache.put(s.getId(), s);
        return songs;
    }
}
