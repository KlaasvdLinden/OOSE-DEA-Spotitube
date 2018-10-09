package Controllers.PlaylistController;


import Dao.Track.TrackDAO;
import Domain.Playlist.Playlist;
import Service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/playlists")
public class PlaylistController {
    @Inject
    private Service.PlaylistService playlistService;

    @Inject
    private UserService userService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayLists(@QueryParam("token") String token) {
        if (userService.rightToken(token)) {
            return Response.ok(playlistService.getPlaylist(token), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(403).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response editPlaylist(@QueryParam("token") String token, @PathParam("id") int id, Playlist playlist) {
        if (userService.rightToken(token)) {
            playlistService.editPlaylist(id, playlist.getName());
            return Response.ok(playlistService.getPlaylist(token), MediaType.APPLICATION_JSON).build();
        } else{
            return Response.status(403).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, Playlist playlist){
        if(userService.rightToken(token)){
            playlistService.addPlaylist(playlist.getName());
            return Response.ok(playlistService.getPlaylist(token), MediaType.APPLICATION_JSON).build();
        }
        else{
            return Response.status(403).build();
        }
    }
}
