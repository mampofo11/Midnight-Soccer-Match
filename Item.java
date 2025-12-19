
public class Item{
    private String name;
    private String description;

    public Item() {
    };
    public Item(String n, String d) {
        name = n;
        description = d;
    }
    public String getDesc() {
        return description;
    }
    public String getName() {
        return name;
       
    }
    public boolean applyEffect() {
        switch (name.toLowerCase()) {
            case "cleats":
                System.out.println("You use the cleats. You feel stronger and faster!");
                return true;
            case "amulet":
                System.out.println("You use the amulet. Your shooting bailities increase exponentially!!!");
                return true;
            case "ball":
            default:
                System.out.println("The " + name + " doesn't seem to have any effect.");
                return false;
        }
    }

   
}




