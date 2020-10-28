package controller;

import spark.Request;
import spark.Response;
import spark.Route;

public abstract class SuperController <T extends Object> {

    public static Route add;
    public static Route change;
    public static Route delete;
    public static Route find;

    public void init(){
        add = new Route() {
            @Override
            public T handle(Request request, Response response) throws Exception {
                return null;
            }
        };

        change = new Route() {
            @Override
            public T handle(Request request, Response response) throws Exception {
                return null;
            }
        };

        delete = new Route() {
            @Override
            public T handle(Request request, Response response) throws Exception {
                return null;
            }
        };

        find = new Route() {
            @Override
            public T handle(Request request, Response response) throws Exception {
                return null;
            }
        };
    }

    public abstract T add(Request req, Response resp);
    public abstract T change(Request req, Response resp);
    public abstract T delete(Request req, Response resp);
    public abstract T find(Request req, Response resp);
}
