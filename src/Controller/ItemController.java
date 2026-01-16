package Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
import Model.Item;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class ItemController {
    
    // Data storage (Model layer)
    private final LinkedList<Item> itemList;
    private final java.util.Queue<Item> claimQueue;
    private final java.util.Queue<Item> recentQueue = new java.util.LinkedList<>();


    Stack<Item> undoStack = new Stack<>();

    
    // Constructor
    public ItemController() {
        this.itemList = new LinkedList<>();
        this.claimQueue = new java.util.LinkedList<>();
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
    undoStack.push(item);          
    recentQueue.add(item);
if (recentQueue.size() > 5) recentQueue.poll();

        return true;
    }
    public LinkedList<Item> searchItems(String keyword) {

    LinkedList<Item> results = new LinkedList<>();

    if (keyword == null || keyword.isEmpty()) {
        return results;
    }

    for (Item item : itemList) {

        
        if (item.getName().toLowerCase().contains(keyword.toLowerCase())
                || String.valueOf(item.getId()).equals(keyword)) {

            results.add(item);
        }
    }
    return results;
}

    public Queue<Item> getRecentItems(){
    return recentQueue;
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
            undoStack.push(itemList.get(i));   // save for undo
            itemList.remove(i);
            return true;
            }
        }
        return false;
    }
    
   
  
    
    
    public void sortById() {
        int n = itemList.size();

for(int i = 0; i < n - 1; i++){
    for(int j = 0; j < n - i - 1; j++){
        if(itemList.get(j).getId() > itemList.get(j + 1).getId()){
            Item temp = itemList.get(j);
            itemList.set(j, itemList.get(j + 1));
            itemList.set(j + 1, temp);
        }
    }
}
    }
    
   
    public void sortByValue() {
        for(int i = 1; i < itemList.size(); i++){
    Item key = itemList.get(i);
    int j = i - 1;

    while(j >= 0 && itemList.get(j).getPrice() > key.getPrice()){
        itemList.set(j + 1, itemList.get(j));
        j--;
    }
    itemList.set(j + 1, key);
}
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
    public Item searchById(int id){
    for(Item i : itemList){
        if(i.getId()==id) return i;
    }
    return null;
}

    public void pushUndo(Item i){
    undoStack.push(i);
}

public Item undoLast(){
    if(undoStack.isEmpty()) return null;
    return undoStack.pop();
}
public boolean addToClaimQueue(int id) {
    Item i = getItemById(id);
    if(i == null) return false;

    for(Item q : claimQueue){
        if(q.getId() == id) return false;   // already in queue
    }

    claimQueue.add(i);
    return true;
}

public Item processNextClaim() {
    return claimQueue.poll();
}

public java.util.Queue<Item> getClaimQueue() {
    return claimQueue;
}
public void saveUndo(Item i) {
    undoStack.push(i);
}

public Item undoLastAction() {
    if (undoStack.isEmpty()) return null;
    return undoStack.pop();
}
public Item binarySearchById(int searchId) {

    bubbleSortById();   

    int low = 0;
    int high = itemList.size() - 1;

    while (low <= high) {
        int mid = (low + high) / 2;
        int midId = itemList.get(mid).getId();

        if (midId == searchId) {
            return itemList.get(mid);
        } else if (midId < searchId) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return null;
}

public java.util.Queue<Item> getRecentQueue(){
    return recentQueue;
}
private void bubbleSortById() {
    int n = itemList.size();

    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {

            if (itemList.get(j).getId() > itemList.get(j + 1).getId()) {
                // swap
                Item temp = itemList.get(j);
                itemList.set(j, itemList.get(j + 1));
                itemList.set(j + 1, temp);
            }
        }
    }
}




   


    

    
}