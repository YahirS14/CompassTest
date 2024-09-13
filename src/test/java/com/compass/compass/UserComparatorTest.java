package com.compass.compass;

import com.compass.compass.data.model.User;
import com.compass.compass.data.services.UserComparator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserComparatorTest {

    private final UserComparator userComparator = new UserComparator();

    @Test
    public void testCompareUsers_ExactMatch() {
        User user1 = new User("1", "John", "Doe", "john.doe@example.com", "123 Main St", "12345");
        User user2 = new User("2", "John", "Doe", "john.doe@example.com", "123 Main St", "12345");

        int matchCount = userComparator.compareUsers(user1, user2);

        assertEquals(5, matchCount, "Match count should be 5 for exact matches.");
    }

    @Test
    public void testCompareUsers_PartialMatch() {
        User user1 = new User("1", "John", "Doe", "john.doe@example.com", "123 Main St", "12345");
        User user2 = new User("2", "John", "Doe", "john.doe@example.com", "456 Elm St", "12345");

        int matchCount = userComparator.compareUsers(user1, user2);

        assertEquals(4, matchCount, "Match count should be 4 for partial matches.");
    }

    @Test
    public void testCompareUsers_NoMatch() {
        User user1 = new User("1", "John", "Doe", "john.doe@example.com", "123 Main St", "12345");
        User user2 = new User("2", "Jane", "Smith", "jane.smith@example.com", "456 Elm St", "67890");

        int matchCount = userComparator.compareUsers(user1, user2);

        assertEquals(0, matchCount, "Match count should be 0 for no matches.");
    }
}
