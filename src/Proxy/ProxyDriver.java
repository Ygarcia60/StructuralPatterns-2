package Proxy;
public class ProxyDriver {
    public static void main(String[] args) {
        RealSongService server = new RealSongService();
        server.addSong(new Song(1, "Bohemian Rhapsody", "Queen", "A Night at the Opera", 354));
        server.addSong(new Song(2, "Love of My Life", "Queen", "A Night at the Opera", 219));
        server.addSong(new Song(3, "Hotel California", "Eagles", "Hotel California", 391));
        server.addSong(new Song(4, "Billie Jean", "Michael Jackson", "Thriller", 294));
        server.addSong(new Song(5, "Thriller", "Michael Jackson", "Thriller", 357));
        server.addSong(new Song(6, "Imagine", "John Lennon", "Imagine", 183));
        server.addSong(new Song(7, "Yesterday", "The Beatles", "Help!", 125));

        // The client only knows about the SongService interface
        SongService service = new SongServiceProxy(server);

        System.out.println("--- Search by ID ---");
        timed("First  searchById(3)", () -> System.out.println("  " + service.searchById(3)));
        timed("Second searchById(3)", () -> System.out.println("  " + service.searchById(3)));

        System.out.println("\n--- Search by title ---");
        timed("First  searchByTitle", () -> System.out.println("  " + service.searchByTitle("Thriller")));
        timed("Second searchByTitle", () -> System.out.println("  " + service.searchByTitle("Thriller")));

        System.out.println("\n--- Search by album ---");
        timed("First  searchByAlbum", () -> System.out.println("  " + service.searchByAlbum("A Night at the Opera")));
        timed("Second searchByAlbum", () -> System.out.println("  " + service.searchByAlbum("A Night at the Opera")));

        System.out.println("\n--- ID cache warmed by album search ---");
        timed("searchById(2)", () -> System.out.println("  " + service.searchById(2)));
    }

    private static void timed(String label, Runnable r) {
        long start = System.currentTimeMillis();
        r.run();
        System.out.println(label + " took " + (System.currentTimeMillis() - start) + " ms");
    }
}
