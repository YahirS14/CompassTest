package com.compass.compass.data.services;

import com.compass.compass.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class DuplicateFinder {
    private final UserComparator userComparator;

    public DuplicateFinder(UserComparator userComparator) {
        this.userComparator = userComparator;
    }

    public List<String> findDuplicates(List<User> users) {
        List<String> duplicates = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            for (int j = i + 1; j < users.size(); j++) {
                User user1 = users.get(i);
                User user2 = users.get(j);

                int matchCount = userComparator.compareUsers(user1, user2);

                String accuracy = "LOW";
                if (matchCount >= 3) {
                    accuracy = "HIGH";
                } else if (matchCount == 2) {
                    accuracy = "MEDIUM";
                }

                if (matchCount > 0 && user1.getId() != null && !user1.getId().isEmpty() && user2.getId() != null && !user2.getId().isEmpty()) {
                    duplicates.add(String.format("ContactID %s matches with ContactID %s: %s (Match Count: %d)",
                            user1.getId(), user2.getId(), accuracy, matchCount));
                }
            }
        }

        return duplicates;
    }
}