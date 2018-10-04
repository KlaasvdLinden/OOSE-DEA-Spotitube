package Service.PlaylistService;

import Dao.TestData;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/playlists")
public class PlaylistService {
    @Inject
    TestData testData;

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
