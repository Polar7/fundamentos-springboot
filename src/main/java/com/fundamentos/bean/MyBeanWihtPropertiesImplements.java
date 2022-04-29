package com.fundamentos.bean;

public class MyBeanWihtPropertiesImplements implements MyBeanWithProperties{

    private String name;
    private String apellido;

    public MyBeanWihtPropertiesImplements(String name, String apellido) {
        this.name = name;
        this.apellido = apellido;
    }

    @Override
    public String function() {
        return name +"-"+apellido;
    }
}
