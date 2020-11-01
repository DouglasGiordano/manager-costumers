package br.edu.compositebit.douglasgiordano.model.entities;

import br.edu.compositebit.douglasgiordano.controller.view.CostumerView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.beans.ConstructorProperties;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Costumer {
    @JsonView(CostumerView.CREATE.class)
    private int id;

    @JsonView(CostumerView.CREATE.class)
    private String name;

    @JsonView(CostumerView.CREATE.class)
    private String email;

    @JsonView(CostumerView.CREATE.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @JsonView(CostumerView.CREATE.class)
    private String cpf;

    @JsonView(CostumerView.CREATE.class)
    private EnumGender gender;

    @JsonView({CostumerView.CREATE.class})
    @JsonProperty(value = "address")
    private Address mainAddress;

    @JsonView(CostumerView.CREATE.class)
    private List<Address> addresses;

}
