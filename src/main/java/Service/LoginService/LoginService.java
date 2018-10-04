package Service.LoginService;
import Dao.Token.TokenDAO;
import Domain.Login.RequestLogin;
import Dao.TestData;
import Domain.Login.ResponseLogin;
import Manager.UserManager;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginService {
    @Inject
    private UserManager userManager;

    private TokenDAO td = new TokenDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(RequestLogin requestLogin) {
        RequestLogin user = userManager.getRequestLogin(requestLogin.getUser());
        if (requestLogin.getUser().equals(user.getUser()) && requestLogin.getPassword().equals(user.getPassword())) {
            ResponseLogin rl = td.generateOrUpdateToken(user.getUser());
            return Response.ok(rl, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(401).build();
        }
    }


}
