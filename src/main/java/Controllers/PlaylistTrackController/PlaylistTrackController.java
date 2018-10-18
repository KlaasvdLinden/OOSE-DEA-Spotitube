package Controllers.PlaylistTrackController;

import Domain.Track.Track;
import Service.PlaylistTrack.IPlaylistTrackService;
import Service.PlaylistTrack.PlaylistTrackService;
import Service.User.IUserService;
import Service.User.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists/{id}/tracks")
public class PlaylistTrackController {

    @Inject
    private IPlaylistTrackService playlistTrackService;

    @Inject
    private IUserService userService;


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@QueryParam("token") String token, @PathParam("id") int id) {
        if (!userService.rightToken(token)) {
            return Response.status(403).build();
        }
        return Response.ok(playlistTrackService.getTracks(id), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrack(@QueryParam("token") String token, @PathParam("id") int id, Track track) {
        if (!userService.rightToken(token)) {
            return Response.status(403).build();
        }
        playlistTrackService.addTrack(id, track.getId(), track.isOfflineAvailable());
        return Response.ok(playlistTrackService.getTracks(id), MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{trackid}")
    public Response deleteTrack(@QueryParam("token") String token, @PathParam("id") int playlistID, @PathParam("trackid") int trackID) {
        if (!userService.rightToken(token)) {
            return Response.status(403).build();
        }
        playlistTrackService.deleteTrack(playlistID, trackID);
        return Response.ok(playlistTrackService.getTracks(playlistID), MediaType.APPLICATION_JSON).build();

    }
}
