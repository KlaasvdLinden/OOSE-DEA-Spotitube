package Playlist;

import Track.Track;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayLists(@QueryParam("token") String token) {
        System.out.println(token);
        if (token.equals("1234")) {
            Playlists ps = new Playlists();

            Playlist p = new Playlist();
            p.setId(1);
            p.setName("My first playlist");
            p.setOwner(true);

            Playlist p2 = new Playlist();
            p2.setId(2);
            p2.setName("Random playlist");
            p2.setOwner(true);

            ps.addPlayList(p);
            ps.addPlayList(p2);
            ps.setLength(12345);
            return Response.ok(ps, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(403).build();
        }

    }
}
