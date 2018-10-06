package Controllers.PlaylistController;


import Dao.Track.TrackDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/playlists")
public class PlaylistController {
    @Inject
    private Service.PlaylistService playlistService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayLists(@QueryParam("token") String token) {
        if (token.equals("123-123")) {
            return Response.ok(playlistService.getPlaylist(token), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(403).build();
        }
    }
}
