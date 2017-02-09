package controllers;

import models.User;
import play.Logger;
import play.libs.Json;
import play.mvc.*;
import utils.UserBodyParser;
import utils.VerboseAction;

import java.util.ArrayList;

public class UserController extends Controller {
    static private ArrayList<User> usersList = new ArrayList<>();

    @With(VerboseAction.class)
    public Result getUsers() {
        return ok(Json.toJson(usersList));
    }

    public Result getUserByIndex(Long id) {
        if (!usersList.isEmpty() &&  id <= usersList.size()) {
            return ok(Json.toJson(usersList.get(id.intValue())));
        }
        else {
            Logger.info("Id not found: " + id);
            return notFound();
        }
    }

    @BodyParser.Of(UserBodyParser.class)
    public Result updateUserByIndex(Long id) {
        Http.RequestBody body = request().body();
        User user = body.as(User.class);

        try {
            usersList.set(id.intValue(), user);
            return ok(Json.toJson(usersList.get(id.intValue())));
        } catch (Exception e) {
            Logger.error("Error updating user: " + e.toString());
            return internalServerError();
        }
    }

    @BodyParser.Of(UserBodyParser.class)
    public Result insertUser() {
        Http.RequestBody body = request().body();
        User user = body.as(User.class);

        usersList.add(user);

        Logger.info("Got body: " + Json.toJson(user));

        return ok("Got name: " + user.getName() + " and age: " + user.getAge());
    }

    public Result removeUseByIndex(Long id) {
        try {
            usersList.remove(id.intValue());
            return ok();
        } catch (Exception e) {
            Logger.error("Error removing user: " + e.toString());
            return internalServerError();
        }
    }
}