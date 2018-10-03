package Playlist;

import TestData.TestData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/playlists")
public class PlaylistController {
    TestData testData = new TestData();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayLists(@QueryParam("token") String token) {
        if (token.equals(testData.getTOKEN())) {
            testData.initPlaylists();
            testData.initTracks();
            return Response.ok(testData.getPlaylists(), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(403).build();
        }
    }


}
