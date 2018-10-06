package Service.PlaylistService;


import Dao.Track.TrackDAO;
import Manager.PlaylistManager;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/playlists")
public class PlaylistService {
    @Inject
    private PlaylistManager playlistManager;

    @Inject
    TrackDAO trackDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayLists(@QueryParam("token") String token) {
        if (token.equals("123-123")) {
//            playlistManager.getPlaylist(token).getPlaylists().get(0).addTrack(trackDAO.findAll(1).get(0));
//            System.out.println(playlistManager.getPlaylist(token).getPlaylists().get(0).getTracks());
            return Response.ok(playlistManager.getPlaylist(token), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(403).build();
        }
    }
}
