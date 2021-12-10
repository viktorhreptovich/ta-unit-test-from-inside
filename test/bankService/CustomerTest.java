package bankService;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CustomerTest {

    /**
     * The system should be able to store information on a new customer using valid data.
     * 1. Given I create a new customer named Mickey Mouse.
     * 2. When I initialize the customer object with valid details.
     * 3. Then Mickey's information should be stored in the system.
     */
    @Test
    public void creatingCustomerWithValidData_StoresSpecifiedData() {
        // Given and When
        Customer customer = new Customer("Mickey Mouse", "Disneyland", "mickey@disneyland.com");

        //Then
        assertNotNull(customer);
        assertEquals(customer.getName(), "Mickey Mouse");
        assertEquals(customer.getAddress(), "Disneyland");
        assertEquals(customer.getEmail(), "mickey@disneyland.com");
    }

    /**
     * The system should be able to update customer information using valid data.
     * Scenario:
     * 1. Given I create a new customer named Mickey Mouse.
     * 2. When I update Mickey's customer information with Minnie's information.
     * 3. Then Minnie's information should be stored in the system.
     */
    @Test
    public void updatingCustomerDataWithDifferentValidValues_StoresNewValues() {
        //Given
        Customer customer = new Customer("Mickey Mouse", "Disneyland", "micky@disneyland.com");

        //When
        customer.updateName("Minnie Mouse");
        customer.updateAddress("Disney World");
        customer.updateEmail("minnie@disneyworld.com");

        //Then
        assertEquals(customer.getName(), "Minnie Mouse");
        assertEquals(customer.getAddress(), "Disney World");
        assertEquals(customer.getEmail(), "minnie@disneyworld.com");
    }

}
