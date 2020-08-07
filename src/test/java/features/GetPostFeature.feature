Feature:
  GET operations

  Scenario: Verify one author of the post
    Given I perform GET operation for "http://localhost:3000/posts"
    Then I should see the author name as "Alex"

#
#  Scenario: Verify collection of authors in the post
#    Given I perform GET operation for "/post"
#    Then I should see the author names
##
#  Scenario: Verify Parameter of Get
#    Given I perform GET operation for "/posts"
#    Then I should see verify GET Parameter

#    Scenario: Verify Post operation
#    Given I perform Post operation for "http://localhost:3000/posts"




#  @smoke
#  Scenario: Verify GET operation with bearer authentication token
#    Given I perform authentication operation for "/auth/loging" with body
#      | email           | password |
#      | bruno@email.com | bruno    |
#    Given I perform GET operation for "/post"
#    Then I should see the author name as "Alex"