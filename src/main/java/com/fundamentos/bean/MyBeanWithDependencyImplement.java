package com.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement  implements MyBeanWithDependency{

    private MyOperation myOperation;

    private final Log logger = LogFactory.getLog(MyBeanWithDependencyImplement.class);

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        logger.info("Hemos ingresado al metodo printWithDependency");
        int number = 1;
        logger.debug("El numero enviado como parametro a la dependencia operation es: " + number);
        System.out.println(myOperation.sum(number));
        System.out.println("Hola desde la implementacion de un bean con dependencia");
    }
}
