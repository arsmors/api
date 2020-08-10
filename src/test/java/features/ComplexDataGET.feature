# new feature
# Tags: optional

Feature: A description

  @smoke
  Scenario: Verify Get operation for complex data
    Given I perform authentication operation for "http://localhost:3000/auth/login" with body
      | email           | password |
      | bruno@email.com | bruno    |
    And I perform GET operation with path parameter for address "http://localhost:3000/location"
      | id |
      | 1  |
    Then I should see the street name as "1st street" for the "primary" address