package dataproviders;

import org.testng.annotations.*;

/**
 * LoginPageDataProvider class contains data provider for LoginPage class
 */
public class LoginPageDataProvider {

    /**
     * users method returns an array of users
     * @return an array of users
     */
    @DataProvider(name = "users")
    public Object[][] users() {
        return new Object[][] {
                {"standard_user"},
                {"visual_user"},
                {"problem_user"},
                {"performance_glitch_user"},
                {"error_user"}
        };
    }
}