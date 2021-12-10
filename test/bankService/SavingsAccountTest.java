package bankService;

import bankService.exceptions.InsufficientFundsException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class SavingsAccountTest {
    Customer customer;
    SavingsAccount savings;

    @BeforeClass
    public void oneTimeSetup() {
        customer = new Customer("Mickey Mouse", "Disneyland", "mickey@disneyland.com");
    }

    @BeforeMethod
    public void eachTimeSetup() {
        savings = new SavingsAccount(customer, 100.00, 123456789);
    }

    /**
     * Customers should be able to withdraw from their savings account.
     * Scenario:
     * 1. Given a customer's savings account with an initial balance of $100.00.
     * 2. When I withdraw $60.00 from the account.
     * 3. Then the new account balance is $40.00.
     */
    @Test(dataProvider = "ValidWithdrawDataProvider")
    public void withdrawingValidAmountFromSavingsAccount_DecreasesBalanceByAmount(double amount, double expectedBalance) throws InsufficientFundsException {
        //When
        savings.withdraw(amount);

        //Then
        assertEquals(savings.getBalance(), expectedBalance);
    }

    @DataProvider(name = "ValidWithdrawDataProvider")
    private Object[][] createValidWithdrawDataProvider() {
        return new Object[][]{
                {60.00, 40.00},
                {100.00, 0.00}
        };
    }

    /**
     * Customers should not be able to withdraw more than their available savings account balance.
     * Scenario:
     * 1. Given a customer's savings account with an initial balance of $100.00.
     * 2. When I attempt to withdraw $200.00.
     * 3. Then an exception should occur indicating that there are insufficient funds in the account.
     * 4. And the account balance should remain unchanged.
     */
    @Test
    public void withdrawingAmountGreaterThanBalance_Throws_InsufficentFundsException() {
        //When
        try {
            savings.withdraw(200.00);
            fail("Expected InsufficientFundsException but none was thrown");
        } catch (InsufficientFundsException e) {
            //Then
            assertEquals(savings.getBalance(), 100.00);
        }
    }
}
