package Service.LoginService;
import Domain.Login.RequestLogin;
import Dao.TestData;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginService {
    @Inject
    TestData testData;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(RequestLogin requestLogin) {
        if (requestLogin.getUser().equals(testData.getUSER()) && requestLogin.getPassword().equals(testData.getPASSWORD())) {
            testData.initResponseLogin();
            return Response.ok(testData.getRl(), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(401).build();
        }
    }


}
