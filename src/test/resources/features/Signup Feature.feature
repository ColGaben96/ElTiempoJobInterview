Feature: Signup Feature
  Scenario: User will signup without checking the tos agreement.
    Given I am on signup
    Then User will fill the form but won't check the tos agreement.
    And Should display an alert where the user hasn't accepted the tos before.

  Scenario: User will signup after checking the tos agreement.
    Given I am on signup
    Then User will fill the form but will check the tos agreement.
    And Should pass to the next step or show an alert that the process was successful.