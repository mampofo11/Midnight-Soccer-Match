
public class Player {
public Item[] inventory;
private int skillvalue;

    public Player() {
        inventory = new Item[5];
        this.skillvalue = 0; // Initialize skill points
    }

    public String ShowInv() {
        String inv = "";
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                inv += inventory[i].getName() + ", ";
            } else {
                inv += "[empty], ";
            }
        }
        return inv;
    }

    public void addItem(Item item) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) { // Find an empty spot in the inventory
                inventory[i] = item;
                System.out.println(item.getName() + " has been added to your inventory.");
                return;
            }
        }
        System.out.println("Your inventory is full. Cannot add " + item.getName() + ".");
    }

    public void useItem(String itemName) {
        // Check if the item exists in the inventory
       Item itemToUse = null;
    int itemIndex = -1;

    for (int i = 0; i < inventory.length; i++) {
        if (inventory[i] != null && inventory[i].getName().equalsIgnoreCase(itemName)) {
            itemToUse = inventory[i];
            itemIndex = i;
            break;
        }
    }

    if (itemToUse == null) {
        System.out.println("You don't have an item named '" + itemName + "' in your inventory.");
        return;
    }

    // Apply the item's effect
    if (itemName.equalsIgnoreCase("cleats")) {
        this.skillvalue += 2; // Add skill points for cleats
        System.out.println("You used the Cleats! Gained 2 skill points. Total skill points: " + this.skillvalue);
    } else if (itemName.equalsIgnoreCase("amulet")) {
        this.skillvalue += 8; // Add skill points for amulet
        System.out.println("You used the Amulet! Gained 8 skill points. Total skill points: " + this.skillvalue);
    } else {
        System.out.println("The " + itemToUse.getName() + " could not be used.");
        return;
    }

    // Remove the item from the inventory after using
    inventory[itemIndex] = null;
    System.out.println("The " + itemToUse.getName() + " has been removed from your inventory.");
    System.out.println("Inventory after using item: " + ShowInv());
}

    // Getter for skillvalue
    public int getSkillValue() {
        return this.skillvalue;
    }

    // Setter for skillvalue (optional, for external updates if needed)
    public void setSkillValue(int value) {
        this.skillvalue = value;
    }
    
    public int calculateSkillPoints() {
    int skillPoints = 0;
    for (Item item : inventory) {
        if (item != null) {
            if (item.getName().equalsIgnoreCase("cleats")) {
                skillPoints += 2;
            } else if (item.getName().equalsIgnoreCase("amulet")) {
                skillPoints += 8;
            }
        }
    }
    return skillPoints;
}

   
}