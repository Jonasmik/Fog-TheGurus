package com.yourcompany.api.factories;

import com.yourcompany.exceptions.carport.CarportValidations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarportFactoryTest {


    CarportFactory factory;

    @BeforeEach
    void setupFactory() {
        factory = new CarportFactory();
    }

    @Nested
    @DisplayName("When the factory is full")
    class WhenTheFactoryIsFull {

        @BeforeEach
        void setupFullFactory() {
            try {
                factory.setLength("240");
                factory.setWidth("240");
                factory.setRoof("Green roof");
                factory.setRoofAngle("25");
            } catch (CarportValidations carportValidations) {
                carportValidations.printStackTrace();
            }
        }

        @Test
        @DisplayName("isValid should be true")
        void isValid_shouldBeTrue() {
            boolean isValid = factory.isValid();
            assertTrue(isValid);
        }

        @Test
        @DisplayName("getLength should give valid lenght")
        void getLength_shouldGiveValidLength() {
            assertEquals(240, factory.getLength());
        }

        @Test
        @DisplayName("getWidth should give valid width")
        void getLength_shouldGiveValidWidth() {
            assertEquals(240, factory.getWidth());
        }

        @Test
        @DisplayName("getRoof should give valid roof")
        void getLength_shouldGiveValidRoof() {
            assertEquals("Green roof", factory.getRoof());
        }

        @Test
        @DisplayName("getRoofAngle should give valid roof angle")
        void getLength_shouldGiveValidRoofAngle() {
            assertEquals(25, factory.getRoofAngle());
        }
    }

    @Nested
    @DisplayName("When no input is given")
    class WhenNoInputIsGiven {

        @Test
        @DisplayName("isValid should be false")
        void isValid_shouldBeFalse() {
            boolean isValid = factory.isValid();
            assertFalse(isValid);
        }

    }

    @Nested
    @DisplayName("When wrong input is given")
    class WhenWrongInputIsGiven {

        @Test
        @DisplayName("carportValidation should be thrown on roof angle")
        void carportValidation_shouldBeThrownOnRoofAngle() {
           assertThrows(CarportValidations.class, () -> {
               factory.setRoofAngle("not_an_integer");
           });
        }

        @Test
        @DisplayName("carportValidation should be thrown on length")
        void carportValidation_shouldBeThrownOnLength() {
            assertThrows(CarportValidations.class, () -> {
                factory.setLength("not_an_integer");
            });
        }

        @Test
        @DisplayName("carportValidation should be thrown on width")
        void carportValidation_shouldBeThrownOnWidth() {
            assertThrows(CarportValidations.class, () -> {
                factory.setWidth("not_an_integer");
            });
        }
    }


}