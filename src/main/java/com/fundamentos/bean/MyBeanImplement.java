package com.fundamentos.bean;

public class MyBeanImplement implements MyBean {

    @Override
    public void println() {
        System.out.println("Hola desde mi implementacion propia del bean");
    }
}
