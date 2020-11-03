package br.edu.compositebit.douglasgiordano.controller;

import br.edu.compositebit.douglasgiordano.model.entities.exception.BadParameterException;
import lombok.Data;

/**
 * @author Douglas Montanha Giordano
 * Super class responsible for encapsulation and definition of attributes and methods in common among controllers.
 */
@Data
public abstract class SuperController {

    /**
     * Abstract method for api init.
     */
    public abstract void initApi();

    /**
     * Method for check and get parameters int.
     * @param value
     * @return
     * @throws BadParameterException
     */
    public Integer getParamInt(String value) throws BadParameterException {
        try {
            Integer number = Integer.parseInt(value);
            return number;
        } catch (NumberFormatException ex) {
            throw new BadParameterException("The parameter value " + value + " is invalid.");
        }
    }
}
