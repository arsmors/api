# new feature
# Tags: optional

Feature: A description

  @smoke
  Scenario: Verify DELETE operation after POST
    Given I ensure to Perform POST operation for "http://localhost:3000/posts" with body as
      | id | title              | author            |
      | 8  | API Testing course | ExecuteAutomation |
    And I Perform DELETE operation for "http://localhost:3000/posts/{postid}/"
      | postid |
      | 8      |
    And I perform GET operation with path parameter for "http://localhost:3000/posts/{postid}"
      | postid |
      | 8      |
    Then I "should not" see the body with title as "API Testing course"