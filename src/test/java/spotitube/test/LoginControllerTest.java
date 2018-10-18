package spotitube.test;

import Controllers.LoginController.LoginController;
import Domain.Login.RequestLogin;
import Domain.Login.ResponseLogin;
import Service.User.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private UserService userService;


    @Test
    public void testLoginReturnsOk(){
        ResponseLogin responseLogin = new ResponseLogin();

        RequestLogin requestLogin = new RequestLogin();
        requestLogin.setUser("testuser");
        requestLogin.setPassword("testpassword");

        Mockito.when(userService.authenticate(Mockito.anyString(), Mockito.anyString())).thenReturn(responseLogin);

        Response response = loginController.login(requestLogin);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(responseLogin, response.getEntity());
    }
}
