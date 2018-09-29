package login;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(RequestLogin requestLogin) {
        if (requestLogin.getUser().equals("klaas") && requestLogin.getPassword().equals("test")) {
            ResponseLogin responseLogin = new ResponseLogin();
            responseLogin.setUser(requestLogin.getUser());
            responseLogin.setToken("1234");
            return Response.ok(responseLogin, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(401).build();
        }
    }


}
