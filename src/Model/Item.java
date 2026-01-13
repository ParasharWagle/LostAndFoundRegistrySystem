package Model;



public class Item {

    private int id;
    private String name;
    private String type;
    private String location;
    private double price;

    public Item(int id, String name, String type, String location, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.price = price;
    }

    public int getId(){ return id; }
    public String getName(){ return name; }
    public String getType(){ return type; }
    public String getLocation(){ return location; }
    public double getPrice(){ return price; }

    
}
