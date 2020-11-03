package br.edu.compositebit.douglasgiordano.controller;

/**
 * @author Douglas Montanha Giordano
 * Class responsible for defining paths.
 * CRUD for Customer
 * CRUD for Addresses
 */
public class Path {
    public static final String PATH = "/api/v1";
    public static final String CUSTOMERS = PATH + "/customers";
    public static final String ADDRESSES = CUSTOMERS + "/:id/addresses";
}
