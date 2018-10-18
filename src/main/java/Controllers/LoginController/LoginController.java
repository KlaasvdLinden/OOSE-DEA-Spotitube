package Controllers.LoginController;

import Domain.Login.RequestLogin;
import Domain.Login.ResponseLogin;
import Service.User.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
    @Inject
    private UserService userService;

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
