package Proxy;

import java.util.List;

//Subject: the common interface shared by the real service and the proxy
public interface SongService {
    Song searchById(Integer songID);
    List<Song> searchByTitle(String title);
    List<Song> searchByAlbum(String album);
}
