/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
import java.util.LinkedList;


public class ItemController {
    
    // Data storage (Model layer)
    private final LinkedList<Item> itemList;
    
    // Constructor
    public ItemController() {
        this.itemList = new LinkedList<>();
    }
    
    
    public boolean addItem(int id, String name, String type, String location, double price) {
        // Validate inputs
        if (name.isEmpty() || location.isEmpty()) {
            return false;
        }
        
        // Check for duplicate ID
        if (isDuplicateId(id)) {
            return false;
        }
        
        // Create and add item
        Item item = new Item(id, name, type, location, price);
        itemList.add(item);
        return true;
    }
    
   
    public LinkedList<Item> getAllItems() {
        return itemList;
    }
    
   
    public Item getItemById(int id) {
        for (Item item : itemList) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    
   
    public int getTotalItems() {
        return itemList.size();
    }
    
    
    public boolean updateItem(int id, String name, String type, String location, double price) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getId() == id) {
                Item updatedItem = new Item(id, name, type, location, price);
                itemList.set(i, updatedItem);
                return true;
            }
        }
        return false;
    }
    
    
    public boolean deleteItem(int id) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getId() == id) {
                itemList.remove(i);
                return true;
            }
        }
        return false;
    }
    
   
    public LinkedList<Item> searchItems(String keyword) {
        LinkedList<Item> results = new LinkedList<>();
        String searchKey = keyword.toLowerCase();
        
        for (Item item : itemList) {
            if (item.getName().toLowerCase().contains(searchKey) || 
                String.valueOf(item.getId()).contains(searchKey)) {
                results.add(item);
            }
        }
        return results;
    }
    
    
    public void sortById() {
        itemList.sort((a, b) -> a.getId() - b.getId());
    }
    
   
    public void sortByValue() {
        itemList.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
    }
    
    
     
     
    public int getLostItemsCount() {
        int count = 0;
        for (Item item : itemList) {
            if (item.getType().equalsIgnoreCase("Lost")) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Gets count of found items
     * @return 
     */
    public int getFoundItemsCount() {
        int count = 0;
        for (Item item : itemList) {
            if (item.getType().equalsIgnoreCase("Found")) {
                count++;
            }
        }
        return count;
    }
    
   
    public double getAverageValue() {
        if (itemList.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0;
        for (Item item : itemList) {
            sum += item.getPrice();
        }
        return sum / itemList.size();
    }
    
    private boolean isDuplicateId(int id) {
        for (Item item : itemList) {
            if (item.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isValidInteger(String str) {
        try {
            Integer.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    
    public boolean isValidDouble(String str) {
        try {
            Double.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}