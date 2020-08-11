# new feature
# Tags: optional

Feature: A description

  @smoke
  Scenario: Verify GET operation with bearer authentication token
    Given I perform authentication operation for "/auth/login" with body
      | email           | password |
      | bruno@email.com | bruno    |
    Given I perform GET operation for "http://localhost:3000/posts/1"
    Then I should see the author name as "Alex"


  @smoke
  Scenario: Verify GET operation with bearer authentication token for json schema validation
    Given I perform authentication operation for "/auth/login" with body
      | email           | password |
      | bruno@email.com | bruno    |
    Given I perform GET operation for "/posts/1"
    Then I should see the author name as "Alex" with json validation