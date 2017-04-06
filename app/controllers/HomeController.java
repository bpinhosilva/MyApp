package controllers;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import redis.clients.jedis.JedisPool;

import javax.inject.Inject;
import java.io.File;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    @Inject
    private JedisPool jedisPool;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(new java.io.File("/public/index.html"));
    }

    public Result redirect() {
        return redirect("/");
    }

    public Result catchAll(String path) {
        Logger.info("Inside catch all");

        return ok(new File("/public/index.html")).as("text/html");
    }

    public Result cache() {
        if (jedisPool.getResource().get("teste") == null) {
            jedisPool.getResource().set("teste", new String("bruno"));
        }
        else {
            Logger.info(jedisPool.getResource().get("teste"));
        }
        return ok();
    }


}