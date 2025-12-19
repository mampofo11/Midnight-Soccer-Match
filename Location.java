//This class represents a Location that can be interacted with by the player.  

public class Location {
    // State of the location object
    String name;
    String description;

    // The arrays could be ArrayLists for ease of use - look them up and learn how
    // you can use them.
    Item[] itemsHere; // to hold all of the items in this location.
    Location[] exits; // to hold all of the locations that you can get to from this location.


    public Location() {
        itemsHere = new Item[0];
    }
    
    // This is a method to check and see if an item is in the room.
    // You don't have to use this but it could be a good way to try to
    // pick up items by first checkecking to see if they are in the room.
    
    public Item getItem(String itemName) {
        // Define a "blank" or "empty" item placeholder
        Item emptyItem = new Item("empty", "This item has been removed from the location.");
        
        for (int i = 0; i < itemsHere.length; i++) {
            if (itemsHere[i] != null && itemsHere[i].getName().equalsIgnoreCase(itemName)) {
                Item retrievedItem = itemsHere[i];
                itemsHere[i] = emptyItem; // Replace the item with a blank placeholder
                return retrievedItem;
            }
        }
        return null; 
    }



public void showItems(){
    if(itemsHere != null && itemsHere.length > 0){
        System.out.println("Items in this location:");
        for(Item item: itemsHere){
            System.out.println("- " + item.getName() + ":" + item.getDesc());
        }
    } else{
        System.out.println("There are no items in this location.");
    }
}

}

