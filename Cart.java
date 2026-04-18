import food.*;
import java.util.*;

public class Cart {
    Map<Integer, Integer> items = new HashMap<>();

    public void addItem(int id, int qty) {
        items.put(id, items.getOrDefault(id, 0) + qty);
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }
}
