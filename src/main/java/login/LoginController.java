package login;
import TestData.TestData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
    TestData testData = new TestData();
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
