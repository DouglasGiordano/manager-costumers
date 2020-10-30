package br.edu.compositebit.douglasgiordano.controller;

import br.edu.compositebit.douglasgiordano.model.entities.Costumer;
import br.edu.compositebit.douglasgiordano.service.AddressesService;
import br.edu.compositebit.douglasgiordano.service.CostumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Inject;
import lombok.extern.java.Log;

import java.util.ArrayList;

import static spark.Spark.*;

@Log
public class Application {

    @Inject
    private CostumerService costumerService;

    @Inject
    private AddressesService addressesService;

    @Inject
    private ObjectMapper objectMapper;

    void run(final int port) {
        port(port);

        before("/*", (req, res) -> log.info(String.format("%s: %s", req.requestMethod(), req.uri())));

        get(Path.COSTUMERS, (req, res) -> {
            ArrayList<Costumer> costumers = this.costumerService.get();
            return objectMapper.writeValueAsString(costumers);
        });

        after("/*", (req, res) -> log.info(res.body()));
    }

    public static void main(final String... args) {
        Guice.createInjector(new GuiceModule())
                .getInstance(Application.class)
                .run(8080);
    }
}
