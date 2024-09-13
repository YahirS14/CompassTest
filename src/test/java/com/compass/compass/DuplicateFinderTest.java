package com.compass.compass;

import com.compass.compass.data.model.User;
import com.compass.compass.data.services.DuplicateFinder;
import com.compass.compass.data.services.UserComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DuplicateFinderTest {

    @Mock
    private UserComparator userComparator;

    @InjectMocks
    private DuplicateFinder duplicateFinder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindDuplicates_WithDuplicates() {
        User user1 = new User("1", "John", "Doe", "john.doe@example.com", "123 Main St", "12345");
        User user2 = new User("2", "John", "Doe", "john.doe@example.com", "123 Main St", "12345");
        User user3 = new User("3", "Jane", "Smith", "jane.smith@example.com", "456 Elm St", "67890");

        when(userComparator.compareUsers(user1, user2)).thenReturn(5);
        when(userComparator.compareUsers(user1, user3)).thenReturn(0);
        when(userComparator.compareUsers(user2, user3)).thenReturn(0);

        List<User> users = Arrays.asList(user1, user2, user3);
        List<String> duplicates = duplicateFinder.findDuplicates(users);

        assertEquals(1, duplicates.size(), "There should be one duplicate entry.");
        assertEquals("ContactID 1 matches with ContactID 2: HIGH (Match Count: 5)",
                duplicates.get(0), "Duplicate entry does not match the expected format.");
    }

    @Test
    public void testFindDuplicates_NoDuplicates() {
        User user1 = new User("1", "John", "Doe", "john.doe@example.com", "123 Main St", "12345");
        User user2 = new User("2", "Jane", "Smith", "jane.smith@example.com", "456 Elm St", "67890");

        when(userComparator.compareUsers(user1, user2)).thenReturn(0);

        List<User> users = Arrays.asList(user1, user2);
        List<String> duplicates = duplicateFinder.findDuplicates(users);

        assertEquals(0, duplicates.size(), "There should be no duplicate entries.");
    }
}
