import controller.AddressesController;
import controller.CostumerController;
import lombok.extern.java.Log;

import static spark.Spark.*;

@Log
public class AppMainSpark {
    public static void main(String[] args) {
        path("/api", () -> {
            //before("/*", (q, a) -> log.info("Received api call"));
            path("/costumers", () -> {
                post("/",       CostumerController.add);
                put("/:id",     CostumerController.change);
                delete("/:id",  CostumerController.delete);
                get("/",  CostumerController.find);
                get("/:id",  CostumerController.find);
            });
            path("/costumers/:id/addresses", () -> {
                post("/",       AddressesController.add);
                put("/:address_id",     AddressesController.change);
                delete("/:address_id",  AddressesController.delete);
                get("/",  AddressesController.find);
                get("/:address_id",  AddressesController.find);
            });
        });
    }
}
