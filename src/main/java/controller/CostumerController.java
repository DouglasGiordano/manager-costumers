package controller;
import lombok.Data;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;
@Data
public class CostumerController extends SuperController{
    @Override
    public Object add(Request req, Response resp) {
        return null;
    }

    @Override
    public Object change(Request req, Response resp) {
        return null;
    }

    @Override
    public Object delete(Request req, Response resp) {
        return null;
    }

    @Override
    public Object find(Request req, Response resp) {
        return null;
    }
}
