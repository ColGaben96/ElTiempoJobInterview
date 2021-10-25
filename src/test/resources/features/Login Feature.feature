Feature: Login
  Scenario: User login with bad credentials
    Given I am on login
    Then User inputs bad user and password credentials and Error Message should display
    And Session should be done

  Scenario: User login with correct credentials
    Given I am on login
    Then User inputs correct user and passwords credentials and Dashboard should load
    And Session should be done