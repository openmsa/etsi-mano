package com.ubiqube.etsi.mano.em.v271.controller.vnfind;


public class ApiException extends Exception{
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
