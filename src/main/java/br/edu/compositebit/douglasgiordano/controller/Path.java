package br.edu.compositebit.douglasgiordano.controller;

public class Path {
    public static final String PATH = "/api/v1";
    public static final String COSTUMERS = PATH + "/costumers";
    public static final String ADDRESSES = COSTUMERS + "/:id/addresses";
}
