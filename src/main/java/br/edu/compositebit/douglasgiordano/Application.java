package br.edu.compositebit.douglasgiordano;

import br.edu.compositebit.douglasgiordano.controller.AddressController;
import br.edu.compositebit.douglasgiordano.controller.CustomerController;
import br.edu.compositebit.douglasgiordano.controller.GuiceModule;
import br.edu.compositebit.douglasgiordano.model.entities.exception.ApiError;
import br.edu.compositebit.douglasgiordano.model.entities.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Inject;
import lombok.extern.java.Log;
import org.eclipse.jetty.http.HttpStatus;

import java.util.logging.Level;

import static spark.Spark.exception;
import static spark.Spark.port;

@Log
public class Application {

    @Inject
    private CustomerController customerController;

    @Inject
    private AddressController addressController;

    @Inject
    private ObjectMapper objectMapper;

    public static void main(final String... args) {
        Guice.createInjector(new GuiceModule())
                .getInstance(Application.class)
                .run(8080);
    }

    public void run(final int port) {
        port(port);
        this.customerController.initApi();
        this.addressController.initApi();
        this.initHandlerException();
    }

    public void initHandlerException() {
        exception(Exception.class, (e, req, res) -> {
            res.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
            res.type("application/json");
            try {
                res.body(objectMapper.writeValueAsString(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR_500, e)));
            } catch (JsonProcessingException jsonProcessingException) {
                res.body(e.getMessage());
            }
            log.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
        });

        exception(EntityNotFoundException.class, (e, req, res) -> {
            res.status(HttpStatus.NOT_FOUND_404);
            res.type("application/json");
            try {
                res.body(objectMapper.writeValueAsString(new ApiError(HttpStatus.NOT_FOUND_404, e)));
            } catch (JsonProcessingException jsonProcessingException) {
                res.body(e.getMessage());
            }
        });
    }
}
