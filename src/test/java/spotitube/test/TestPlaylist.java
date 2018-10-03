package spotitube.test;

import Playlist.Playlist;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPlaylist {

    private Playlist playlist;

    @Before
    public void init(){
        playlist = new Playlist();
    }

    @Test
    public void testGetId(){
        //Act
        playlist.setId(1);
        //Assert
        assertEquals(1, playlist.getId());
    }

    @Test
    public void testGetName(){
        //Act
        playlist.setName("Playlist");
        //Assert
        assertEquals("Playlist", playlist.getName());
    }

    @Test
    public void testGetOwner(){
        //Act
        playlist.setOwner(true);
        //Assert
        assertEquals(true, playlist.isOwner());
    }
}
