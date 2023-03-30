package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Categories {
    public Map<String, String> getCategories() {

        return categories;
    }

    Map<String, String> categories;

    {
        try {
            categories = Categories();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Map<String, String> Categories() throws IOException {
        Map<String, String> forCategories = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("categories.tsv"))) {

            while (true) {
                String str = br.readLine();
                if (str == null) {
                    break;
                }
                String[] data = str.split("\t");
                forCategories.put(data[0], data[1]);
            }
        }
        return forCategories;

    }
}

