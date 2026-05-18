package co.edu.udea.calidad.opencart.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Categories {

    private static final String BASE = "https://tutorialsninja.com/demo/index.php?route=product/category&path=";
    private static final Map<String, String> PATH;

    static {
        Map<String, String> m = new HashMap<>();
        m.put("Desktops", "20");
        m.put("Laptops & Notebooks", "18");
        m.put("Components", "25");
        m.put("Tablets", "57");
        m.put("Software", "17");
        m.put("Phones & PDAs", "24");
        m.put("Cameras", "33");
        m.put("MP3 Players", "34");
        PATH = Collections.unmodifiableMap(m);
    }

    private Categories() {}

    public static String urlOf(String categoryName) {
        String id = PATH.get(categoryName);
        if (id == null) {
            throw new IllegalArgumentException("Categoría no registrada: " + categoryName
                    + ". Categorías válidas: " + PATH.keySet());
        }
        return BASE + id;
    }
}
