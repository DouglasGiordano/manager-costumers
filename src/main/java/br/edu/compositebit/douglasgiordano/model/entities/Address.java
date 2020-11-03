package br.edu.compositebit.douglasgiordano.model.entities;

import br.edu.compositebit.douglasgiordano.controller.view.AddressView;
import br.edu.compositebit.douglasgiordano.controller.view.CustomerView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Address {
    @JsonView({CustomerView.DEFAULT.class, AddressView.DEFAULT.class})
    private int id;

    @JsonView({CustomerView.CREATE.class, AddressView.CREATE.class})
    private EnumState state;

    @JsonView({CustomerView.CREATE.class, AddressView.CREATE.class})
    private String city;

    @JsonView({CustomerView.CREATE.class, AddressView.CREATE.class})
    private String neighborhood;

    @JsonView({CustomerView.CREATE.class, AddressView.CREATE.class})
    private String zipCode;

    @JsonView({CustomerView.CREATE.class, AddressView.CREATE.class})
    private String street;

    @JsonView({CustomerView.CREATE.class, AddressView.CREATE.class})
    private String number;

    @JsonView({CustomerView.CREATE.class, AddressView.CREATE.class})
    private String additionalInformation;

    @JsonView({CustomerView.CREATE.class, AddressView.CREATE.class})
    private boolean main;

    @JsonView({CustomerView.DEFAULT.class, AddressView.DEFAULT.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonView({CustomerView.DEFAULT.class, AddressView.DEFAULT.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")

    private LocalDateTime updateAt;

    private Integer customer;
}
