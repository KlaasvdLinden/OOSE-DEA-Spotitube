package Controllers.TrackController;

import Exceptions.AccesNotAllowedException;
import Service.TrackService;
import Service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {

    @Inject
    TrackService trackService;

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracks(@QueryParam("forPlaylist") int playlistID, @QueryParam("token") String token){
        try{
            userService.rightToken(token);
            return Response.ok(trackService.getAll(playlistID), MediaType.APPLICATION_JSON).build();
        } catch(AccesNotAllowedException e){
            return Response.status(403).build();

        }

    }
}
