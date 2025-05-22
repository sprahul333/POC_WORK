Feature: Submit Product Application (Non-Pawn)

  Background:
    Given Account Officer is logged in

  @RegressionSuite @CreateLoanApplication @English
  Scenario Outline: Successful submission of product AMANAH
    Given Account Officer is on the Non-Pawn Application page
    And Account Officer fills in the application data with disbursement outlet "<Disbursement Outlet>", application purpose "<Application Purpose>",  loan purpose "<Loan Purpose>", rubric "<Rubric>", loan amount "<Loan Amount>", and product "<Product>"
    And Account Officer fills in the customer data with customer id "<Customer_ID>", customer name "<Customer_Name>", gender "<Gender>", PlaceOfBirth "<PlaceOfBirth>", PinCode "<PinCode>", SubDistrict "<SubDistrict>" and verify from Dukcapil
    And Account Officer adds One Collateral with Collateral Category "<CollateralCategory>", Collateral Type "<CollateralType>", Collateral Condition "<CollateralCondition>"
    When Account Officer clicks submit for product "<Product>"
    Then Application is successful

    Examples:
      | Disbursement Outlet | Application Purpose | Loan Purpose | Rubric    | Loan Amount | Product | Customer_ID      | Customer_Name | Gender    | PlaceOfBirth | PinCode | SubDistrict | CollateralCategory | CollateralType | CollateralCondition |
      | CPS KRAMAT RAYA     | PRODUKTIF           | INVESTASI    | KENDARAAN | 32000000    | AMANAH  | 3577011610960002 | Automation    | Perempuan | BANDUNG      | 63117   | KLEGEN      | KENDARAAN          | SEPEDA MOTOR   | BEKAS               |
