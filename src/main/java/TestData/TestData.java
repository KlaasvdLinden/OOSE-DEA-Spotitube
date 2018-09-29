package TestData;

import Playlist.Playlist;
import Playlist.Playlists;
import Track.Track;
import login.ResponseLogin;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class TestData {


    private static TestData testData;

    private final String USER = "klaas";
    private final String PASSWORD = "test";
    private final String TOKEN = "1234";
    private ResponseLogin rl = new ResponseLogin();

    private Playlists ps = new Playlists();
    private final int PLAYLISTS_LENGTH = 12345;

    private  Playlist p = new Playlist();
    private  Playlist p2 = new Playlist();


    public String getUSER() {
        return USER;
    }

    public void initResponseLogin(){
        rl.setUser(USER);
        rl.setToken(TOKEN);
    }

    public ResponseLogin getRl(){
        return rl;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getTOKEN(){
        return TOKEN;
    }

    public void initPlaylists(){
        p.setId(1);
        p.setName("My first playlist");
        p.setOwner(true);

        p2.setId(2);
        p2.setName("Random playlist");
        p2.setOwner(true);

        ps.addPlayList(p);
        ps.addPlayList(p2);
        ps.setLength(PLAYLISTS_LENGTH);
    }

    public void initTracks(){
        Track t = new Track();
        t.setTitle("The Song");
        t.setAlbum("Songs");
        t.setDuration(5);
        t.setPerformer("The Singer");

        p.addTrack(t);

        Track t2 = new Track();
        t2.setTitle("The Lie");
        t2.setAlbum("Deception");
        t2.setDuration(4);
        t2.setPerformer("The Truth");

        p2.addTrack(t2);

    }
    public Playlists getPlaylists() {
        return this.ps;
    }

    public List<Track> returnPS(int id){
        for(Playlist ps: ps.getPlaylists()){
            if(ps.getId() == id){
               return ps.getTracks();
            }

        }
        return null;
    }

//    public static  TestData getInstance(){
//        if(testData == null){
//            testData = new TestData();
//        }
//        return testData;
//    }

}
