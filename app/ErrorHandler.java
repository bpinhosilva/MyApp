import com.fasterxml.jackson.databind.node.ObjectNode;
import play.*;
import play.api.OptionalSourceMapper;
import play.api.UsefulException;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.libs.Json;
import play.mvc.Http.*;
import play.mvc.*;

import javax.inject.*;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.notFound;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

@Singleton
public class ErrorHandler extends DefaultHttpErrorHandler {

    @Inject
    public ErrorHandler(Configuration configuration, Environment environment,
                        OptionalSourceMapper sourceMapper, Provider<Router> routes) {
        super(configuration, environment, sourceMapper, routes);
    }

    protected CompletionStage<Result> onProdServerError(RequestHeader request, UsefulException exception) {
        return CompletableFuture.completedFuture(
                Results.internalServerError("A server error occurred: " + exception.getMessage())
        );
    }

    protected CompletionStage<Result> onForbidden(RequestHeader request, String message) {
        return CompletableFuture.completedFuture(
                Results.forbidden("You're not allowed to access this resource.")
        );
    }

    @Override
    protected CompletionStage<Result> onNotFound(RequestHeader request, String message) {
        Logger.error("ErrorHandler - Not found: " + request.toString());

        if (request.path().contains("/api")) {
            ObjectNode result = Json.newObject();

            result.put("msg", "Not found");

            return CompletableFuture.completedFuture(notFound(result));
        }

        //return CompletableFuture.completedFuture(redirect("/"));

        return CompletableFuture.completedFuture(ok(new File("public/index.html")).as("text/html"));
    }

    @Override
    protected CompletionStage<Result> onBadRequest(RequestHeader request, String message) {
        Logger.error("Bad request: " + request.toString());

        if (request.path().contains("/api")) {
            ObjectNode result = Json.newObject();

            result.put("msg", "Bad request");

            return CompletableFuture.completedFuture(notFound(result));
        }

        return CompletableFuture.completedFuture(redirect("/"));
    }
}