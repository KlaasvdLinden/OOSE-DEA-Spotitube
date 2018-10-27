package oose.dea.spotitube.klaasvanderlinden.Controllers.LoginController;

import oose.dea.spotitube.klaasvanderlinden.Domain.Login.RequestLogin;
import oose.dea.spotitube.klaasvanderlinden.Domain.Login.ResponseLogin;
import oose.dea.spotitube.klaasvanderlinden.Service.User.IUserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
    @Inject
    private IUserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(RequestLogin requestLogin) {
        ResponseLogin responseLogin = userService.authenticate(requestLogin.getUser(), requestLogin.getPassword());

        if (responseLogin != null) {
            return Response.ok(responseLogin, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(401).build();
    }

}
