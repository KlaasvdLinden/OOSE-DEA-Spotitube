package oose.dea.spotitube.klaasvanderlinden.Controllers.TrackController;

import oose.dea.spotitube.klaasvanderlinden.Service.Track.ITrackService;
import oose.dea.spotitube.klaasvanderlinden.Service.User.IUserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {

    @Inject
    private ITrackService trackService;

    @Inject
    private IUserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracks(@QueryParam("forPlaylist") int playlistID, @QueryParam("token") String token) {
        if (!userService.rightToken(token)) {
            return Response.status(403).build();
        }
        return Response.ok(trackService.getAll(playlistID), MediaType.APPLICATION_JSON).build();
    }
}
