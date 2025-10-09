Feature: Banking Application

  Background:
    Given Launch the Application

  @RegressionSuite @Banking @CreateCustomer @JIRAID_123
  Scenario Outline: Create a New Customer
    Given Navigate to Bank Manager Screen
    When Click On Add Customer
    And Create a new Customer
      | first_name   | last_name   | postal_code   |
      | <first_name> | <last_name> | <postal_code> |
    Then Verify Customer is created successfully

    Examples:
      | first_name | last_name | postal_code |
      | Hameed     | Joseph    | 89210215    |
      | Jaren      | Duren     | 89210219    |

  @RegressionSuite @Banking @CreateAccount @JIRAID_456
  Scenario Outline: Create a New Account
    Given Navigate to Bank Manager Screen
    When Click On Add Customer
    And Create a new Customer
      | first_name   | last_name   | postal_code   |
      | <first_name> | <last_name> | <postal_code> |
    Then Verify Customer is created successfully
    And Navigate to Bank Manager Screen
    When Click On Open Account
    And Create an Account
      | currency_data |
      | <currency>    |
    Then Verify Account is created successfully

    Examples:
      | first_name | last_name | postal_code | currency |
      | Hameed     | Joseph    | 89210215    | Dollar   |
