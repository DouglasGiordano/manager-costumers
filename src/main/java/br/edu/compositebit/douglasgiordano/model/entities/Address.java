package br.edu.compositebit.douglasgiordano.model.entities;

import br.edu.compositebit.douglasgiordano.controller.view.AddressView;
import br.edu.compositebit.douglasgiordano.controller.view.CostumerView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import lombok.experimental.Accessors;
import org.jdbi.v3.core.mapper.Nested;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Address {
    @JsonView({CostumerView.CREATE.class, AddressView.DEFAULT.class})
    private int id;

    @JsonView({CostumerView.CREATE.class, AddressView.CREATE.class})
    private EnumState state;

    @JsonView({CostumerView.CREATE.class, AddressView.CREATE.class})
    private String city;

    @JsonView({CostumerView.CREATE.class, AddressView.CREATE.class})
    private String neighborhood;

    @JsonView({CostumerView.CREATE.class, AddressView.CREATE.class})
    private String zipCode;

    @JsonView({CostumerView.CREATE.class, AddressView.CREATE.class})
    private String street;

    @JsonView({CostumerView.CREATE.class, AddressView.CREATE.class})
    private String number;

    @JsonView({CostumerView.CREATE.class, AddressView.CREATE.class})
    private String additionalInformation;

    @JsonView({CostumerView.CREATE.class, AddressView.CREATE.class})
    private boolean main;

    private Integer costumer;
}
