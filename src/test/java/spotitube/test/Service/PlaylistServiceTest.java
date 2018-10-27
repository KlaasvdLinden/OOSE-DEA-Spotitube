package spotitube.test.Service;

import oose.dea.spotitube.klaasvanderlinden.Domain.Playlists.Playlists;
import oose.dea.spotitube.klaasvanderlinden.IdentityMappers.PlaylistIdentityMapper;
import oose.dea.spotitube.klaasvanderlinden.Service.Playlist.PlaylistService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceTest {

    @InjectMocks
    private PlaylistService playlistService;

    @Mock
    private PlaylistIdentityMapper playlistIdentityMapper;

    @Test
    public void testGetPlaylist(){
        Playlists playlists = new Playlists();
        Mockito.when(playlistIdentityMapper.getPlaylists(Mockito.anyInt())).thenReturn(playlists);
        Playlists receivedPlaylists = playlistService.getPlaylists();

        assertEquals(playlists, receivedPlaylists);
    }

    @Test
    public void testEditPlaylist(){
        ArgumentCaptor valueCapture = ArgumentCaptor.forClass(String.class);
        doNothing().when(playlistIdentityMapper).editPlaylist(any(Integer.class),
                (String) valueCapture.capture());
        playlistService.editPlaylist(0, "Test");

        assertEquals("Test", valueCapture.getValue());
    }

    @Test
    public void testAddPlaylist(){
        ArgumentCaptor valueCapture = ArgumentCaptor.forClass(String.class);
        doNothing().when(playlistIdentityMapper).addPlaylist(any(Integer.class),
                (String) valueCapture.capture());
        playlistService.addPlaylist("Test");

        assertEquals("Test", valueCapture.getValue());
    }

    @Test
    public void testDeletePlaylist(){
        ArgumentCaptor valueCapture = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(playlistIdentityMapper).deletePlaylist((int) valueCapture.capture());
        playlistService.deletePlaylist(1);

        assertEquals(1, valueCapture.getValue());
    }
}
