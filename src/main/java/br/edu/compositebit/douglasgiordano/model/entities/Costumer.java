package br.edu.compositebit.douglasgiordano.model.entities;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Costumer {
    private Integer id;
    private String name;
    private String email;
    private LocalDateTime birthDate;
    private String cpf;
    private String gender;
}
