package spotitube.test;

import Controllers.PlaylistController.PlaylistController;
import Dao.Entity.User;
import Dao.User.UserDAO;
import Domain.Playlist.Playlist;
import Domain.Playlists.Playlists;
import Service.Playlist.IPlaylistService;
import Service.User.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistControllerTest {

    @InjectMocks
    private PlaylistController playlistController;

    @Mock
    private IPlaylistService playlistService;

    @Mock
    private IUserService userService;

    private Playlists playlists;

    @Before
    public void setup(){
        playlists = new Playlists();
        Mockito.when(userService.rightToken("123")).thenReturn(true);
        Mockito.when(playlistService.getPlaylist()).thenReturn(playlists);
    }

    @Test
    public void testGetAllPlaylistsOk(){
        Response response =  playlistController.getPlayLists("123");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlists, response.getEntity());
    }

    @Test
    public void testGetAllPlaylistReturns403(){
        Response response = playlistController.getPlayLists("12");

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void testEditPlaylistOk(){
        Playlist playlist = new Playlist();
        Response response = playlistController.editPlaylist("123", 1, playlist);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlists, response.getEntity());
    }

    @Test
    public void testEditPlaylistReturns403(){
        Playlist playlist = new Playlist();
        Response response = playlistController.editPlaylist("321", 1, playlist);

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void testAddPlaylistOk(){
        Playlist playlist = new Playlist();
        Response response = playlistController.addPlaylist("123", playlist);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlists, response.getEntity());
    }

    @Test
    public void testAddPlaylistReturns403(){
        Playlist playlist = new Playlist();
        Response response = playlistController.addPlaylist("abc", playlist);

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void testDeletePlaylistOk(){
        Response response = playlistController.deletePlaylist("123", 1);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlists, response.getEntity());
    }

    @Test
    public void testDeletePlaylistReturns403(){
        Response response = playlistController.deletePlaylist("a1b2c3", 1);

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

}
