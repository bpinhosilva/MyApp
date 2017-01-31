package controllers;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

public class LoginController extends Controller {
    public Result login() {
        session("user", "email@test.com");

        return ok();
    }

    public Result logout() {
        Logger.info(session("user") + " logged out.");

        session().clear();

        return ok();
    }

    public Result loggedin() {
        if (session("user") != null) {
            return ok(session("user"));
        }
        else {
            return notFound();
        }
    }
}
