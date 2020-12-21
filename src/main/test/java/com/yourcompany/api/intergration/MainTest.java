package com.yourcompany.api.intergration;

import com.yourcompany.api.Fog;
import com.yourcompany.api.facades.*;
import com.yourcompany.api.factories.*;
import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.carport.CarportRepository;
import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.customer.CustomerRepository;
import com.yourcompany.domain.material.MaterialPriceRepository;
import com.yourcompany.domain.offer.OfferRepository;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.preorder.PreOrderRepository;
import com.yourcompany.domain.salesman.Salesman;
import com.yourcompany.domain.salesman.SalesmanRepository;
import com.yourcompany.domain.shed.ShedRepository;
import com.yourcompany.domain.user.User;
import com.yourcompany.domain.user.UserRepository;
import com.yourcompany.exceptions.carport.CarportValidations;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.user.CustomerValidation;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.exceptions.user.NoSuchSalesmanExists;
import com.yourcompany.exceptions.user.UserValidationError;
import com.yourcompany.infrastructure.database.*;
import com.yourcompany.infrastructure.dbsetup.Database;
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

        MaterialPriceRepository materialPriceRepository = new DBMaterialPrice(db);
        MaterialPriceFacade materialPriceFacade = new MaterialPriceFacade(materialPriceRepository);

        OfferRepository offerRepository = new DBOfferRepository(db);
        OfferFacade offerFacade = new OfferFacade(offerRepository);

        api = new Fog(userFacade, carportFacade, shedFacade, customerFacade, preOrderFacade, salesmanFacade, materialPriceFacade, offerFacade,
            OrderFacade.getInstance());
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

    /*
    US-5:
    Som sælger vil jeg gerne kunne “assigne” mig selv på en forespørgsel hvis den ikke allerede er taget, sådan at jeg nemt kan holde kontakt med kunden.
     */
    @Nested
    @DisplayName("User story five")
    class UserStoryFive {

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
        @DisplayName("Preorder assign: Should assign a valid salesman to preorder")
        void preOrderAssign_ShouldAssignAValidSalesmanToPreOrder() throws UserValidationError, NoSuchSalesmanExists, NoSuchCarportExists, NoSuchCustomerExists, NoSuchPreOrderExists {

            //setup step one: create user
            User user = api.getUserFacade().createUser(userFactory);

            //setup step two: Create salesman
            SalesmanFactory salesmanFactory = new SalesmanFactory();
            salesmanFactory.setUserId(user.getId());

            Salesman salesman = api.getSalesmanFacade().createSalesman(salesmanFactory);

            //setup step three: create carportj
            Carport carport = api.getCarportFacade().createCarport(carportFactory);

            //setup step four: create customer
            Customer customer = api.getCustomerFacade().createCustomer(customerFactory);

            //setup step five: Create preorder
            PreOrderFactory preOrderFactory = new PreOrderFactory();
            preOrderFactory.setCarportId(carport.getId());
            preOrderFactory.setCustomerId(customer.getId());
            PreOrder preOrder = api.getPreOrderFacade().createPreOrder(preOrderFactory);

            //action: insert salesman into preorder
            api.getPreOrderFacade().takePreOrder(salesman.getId(), preOrder.getId());

            PreOrder takenPreOrder = api.getPreOrderFacade().findPreOrderById(preOrder.getId());

            //Test: if preorder is taken, salesman id is now equal to the taken preorder id
            assertEquals(salesman.getId(), takenPreOrder.getSalesmanId());

        }
    }

}
