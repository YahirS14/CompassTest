package com.compass.compass;

import com.compass.compass.application.services.FileManagement;
import com.compass.compass.data.model.User;
import com.compass.compass.data.services.DuplicateFinder;
import com.compass.compass.data.services.UserComparator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CompassApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompassApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please provide the file path (e.g., /path/to/file/users.xlsx):\n");
            String filePath = scanner.nextLine();

            try {
                // Verifica si el archivo existe
                if (!Files.exists(Paths.get(filePath))) {
                    System.out.println("The file does not exist\n: " + filePath);
                    return;
                }

                FileInputStream fileInputStream = new FileInputStream(filePath);

                List<User> users = FileManagement.readExcelFile(fileInputStream);

                UserComparator comparator = new UserComparator();
                DuplicateFinder df = new DuplicateFinder(comparator);

                List<String> duplicates = df.findDuplicates(users);
                duplicates.forEach(System.out::println);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                scanner.close();
            }
        };
    }
}
