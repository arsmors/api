# new feature
# Tags: optional

Feature: A description

  Scenario: A scenario
    Given I ensure to Perform POST operation for "http://localhost:3000/posts" with body as
      | id | title              | author            |
      | 8  | API Testing course | ExecuteAutomation |
    And I Perform PUT operation for "http://localhost:3000/posts/{postid}/"
      | id | title              | author            |
      | 8  | API Testing course2 | ExecuteAutomation |
    And I perform GET operation with path parameter for "http://localhost:3000/posts/{postid}"
      | postid |
      | 8      |
    Then I "should" see the body with title as "API Testing course2"