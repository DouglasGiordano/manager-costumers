package br.edu.compositebit.douglasgiordano.controller;

import br.edu.compositebit.douglasgiordano.controller.view.CostumerView;
import br.edu.compositebit.douglasgiordano.model.entities.Costumer;
import br.edu.compositebit.douglasgiordano.model.entities.Message;
import br.edu.compositebit.douglasgiordano.dao.filter.CostumerFilter;
import br.edu.compositebit.douglasgiordano.service.CostumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import lombok.Data;

import java.util.ArrayList;

import static spark.Spark.*;
import static spark.Spark.delete;

@Data
public class CostumerController extends SuperController{

    @Inject
    private CostumerService costumerService;

    @Inject
    private ObjectMapper objectMapper;

    @Override
    public void initApi() {
        get(Path.COSTUMERS, (req, res) -> {
            CostumerFilter filter = new CostumerFilter(req.queryMap().toMap());
            res.type("application/json");
            ArrayList<Costumer> costumers = this.costumerService.get(filter);
            return objectMapper.writeValueAsString(costumers);
        });

        get(Path.COSTUMERS+"/:id", (req, res) -> {
            res.type("application/json");
            String id = req.params("id");
            Costumer costumer = this.costumerService.get(Integer.parseInt(id));
            return objectMapper.writeValueAsString(costumer);
        });

        post(Path.COSTUMERS, (req, res) -> {
            res.type("application/json");
            Costumer c = objectMapper.readerWithView(CostumerView.CREATE.class).forType(Costumer.class).readValue(req.body());
            Costumer costumer = this.costumerService.add(c);
            return objectMapper.writerWithView(CostumerView.CREATE.class).writeValueAsString(costumer);
        });

        put(Path.COSTUMERS+"/:id", (req, res) -> {
            res.type("application/json");
            String id = req.params("id");
            Costumer c = objectMapper.readerWithView(CostumerView.CREATE.class).forType(Costumer.class).readValue(req.body());
            Costumer costumer = this.costumerService.update(Integer.parseInt(id), c);
            return objectMapper.writerWithView(CostumerView.CREATE.class).writeValueAsString(costumer);
        });

        delete(Path.COSTUMERS+"/:id", (req, res) -> {
            res.type("application/json");
            String id = req.params("id");
            this.costumerService.delete(Integer.parseInt(id));
            return objectMapper.writeValueAsString(new Message("The costumer has been deleted!"));
        });
    }
}
