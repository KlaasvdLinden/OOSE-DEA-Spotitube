package oose.dea.spotitube.klaasvanderlinden.Controllers.PlaylistController;


import oose.dea.spotitube.klaasvanderlinden.Domain.Playlist.Playlist;
import oose.dea.spotitube.klaasvanderlinden.Service.Playlist.IPlaylistService;
import oose.dea.spotitube.klaasvanderlinden.Service.User.IUserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/playlists")
public class PlaylistController {
    @Inject
    private IPlaylistService playlistService;

    @Inject
    private IUserService userService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayLists(@QueryParam("token") String token) {
        if (!userService.rightToken(token)) {
            return Response.status(403).build();
        }
        return Response.ok(playlistService.getPlaylists(), MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response editPlaylist(@QueryParam("token") String token, @PathParam("id") int id, Playlist playlist) {
        if (!userService.rightToken(token)) {
            return Response.status(403).build();
        }
        playlistService.editPlaylist(id, playlist.getName());
        return Response.ok(playlistService.getPlaylists(), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, Playlist playlist) {
        if (!userService.rightToken(token)) {
            return Response.status(403).build();
        }
        playlistService.addPlaylist(playlist.getName());
        return Response.ok(playlistService.getPlaylists(), MediaType.APPLICATION_JSON).build();

    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        if (!userService.rightToken(token)) {
            return Response.status(403).build();
        }
        playlistService.deletePlaylist(id);
        return Response.ok(playlistService.getPlaylists(), MediaType.APPLICATION_JSON).build();
    }
}
