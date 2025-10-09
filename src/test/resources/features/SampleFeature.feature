Feature: Banking Application

  Background:
    Given Launch the Application

  @RegressionSuite @Banking @CreateCustomer
  Scenario Outline: Create a New Customer
    And Create a new Customer having First Name "<first_name>" Last Name "<last_name>" Post Code "<postal_code>"

    Examples:
      | first_name | last_name | postal_code |
      | Hameed     | Joseph    | 89210215    |

  @RegressionSuite @Banking @CreateNewAccount
  Scenario Outline: Create a New Account
    And Create a new Customer having First Name "<first_name>" Last Name "<last_name>" Post Code "<postal_code>"
    And Create a New Account having Currency "<Currency>"

    Examples:
      | first_name | last_name | postal_code | Currency |
      | Hameed     | Joseph    | 89210215    | Dollar   |

  @RegressionSuite @Banking @SearchCustomers
  Scenario Outline: Search for the Customers
    And Create a new Customer having First Name "<first_name>" Last Name "<last_name>" Post Code "<postal_code>"
    And Search for the Customer

    Examples:
      | first_name | last_name | postal_code |
      |            |           |             |


  @RegressionSuite @Customer @DepositAmount
  Scenario Outline: Deposit the Amount
    And Create a new Customer having First Name "<first_name>" Last Name "<last_name>" Post Code "<postal_code>"
    And Create a New Account having Currency "<Currency>"
    And Deposit the Amount of "<Amount>"

    Examples:
      | first_name | last_name | postal_code | Currency | Amount |
      |            |           |             |          |        |

  @RegressionSuite @Customer @WithdrawAmount
  Scenario Outline: Withdraw the Amount
    And Create a new Customer having First Name "<first_name>" Last Name "<last_name>" Post Code "<postal_code>"
    And Create a New Account having Currency "<Currency>"
    And Withdraw the Amount of "<Amount>"

    Examples:
      | first_name | last_name | postal_code | Currency | Amount |
      |            |           |             |          |        |
