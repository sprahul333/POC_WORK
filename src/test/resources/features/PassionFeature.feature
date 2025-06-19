Feature: Submit Product Application (Non-Pawn)

  @RegressionSuite @CreateAndApprovePawnApplication @English
  Scenario Outline: Successful submission of PAWN Application
    Given the Cashier successfully logs into the Passion application using credentials
#    When the user accesses the menu with code "41010"
#    And the user fills in the pawn application data for product "GADAI KCA" with a term of "120 Days", special rate set to "Yes", and collateral rubric "Electronic Items"
#    And the user enters the collateral detail as "WATCH"
#    And the user successfully submits the pawn application and retrieves the Reference Number
    And User Fills the Pawn Application for Customer ID "<Customer ID>" and Amount "<Amount>"
    When Login to the Appraiser Application
    Then Approve the Pawn Application
    When Login to the Branch Manager Application
    Then Approve the Pawn Application using branch manager credentials
    When Login to the Appraiser Application
    Then Approve the Pawn Application at the third level
    When Login to the Cashier Application
    Then Approve the Pawn Application for disbursement using SBG Number "<SBG_Number>"

    Examples:
      | Customer ID | Amount   | SBG_Number   |
      | 10000021218 | 25000000 | 230142908328 |

#    And the user performs approval or confirmation of the pawn application
#    And approval is given by the Pawn Manager (P84038B / 123)
#    And approval is also given by the Branch Manager (Pinca)
#    Then the "Cashier" successfully processes the pawn application and disbursement with "Non-Cash" payment


