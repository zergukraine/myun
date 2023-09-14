@tag

Feature: verification


  Scenario Outline: Verify sign in
    Given Launch the app
    And Navigate to the SignInPage
    When Enter username <username> and password <pass> on SignInPage
    And Set checkboxes on SignInPage
    And Click submit button on SignInPage
    Then The user avatar is displayed on the home page
    And The week dashboard is displayed on the home page
    And The left menu items are displayed on the home page
    And Store the access token
    And Close browser

    Examples:
      | username                                          | pass      |
      | user.test.cabinet+myunisoft_candidat@myunisoft.fr | G00D_LuCk |


  Scenario Outline: Changing the User's Password
    Given The user has an access token
    When The user sends a request to change the password from old_password <old_password> to new_password <new_password>
    Then The request should be successful with a status code <code>

    Examples:
      | old_password | new_password  | code |
      | G00D_LuCk    | G00D_LuCk1    | 204  |


  Scenario Outline: Changing the User's Password back
    Given The user has an access token
    When The user sends a request to change the password from old_password <old_password> to new_password <new_password>
    Then The request should be successful with a status code <code>

    Examples:
      | old_password | new_password  | code |
      | G00D_LuCk1    | G00D_LuCk    | 204  |
