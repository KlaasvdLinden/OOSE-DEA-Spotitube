package Controllers.TrackController;

import Service.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists/{id}/tracks")
public class TrackController {

    @Inject
    TrackService trackService;


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@QueryParam("token") String token, @PathParam("id") int id) {
        if (token.equals("123-123")) {
            return Response.ok(trackService.getTracks(id), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(403).build();
        }
    }
}
