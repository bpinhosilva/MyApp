package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.Logger;
import play.libs.Json;
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
        if (!session().isEmpty()) {
            ObjectNode result = Json.newObject();

            session().forEach((key, value) -> result.put(key, value) );

            return ok(result);
        }
        else {
            return notFound();
        }
    }
}