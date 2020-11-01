package br.edu.compositebit.douglasgiordano.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime timestamp;
    private String message;

    public Message(String message){
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }
}
