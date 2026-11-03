package Proxy;

public class Song {
    private final int id;
    private final String title;
    private final String artist;
    private final String album;
    private final int duration;

    public Song(int id, String title, String artist, String album, int duration) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getAlbum() { return album; }
    public int getDuration() { return duration; }

    @Override
    public String toString() {
        return "Song{" + id + ", \"" + title + "\", " + artist + ", " + album + ", " + duration + "s}";
    }
}
