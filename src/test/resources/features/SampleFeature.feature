Feature: Submit Product Application (Non-Pawn)

  Background:
    Given the Account Officer is logged in

  Scenario: Successful submission of product AMANAH
    Given the Account Officer is on the Non-Pawn Application page
    And the Account Officer fills in the application data with loan purpose "PRODUCTIVE", rubric "VEHICLE", loan amount "32000000", and product "AMANAH"
    And the Account Officer fills in the customer data with "nikSesuaiTreshold" from Dukcapil
    And the Account Officer adds 1 new vehicle collateral "kendaraanMobil"
    When the Account Officer clicks submit for product "AMANAH"
    Then the application is successful
