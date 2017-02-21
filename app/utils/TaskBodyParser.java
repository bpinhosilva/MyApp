package utils;

import akka.util.ByteString;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.Task;
import play.libs.F;
import play.libs.streams.Accumulator;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;


import java.util.concurrent.Executor;

public class TaskBodyParser implements BodyParser<Task> {
    private BodyParser.Json jsonParser;
    private Executor executor;

    @Inject
    public TaskBodyParser(BodyParser.Json jsonParser, Executor executor) {
        this.jsonParser = jsonParser;
        this.executor = executor;
    }

    @Override
    public Accumulator<ByteString, F.Either<play.mvc.Result, Task>> apply(Http.RequestHeader request) {
        Accumulator<ByteString, F.Either<Result, JsonNode>> jsonAccumulator = jsonParser.apply(request);

        return jsonAccumulator.map(resultOrJson -> {
            if (resultOrJson.left.isPresent()) {
                return F.Either.Left(resultOrJson.left.get());
            } else {
                JsonNode json = resultOrJson.right.get();
                try {
                    Task task = play.libs.Json.fromJson(json, Task.class);
                    return F.Either.Right(task);
                } catch (Exception e) {
                    return F.Either.Left(Results.badRequest(
                            "Unable to read Task from json: " + e.getMessage()));
                }
            }
        }, executor);
    }
}