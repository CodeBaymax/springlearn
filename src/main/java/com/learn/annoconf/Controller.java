package com.learn.annoconf;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "Controller: Service = { " + service + " }";
    }
}
