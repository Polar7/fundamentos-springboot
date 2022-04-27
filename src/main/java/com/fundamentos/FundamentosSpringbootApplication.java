package com.fundamentos;

import com.fundamentos.bean.MyBean;
import com.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosSpringbootApplication implements CommandLineRunner {

    private ComponentDependency componentDependency;

    private MyBean myBean;

    private MyBeanWithDependency myBeanWithDependency;

    @Autowired
    public FundamentosSpringbootApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
    }

    public static void main(String[] args) {
        SpringApplication.run(FundamentosSpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        componentDependency.saludar();
        myBean.println();
        myBeanWithDependency.printWithDependency();
    }
}
