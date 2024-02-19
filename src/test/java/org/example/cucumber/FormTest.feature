Feature: Form test

  Background:
    Given I landed on index page

  Scenario Outline: Form data page positive test
    And Go to form page
    When Fill the form with <name> and <password>
    Then Assert correct message on submit page "Received!"

    Examples:
      | name    | password           |
      | Robo    | Secret pass shhhhh |
      | Max     | Best Password      |
      | Jasmine | SuperP             |