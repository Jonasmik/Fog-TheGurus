package com.yourcompany.api.intergration;

import com.yourcompany.api.Fog;
import com.yourcompany.api.facades.*;
import com.yourcompany.api.factories.CarportFactory;
import com.yourcompany.api.factories.CustomerFactory;
import com.yourcompany.api.factories.PreOrderFactory;
import com.yourcompany.api.factories.UserFactory;
import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.carport.CarportRepository;
import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.customer.CustomerRepository;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.preorder.PreOrderRepository;
import com.yourcompany.domain.salesman.SalesmanRepository;
import com.yourcompany.domain.shed.ShedRepository;
import com.yourcompany.domain.user.User;
import com.yourcompany.domain.user.UserRepository;
import com.yourcompany.exceptions.carport.CarportValidations;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.order.PreOrderValidationError;
import com.yourcompany.exceptions.user.CustomerValidation;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.exceptions.user.UserValidationError;
import com.yourcompany.infrastructure.database.*;
import com.yourcompany.infrastructure.dbsetup.Database;
import com.yourcompany.infrastructure.dbsetup.Migrate;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.*;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@Tag("integration-test")
public class MainTest {

    Fog api;

    static void resetTestDatabase() {
        //reset test database
        String URL = "jdbc:mysql://localhost:3306/?serverTimezone=CET";
        String USER = "fogdbtest";

        InputStream stream = MainTest.class.getClassLoader().getResourceAsStream("init.sql");
        if (stream == null) {
            System.out.println("Migration file, does not exist: ");
            throw new RuntimeException("init.sql");
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, null)) {
            conn.setAutoCommit(false);
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setStopOnError(true);
            runner.runScript(new BufferedReader(new InputStreamReader(stream)));
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Done running migration");
    }


    @BeforeEach
    void setupAPI() {
        resetTestDatabase();
        Database db = new Database("jdbc:mysql://localhost:3306/fogdbtest?serverTimezone=CET", "fogdbtest");


        UserRepository userRepo = new DBUserRepository(db);
        UserFacade userFacade = new UserFacade(userRepo);

        CarportRepository carportRepo = new DBCarportRepository(db);
        CarportFacade carportFacade = new CarportFacade(carportRepo);

        ShedRepository shedRepository = new DBShedRepository(db);
        ShedFacade shedFacade = new ShedFacade(shedRepository);

        CustomerRepository customerRepository = new DBCustomerRepository(db);
        CustomerFacade customerFacade = new CustomerFacade(customerRepository);

        PreOrderRepository preOrderRepository = new DBPreOrderRepository(db);
        PreOrderFacade preOrderFacade = new PreOrderFacade(preOrderRepository);

        SalesmanRepository salesmanRepository = new DBSalesmanRepository(db);
        SalesmanFacade salesmanFacade = new SalesmanFacade(salesmanRepository);

        api = new Fog(userFacade, carportFacade, shedFacade, customerFacade, preOrderFacade, salesmanFacade);
    }

    @Nested
    @DisplayName("User story one")
    class UserStoryOne {

        @Test
        @DisplayName("User repository: Should create and login user")
        void userRepository_ShouldCreateAndLoginUser() throws UserValidationError {
            //A user should be able to log in
            // Setup step 1: Create user

            UserFactory userFactory = new UserFactory();

            userFactory.setEmail("tobias.zimmer@hotmail.com");
            userFactory.setPassword("1234");
            userFactory.setName("Tobias");
            userFactory.setCity("Lyngby");
            userFactory.setZip("2800");
            userFactory.setAddress("Borgevej");

            api.getUserFacade().createUser(userFactory);

            //action
            // login user
            User loggedInUser = api.getUserFacade().authorizeUser(userFactory.getEmail(), userFactory.getPassword());

            //Test
            assertEquals(userFactory.getEmail(), loggedInUser.getEmail());
        }
    }

    @Nested
    @DisplayName("User story two")
    class UserStoryTwo {

        UserFactory userFactory;
        CustomerFactory customerFactory;
        CarportFactory carportFactory;

        @BeforeEach
        void setup() throws CustomerValidation, CarportValidations {
            userFactory = new UserFactory();

            userFactory.setEmail("tobias.zimmer@hotmail.com");
            userFactory.setPassword("1234");
            userFactory.setName("Tobias");
            userFactory.setCity("Lyngby");
            userFactory.setZip("2800");
            userFactory.setAddress("Borgevej");

            customerFactory = new CustomerFactory();
            customerFactory.setUserid("1");
            customerFactory.setEmail("tobias.zimmer@hotmail.com");
            customerFactory.setCity("Lyngby");
            customerFactory.setZipcode("2800");
            customerFactory.setAdress("Borgevej");
            customerFactory.setName("Tobias");

            carportFactory = new CarportFactory();
            carportFactory.setRoofAngle("25");
            carportFactory.setRoof("Green roof");
            carportFactory.setLength("780");
            carportFactory.setWidth("750");

        }

        @Test
        @DisplayName("Preorder: Should create a valid preorder")
        void preOrder_ShouldCreateAValidPreOrder() throws NoSuchCustomerExists,
                UserValidationError, NoSuchCarportExists, NoSuchPreOrderExists {


            User user = api.getUserFacade().createUser(userFactory);


            Customer customer = api.getCustomerFacade().createCustomer(customerFactory);

            assertEquals(user.getEmail(), customer.getEmail());

            Carport carport = api.getCarportFacade().createCarport(carportFactory);

            PreOrderFactory preOrderFactory = new PreOrderFactory();
            preOrderFactory.setCustomerId(customer.getId());
            preOrderFactory.setCarportId(carport.getId());

            PreOrder preOrder = api.getPreOrderFacade().createPreOrder(preOrderFactory);

            assertEquals(carport.getId(), preOrder.getCarportId());
            assertEquals(customer.getId(), preOrder.getCustomerId());
        }
    }
}
