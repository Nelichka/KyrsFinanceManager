package org.example;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountingMaxCategories {

    public String getMaxCategory(List<Purchase> basket) {


        Map<String, String> categories = Categories.getCategories();
        Map<String, Integer> sum = new HashMap<>();

        for (Purchase purchase : basket) {
            String category;
            String title = purchase.getTitle();

            if (categories.containsKey(title)) {
                category = categories.get(title);
            } else {
                category = "Другое";
            }

            if (sum.containsKey(category)) {
                int amount = sum.get(category);
                sum.replace(category, amount + purchase.getSum());
            } else {
                sum.put(category, purchase.getSum());
            }
        }

        int maxSum = 0;
        String key = null;
        for (Map.Entry<String, Integer> entryMap : sum.entrySet()) {
            if (entryMap.getValue() > maxSum) {
                key = entryMap.getKey();
                maxSum = entryMap.getValue();
            }
        }

        JSONObject keyMaxSum = new JSONObject();
        keyMaxSum.put(key, maxSum);
        String MaxCategory = keyMaxSum.toJSONString();

        return MaxCategory;
    }
}