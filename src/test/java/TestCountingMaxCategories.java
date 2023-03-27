import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.CountingMaxCategories;
import org.example.Purchase;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestCountingMaxCategories {

    Purchase purchase1 = new Purchase("булка", "2022.08.12", 100);
    Purchase purchase2 = new Purchase("колбаса", "2023.02.05", 500);
    Purchase purchase3 = new Purchase("сухарики", "2018.10.03", 70);
    Purchase purchase4 = new Purchase("курица", "2023.01.17", 400);
    Purchase purchase5 = new Purchase("тапки", "2021.09.19", 800);
    Purchase purchase6 = new Purchase("шапка", "2022.11.28", 1500);
    Purchase purchase7 = new Purchase("мыло", "2021.07.16", 30);

    List<Purchase> basket = new ArrayList<>();

    {
        basket.add(purchase1);
        basket.add(purchase2);
        basket.add(purchase3);
        basket.add(purchase4);
        basket.add(purchase5);
        basket.add(purchase6);
        basket.add(purchase7);
    }

    @Test
    public void testCountingMaxCategories() throws ParseException, JsonProcessingException {
        String str = CountingMaxCategories.getMaxCategory(basket);

        JSONObject json = new JSONObject();
        json.put("одежда", 2300);

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(str), mapper.readTree(String.valueOf(json)));
    }

    @Test
    public void testOtherCategory() throws ParseException, JsonProcessingException {
        Purchase other = new Purchase("Коньки", "2023.03.26", 15_000);
        basket.add(other);
        String str = CountingMaxCategories.getMaxCategory(basket);
        System.out.println(str);

        JSONObject json = new JSONObject();
        json.put("Другое", 15_000);

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(str), mapper.readTree(String.valueOf(json)));

    }
}
