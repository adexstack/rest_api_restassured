package org.datadriving;

import java.util.HashMap;

// RestAssured also accepts hashmap as body, just as serialized Java POJO,
// customize similar hashmap object and send as body
public class HashMapForRestBody {
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("Name", "Adex");
        map.put("Address", "Adex Street");
        map.put("Country", "UK");

        HashMap<String, Object> occupation = new HashMap<>();
        occupation.put("trade", "IT");
        occupation.put("salary", 2000);

        map.put("occupation", occupation);

        System.out.println(map);
    }
}
