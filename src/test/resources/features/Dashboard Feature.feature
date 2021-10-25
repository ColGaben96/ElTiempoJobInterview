Feature: Dashboard Feature
  Scenario: User will add books to cart according to stock quantities
    Given User is on Dashboard
    Then Buy all the stock
    But When clicking the Add button, the grand total must coincide with the books value

  Scenario: User will add books to cart without caring of stock quantities
    Given User is on Dashboard
    Then Buy one more unit than the Stock
    But When clicking the Add button, an error should display due to there is no more units available

  Scenario: User will just buy Core Java Books
    Given User is on Dashboard
    Then Buy all the stock of Java Books
    But When clicking the Add button, the grand total must coincide with the Java books value

  Scenario: User will just buy Ruby for Rails Books
    Given User is on Dashboard
    Then Buy all the stock of Ruby for Rails Book
    But When clicking the Add button, the grand total must coincide with the Ruby for Rails books value

  Scenario: User will just buy Python Cookbook
    Given User is on Dashboard
    Then Buy all the stock of Python Cookbook
    But When clicking the Add button, the grand total must coincide with Python the books value

  Scenario: User will just buy 1 Core Java Book
    Given User is on Dashboard
    Then Buy one of stock of Java Books
    But When clicking the Add button, the grand total must coincide with the Java Book value

  Scenario: User will just buy 1 Ruby for Rails Book
    Given User is on Dashboard
    Then Buy one of stock of Ruby for Rails Book
    But When clicking the Add button, the grand total must coincide with the Roby for Rails book value

  Scenario: User will just buy 1 Python Cookbook
    Given User is on Dashboard
    Then Buy one of stock of Python Cookbook
    But When clicking the Add button, the grand total must coincide with the Python book value