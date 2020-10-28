package model;

import lombok.Data;

@Data
public class Path {
    private static String PATH = "api/v1";
    private static String CUSTOMERS = PATH + "/customers";
    private static String CUSTOMERS_ADDRESSES = PATH + "/{id}/addresses";
}
