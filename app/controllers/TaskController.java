package controllers;

import models.Task;
import play.Logger;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.TaskBodyParser;

public class TaskController extends Controller {

    public Result getTasks() {
        return ok(Json.toJson(Task.find.all()));
    }

    @BodyParser.Of(TaskBodyParser.class)
    public Result insertTask() {
        Http.RequestBody body = request().body();
        Task task = body.as(Task.class);

        if (task.getName() == null) {
            return badRequest(Json.newObject().put("error", "Name cannot be null"));
        }

        task.save();

        Logger.info("Got body: " + Json.toJson(task));

        return ok();
    }
}