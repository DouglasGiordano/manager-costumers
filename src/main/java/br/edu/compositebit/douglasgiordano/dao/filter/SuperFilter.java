package br.edu.compositebit.douglasgiordano.dao.filter;

import lombok.Data;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Stream;

/**
 * @author Douglas Montanha Giordano
 * Super class responsible for encapsulation and definition of attributes and methods in common among filter.
 */
@Data
public class SuperFilter {
    protected <T> T getObjectParam(T... param) {
        if (param == null) {
            return null;
        }
        return Stream.of(param).filter(Objects::nonNull).findFirst().orElse(null);
    }

    protected <T extends Enum<T>> T getEnumParam(Class<T> enumType, String... param) {
        if (param == null) {
            return null;
        }
        String value = Stream.of(param).filter(Objects::nonNull).findFirst().orElse(null);
        if (value == null) {
            return null;
        }
        return Enum.valueOf(enumType, value);
    }

    protected void setWhereString(StringJoiner builder, String field, String value) {
        builder.add("(:" + field + " IS NULL OR " + field + " like '%" + value + "%')");
    }

    protected void setWhereString(StringJoiner builder, String field, Enum value) {
        builder.add("(:" + field + " IS NULL OR " + field + " like '" + value + "'");
    }
}
