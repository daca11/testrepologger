package com.dalexa.presentation.controllers;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Named;

/**
 * Created by David
 */
@Named
@ManagedBean
public class IndexController {
    private String hello;

    @PostConstruct
    public void initialize(){
        this.hello = "Hello world!";
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}
