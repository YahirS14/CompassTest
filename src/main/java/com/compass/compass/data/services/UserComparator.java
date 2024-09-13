package com.compass.compass.data.services;

import com.compass.compass.data.model.User;

public class UserComparator {

    public int compareUsers(User user1, User user2) {
        int matchCount = 0;

        if (user1.getName() != null && !user1.getName().isEmpty() && user2.getName() != null && !user2.getName().isEmpty()) {
            if (user1.getName().equals(user2.getName())) {
                matchCount++;
            }
        }

        if (user1.getLastName() != null && !user1.getLastName().isEmpty() && user2.getLastName() != null && !user2.getLastName().isEmpty()) {
            if (user1.getLastName().equals(user2.getLastName())) {
                matchCount++;
            }
        }

        if (user1.getEmail() != null && !user1.getEmail().isEmpty() && user2.getEmail() != null && !user2.getEmail().isEmpty()) {
            if (user1.getEmail().equals(user2.getEmail())) {
                matchCount++;
            }
        }

        if (user1.getAddress() != null && !user1.getAddress().isEmpty() && user2.getAddress() != null && !user2.getAddress().isEmpty()) {
            if (user1.getAddress().equalsIgnoreCase(user2.getAddress())) {
                matchCount++;
            }
        }

        if (user1.getZipCode() != null && !user1.getZipCode().isEmpty() && user2.getZipCode() != null && !user2.getZipCode().isEmpty()) {
            if (user1.getZipCode().equals(user2.getZipCode())) {
                matchCount++;
            }
        }

        return matchCount;
    }
}