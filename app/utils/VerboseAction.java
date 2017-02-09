package utils;

import play.Logger;
import play.mvc.Action.Simple;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class VerboseAction extends Simple {

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        Logger.info("Calling action for {}", ctx);

        Logger.info(ctx.session().toString());

        if (ctx.session().isEmpty()) {
            return CompletableFuture.supplyAsync(() -> unauthorized());
        }
        else {
            return delegate.call(ctx);
        }
    }
}