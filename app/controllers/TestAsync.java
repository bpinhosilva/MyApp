package controllers;

import com.google.inject.Inject;
import models.User;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class TestAsync extends Controller {
    @Inject
    HttpExecutionContext ec;

    public CompletionStage<Result> index() {
        return CompletableFuture.supplyAsync(() -> intensiveComputation(), ec.current())
                .thenApply(user -> ok(Json.toJson(user)));
    }

    private User intensiveComputation() {
        return new User("Bruno", 27);
    }

}
