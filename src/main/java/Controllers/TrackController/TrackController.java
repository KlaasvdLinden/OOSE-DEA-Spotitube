package Controllers.TrackController;

import Service.TrackService;
import Service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists/{id}/tracks")
public class TrackController {

    @Inject
    TrackService trackService;

    @Inject
    private UserService userService;


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@QueryParam("token") String token, @PathParam("id") int id) {
        if (userService.rightToken(token)) {
            return Response.ok(trackService.getTracks(id), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(403).build();
        }
    }
}
