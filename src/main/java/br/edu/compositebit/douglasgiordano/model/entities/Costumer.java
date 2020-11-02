package br.edu.compositebit.douglasgiordano.model.entities;

import br.com.caelum.stella.bean.validation.CPF;
import br.edu.compositebit.douglasgiordano.controller.view.CostumerView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Costumer {
    @JsonView(CostumerView.CREATE.class)
    private int id;

    @JsonView(CostumerView.CREATE.class)
    @NotNull(message = "Name cannot be null")
    @NonNull
    private String name;

    @JsonView(CostumerView.CREATE.class)
    @Email(message = "Email should be valid!")
    private String email;

    @JsonView(CostumerView.CREATE.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @JsonView(CostumerView.CREATE.class)
    @CPF
    private String cpf;

    @JsonView(CostumerView.CREATE.class)
    private EnumGender gender;

    @JsonView({CostumerView.CREATE.class})
    @JsonProperty(value = "address")
    private Address mainAddress;

    @JsonView(CostumerView.CREATE.class)
    private List<Address> addresses;

}
