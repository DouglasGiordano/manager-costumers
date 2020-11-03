package br.edu.compositebit.douglasgiordano.model.entities;

import br.com.caelum.stella.bean.validation.CPF;
import br.edu.compositebit.douglasgiordano.controller.view.CustomerView;
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
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @JsonView(CustomerView.DEFAULT.class)
    private int id;

    @JsonView(CustomerView.CREATE.class)
    @NotNull(message = "Name cannot be null")
    @NonNull
    private String name;

    @JsonView(CustomerView.CREATE.class)
    @Email(message = "Email should be valid!")
    private String email;

    @JsonView(CustomerView.CREATE.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @JsonView(CustomerView.CREATE.class)
    @CPF
    private String cpf;

    @JsonView(CustomerView.CREATE.class)
    private EnumGender gender;

    @JsonView(CustomerView.DEFAULT.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonView(CustomerView.DEFAULT.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updateAt;

    @JsonView({CustomerView.CREATE.class})
    @JsonProperty(value = "address")
    private Address mainAddress;

    @JsonView(CustomerView.DEFAULT.class)
    private List<Address> addresses;

}
