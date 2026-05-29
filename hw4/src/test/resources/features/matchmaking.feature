# As a developer, I want to create a new container, so that I can store user id references.
# As a developer, I want the first and last user id references from a single container, so I can randomly match two users that have been added to the queue.
# As a developer, I want to check if a container is empty, so that I can remove it and its associations.
# As a developer, I want to check the size of a container, to calculate an estimated wait time.

Feature: Matchmaking queue container management
  Matchmaking services use a deque-style container to manage waiting user ids.

  Scenario: Create a new container for queued user ids
    Given no matchmaking container exists
    When I create a matchmaking container
    Then a matchmaking container exists

  Scenario: Read first and last user ids for random pairing
    Given a matchmaking container exists
    And the container has user ids 100, 200, and 300
    When I read the first and last ids for pairing
    Then I have the first user id 100
    And I have the last user id 300

  Scenario: Check whether container is empty before cleanup
    Given a queue container exists with no waiting user ids
    When I evaluate whether the queue container is empty
    Then the queue container reports empty status
    And I can remove the container associations

  Scenario: I can check the size of a container to estimate wait time
    Given I have a container with 4 user id references waiting
    When I check the size of the container
    Then I have the container size of 4
    And I can calculate an estimated wait time from the size

# As a developer, I can merge two containers of user id references, so that I can combine queues when necessary.

#  Scenario: Merge two containers of user id references
#    Given I have a container with 4 user id references waiting
#    And I have another container with 2 user id references waiting
#    When I merge the two containers
#    Then the merged container has a size of 5

#  Scenario: Fail to merge when a second container is unavailable
#    Given I have a container with 4 user id references waiting
#    And no second container is available to merge
#    When I merge the two containers
#    Then the merge is rejected because a second container is required
#    And the original container still has a size of 4



