package Controllers.PlaylistTrackController;

import Domain.Track.Track;
import Service.PlaylistTrackService;
import Service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists/{id}/tracks")
public class PlaylistTrackController {

    @Inject
    PlaylistTrackService playlistTrackService;

    @Inject
    private UserService userService;


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@QueryParam("token") String token, @PathParam("id") int id) {
        if (userService.rightToken(token)) {
            return Response.ok(playlistTrackService.getTracks(id), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(403).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrack(@QueryParam("token") String token, @PathParam("id") int id, Track track){
        if(userService.rightToken(token)){
            playlistTrackService.addTrack(id, track.getId(), track.isOfflineAvailable());
            return Response.ok(playlistTrackService.getTracks(id), MediaType.APPLICATION_JSON).build();
        } else{
            return Response.status(403).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{trackid}")
    public Response deleteTrack(@QueryParam("token") String token, @PathParam("id") int playlistID, @PathParam("trackid") int trackID){
        if(userService.rightToken(token)){
            playlistTrackService.deleteTrack(playlistID, trackID);
            return Response.ok(playlistTrackService.getTracks(playlistID), MediaType.APPLICATION_JSON).build();
        } else{
            return Response.status(403).build();
        }
    }
}
