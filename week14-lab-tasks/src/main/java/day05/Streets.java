package day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Streets {

    private final Map<String, List<Integer>> streetsWithNumber = new LinkedHashMap<>();


    public void readStreets(Path path) {
        String line;

        try {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                while ((line = reader.readLine()) != null) {
                    String[] temp = line.split(" ");
                    if (!setUpStreet(temp[0], Integer.parseInt(temp[1]))) {
                        addNewNumber(temp[0], Integer.parseInt(temp[1]));
                    }
                }
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
    }

    private void addNewNumber(String street, int number) {
        int maxNumber = streetsWithNumber.get(street).stream().mapToInt(i -> i).filter(i -> i % 2 == number).max().orElse(-number);
        streetsWithNumber.get(street).add(maxNumber + 2);
    }

    private boolean setUpStreet(String street, int number) {
        if (!streetsWithNumber.containsKey(street)) {
            streetsWithNumber.put(street, new ArrayList<>());
            if (number == 0) {
                streetsWithNumber.get(street).add(2);
            } else {
                streetsWithNumber.get(street).add(1);
            }
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Streets streets = new Streets();
        streets.readStreets(Paths.get("src/main/resources/streets.txt"));

        for (Map.Entry<String, List<Integer>> e : streets.streetsWithNumber.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }
}