package com.fundamentos;

import com.fundamentos.bean.MyBean;
import com.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.component.ComponentDependency;
import com.fundamentos.entity.User;
import com.fundamentos.pojo.UserPojo;
import com.fundamentos.repository.UserRepository;
import com.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosSpringbootApplication implements CommandLineRunner {

    private final Log logger = LogFactory.getLog(FundamentosSpringbootApplication.class);

    private ComponentDependency componentDependency;

    private MyBean myBean;

    private MyBeanWithDependency myBeanWithDependency;

    private MyBeanWithProperties myBeanWithProperties;

    private UserPojo userPojo;

    private UserRepository userRepository;

    private UserService userService;

    @Autowired
    public FundamentosSpringbootApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(FundamentosSpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //ejemplosAnteriores();
        saveUsersInDataBase();
        getInformationJpqlFromUser();
        saveWithErrorTransactional();
    }

    private void getInformationJpqlFromUser(){
        //Jpql
        logger.info("Usuario con el metodo findByUserEmail: " +
                userRepository.findByUserEmail("julie@domain.com").
                        orElseThrow(() -> new RuntimeException("NO se encontró el usuario")));

        userRepository.findAndSort("user", Sort.by("id").descending())
                .forEach(user -> logger.info("Usuario con metodo sort: "+ user));

        //Query methods
        userRepository.findByName("John").forEach(user -> logger.info("Usuario con query method: " + user));
        logger.info("Usuario con query method findByEmailAndName: " + userRepository.findByEmailAndName("daniela@domain.com", "Daniela")
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        userRepository.findByNameLike("%user%")
                .forEach(user -> logger.info("Usuario findByNameLike: " + user));

        userRepository.findByNameOrEmail(null, "user10@domain.com")
                .forEach(user -> logger.info("Usuario findByNameOrEmail: " + user));

        userRepository.findByBirthDateBetween(LocalDate.of(2021, 3, 1), LocalDate.of(2021, 4, 2))
                .forEach(user -> logger.info("Usuario con intervalo de fechas: " + user));

        userRepository.findByNameLikeOrderByIdDesc("%user%")
                .forEach(user -> logger.info("Usuario encontrado con like y ordenado: " + user));

        userRepository.findByNameContainingOrderByIdDesc("user")
                .forEach(user -> logger.info("Usuario encontrado con CONTAINING y ordenado: " + user));

        //JPQL named parameters
        logger.info("El usuario a partir del named parameter es; " +
                userRepository.getAllByBirthDareAndEmail(LocalDate.of(2021, 7, 30), "daniela@domain.com"));
    }

    private void saveWithErrorTransactional(){
        User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
        User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
        User test3 = new User("TestTransactional3", "TestTransactional3@domain.com", LocalDate.now());
        User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

        List<User> users = Arrays.asList(test1, test2, test3, test4);

        try {
            userService.saveTransactional(users);
        } catch (Exception e){
            logger.error("Esta es una excepcion dentro del metodo transaccional: " + e);
        }

        userService.getAllUsers().forEach(user -> logger.info("Este es el usuario dentro del metodo transaccional: " + user));
    }

    private void saveUsersInDataBase () {
        User user1 = new User("John", "john@domain.com", LocalDate.of(2021,3,20));
        User user2 = new User("Julie", "julie@domain.com", LocalDate.of(2021,5,21));
        User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021,7,30));
        User user4 = new User("user4", "user4@domain.com", LocalDate.of(2021,07,15));
        User user5 = new User("user5", "user5@domain.com", LocalDate.of(2021,11,11));
        User user6 = new User("user6", "user6@domain.com", LocalDate.of(2021,2,17));
        User user7 = new User("user7", "user7@domain.com", LocalDate.of(2021,3,9));
        User user8 = new User("user8", "user8@domain.com", LocalDate.of(2021,4,5));
        User user9 = new User("user9", "user9@domain.com", LocalDate.of(2021,8,7));
        User user10 = new User("user10", "user10@domain.com", LocalDate.of(2021,1,14));
        List<User> listUser = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);
        //listUser.forEach(userRepository::save);
        userRepository.saveAll(listUser);
    }

    private void ejemplosAnteriores(){
        componentDependency.saludar();
        myBean.println();
        myBeanWithDependency.printWithDependency();
        System.out.println(myBeanWithProperties.function());
        System.out.println(userPojo.getEmail()+"-"+userPojo.getPassword());
        logger.error("Esto es un error");
    }
}
