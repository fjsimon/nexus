Feature: Initial feature

  Scenario: Check greeting endpoint

    Given user wants to call greeting endpoint "fran"
    When get greeting request
    Then call 'FAILS'