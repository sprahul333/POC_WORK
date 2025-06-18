Feature: Submit Product Application (Non-Pawn)

  Background:
    Given Account Officer is logged in

  @RegressionSuite @CreateLoanApplication @English
  Scenario: Successful submission of PAWN Application
    Given the Cashier successfully logs into the Passion application using credentials
#    When the user accesses the menu with code "41010"
#    And the user fills in the pawn application data for product "GADAI KCA" with a term of "120 Days", special rate set to "Yes", and collateral rubric "Electronic Items"
#    And the user enters the collateral detail as "WATCH"
#    And the user successfully submits the pawn application and retrieves the Reference Number
    And User Fills the Pawn Application
    When Login to the Appraiser Application
    Then Approve the Pawn Application

#    And the user performs approval or confirmation of the pawn application
#    And approval is given by the Pawn Manager (P84038B / 123)
#    And approval is also given by the Branch Manager (Pinca)
#    Then the "Cashier" successfully processes the pawn application and disbursement with "Non-Cash" payment


