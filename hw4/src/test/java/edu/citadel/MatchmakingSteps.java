package edu.citadel;

import io.cucumber.java.en.*;

import static org.junit.Assert.*;

public class MatchmakingSteps {

    private Deque<Integer> container;
    private Deque<Integer> anotherContainer;
    private Integer firstId;
    private Integer lastId;
    private int containerSize;

    // Cucumber scenario 1
    // New Container

    @Given("no matchmaking container exists")
    public void noMatchmakingContainerExists() {
        container = null;
    }

    @When("I create a matchmaking container")
    public void iCreateAMatchmakingContainer() {
        container = new Deque<>();
    }

    // Cucumber scenario 2
    // Scenario 2 requires container with users

    @Given("a matchmaking container exists")
    public void aMatchmakingContainerExists() {
        if (container == null) {
            container = new Deque<>();
        }
        assertNotNull("Matchmaking container should not be null", container);
    }

    @Given("the container has user ids {int}, {int}, and {int}")
    public void theContainerHasUserIds(int id1, int id2, int id3) {
        container.addBack(id1);
        container.addBack(id2);
        container.addBack(id3);
    }

    // Getting users from container with users and verifying they exist
    @When("I read the first and last ids for pairing")
    public void iRequestTheFirstAndLastUserIds() {
        firstId = container.removeFront();
        lastId = container.removeBack();
    }

    @Then("I have the first user id {int}")
    public void theFirstUserIdIs(int id) {
        assertEquals(id, (int) firstId);
    }

    @Then("I have the last user id {int}")
    public void theLastUserIdIs(int id) {
        assertEquals(id, (int) lastId);
    }


    // Scenario 3
    // Require empty container
    @Given("a queue container exists with no waiting user ids")
    public void theContainerHasNoUserIds() {
        if (container == null) {
            container = new Deque<>();
        }
        assertTrue("Container should be empty", container.empty());
    }

    @When("I evaluate whether the queue container is empty")
    public void iCheckWhetherTheContainerIsEmpty() {
        // method exists to verify
    }

    @Then("the queue container reports empty status")
    public void theContainerIsEmpty() {
        assertTrue("Container should be empty", container.empty());
    }

    @When("I can remove the container associations")
    public void iRemoveTheEmptyContainer() {
        container = null;
    }

    @Then("the container associations are removed")
    public void theContainerAssociationsAreRemoved() {
        assertNull("Container reference should be null after removal", container);
    }

    // Scenario 4
    // Require container with users
    @Given("I have a container with {int} user id references waiting")
    public void iHaveAContainerWithUserIdReferencesWaiting(int count) {
        container = new Deque<>();
        for (int i = 1; i <= count; i++) {
            container.addBack(i * 100);
        }
    }

    @When("I check the size of the container")
    public void iCheckTheSizeOfTheContainer() {
        containerSize = container.size();
    }

    @Then("I have the container size of {int}")
    public void theContainerSizeIs(int expectedSize) {
        assertEquals(expectedSize, containerSize);
    }

    @Then("I can calculate an estimated wait time from the size")
    public void iCanCalculateAnEstimatedWaitTimeFromTheSize() {
        assertTrue("Container size must be positive to calculate wait time", containerSize > 0);
    }

    // Part 4 (scenario 5 and 6)
    // Expected to currently fail
    /*
    @Given("I have another container with {int} user id references waiting")
    public void iHaveAnotherContainerWithUserIdReferencesWaiting(int count) {
        anotherContainer = new Deque<>();
        for (int i = 1; i <= count; i++) {
            anotherContainer.addBack(i * 100);
        }
    }

    @When("I merge the two containers")
    public void iMergeTheTwoContainers() {
        // Does not exist 
        if (container != null &&anotherContainer != null) {
            container.merge(anotherContainer);
        }
    }

    @Then("the merged container has a size of {int}")
    public void theMergedContainerHasASizeOf(int expectedSize) {
        assertEquals(expectedSize, container.size());
    }

    @Given("no second container is available to merge")
    public void noSecondContainerExists() {
        assertNull("Second container should be null", anotherContainer);
    }

    @Then("the merge is rejected because a second container is required")
    public void theMergeIsRejectedBecauseASecondContainerIsRequired() {
        AssertException thrown = assertThrows(IllegalArgumentException.class, () -> {
            container.merge(anotherContainer);
        });
    }
    
    @Then("the original container still has a size of {int}")
    public void theOriginalContainerStillHasASizeOf(int expectedSize) {
        assertEquals(expectedSize, container.size());
    } */
}