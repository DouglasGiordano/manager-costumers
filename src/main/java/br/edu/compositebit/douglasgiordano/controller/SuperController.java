package br.edu.compositebit.douglasgiordano.controller;

import br.edu.compositebit.douglasgiordano.model.entities.exception.BadParameterException;
import lombok.Data;

@Data
public abstract class SuperController {

    public abstract void initApi();

    public Integer getParamInt(String value) throws BadParameterException {
        try{
            Integer number = Integer.parseInt(value);
            return number;
        }catch (NumberFormatException ex){
            throw new BadParameterException("The parameter value "+value+" is invalid.");
        }
    }
}
