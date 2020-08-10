# new feature
# Tags: optional

Feature: A description

  @smoke
  Scenario: Verify GET operation with bearer authentication token
    Given I perform authentication operation for "http://localhost:3000/auth/login" with body
      | email           | password |
      | bruno@email.com | bruno    |
    Given I perform GET operation for "http://localhost:3000/posts/1"
    Then I should see the author name as "Alex"