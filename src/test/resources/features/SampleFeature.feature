Feature: Submit Product Application (Non-Pawn)

  Background:
    Given the Account Officer is logged in

    @RegressionSuite @CreateLoanApplication
  Scenario: Successful submission of product AMANAH
    Given the Account Officer is on the Non-Pawn Application page
    And the Account Officer fills in the application data with disbursement outlet "CPS KRAMAT RAYA", application purpose "PRODUKTIF",  loan purpose "INVESTASI", rubric "KENDARAAN", loan amount "32000000", and product "AMANAH"
    And the Account Officer fills in the customer data with customer id "3577011610960002", customer name "Automation", gender "Perempuan", PlaceOfBirth "BANDUNG", PinCode "63117", SubDistrict "KLEGEN" and verify from Dukcapil
    And the Account Officer adds One Collateral with Collateral Category "KENDARAAN", Collateral Type "SEPEDA MOTOR", Collateral Condition "BEKAS"
    When the Account Officer clicks submit for product "AMANAH"
    Then the application is successful
