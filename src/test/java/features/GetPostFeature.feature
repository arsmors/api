Feature:
  GET operations

#  Scenario: Verify one author of the post
#    Given I perform GET operation for "/post"
#    And I perform GET for the post number "1"
#    Then I should see the author name as "Ales"


  Scenario: Verify collection of authors in the post
    Given I perform GET operation for "/post"
    Then I should see the author names
#
#  Scenario: Verify Parameter of Get
#    Given I perform GET operation for "/posts"
#    Then I should see verify GET Parameter




#  @smoke
#  Scenario: Verify GET operation with bearer authentication token
#    Given I perform authentication operation for "/auth/loging" with body
#      | email           | password |
#      | bruno@email.com | bruno    |
#    Given I perform GET operation for "/post"
#    Then I should see the author name as "Alex"