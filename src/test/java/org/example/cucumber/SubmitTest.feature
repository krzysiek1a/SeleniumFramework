Feature: Submit test

  Background:
    Given I landed on index page

  Scenario: Submit data page positive test
    And Go to form page
    When Fill the form with "John" and "superSecret!"
    Then Assert correct message on submit page "ABC"