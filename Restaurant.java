
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<>();
    private List<Item> selectedItem = new ArrayList<>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }
    public boolean isRestaurantOpen() {
        if (getCurrentTime().isAfter(openingTime) && getCurrentTime().isBefore(this.closingTime)) { return true; }
        return false;
    }
    public LocalTime getCurrentTime(){ return  LocalTime.now(); }
    public List<Item> getMenu() { return this.menu; }
    public String getName() { return name; }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {
        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);
        menu.remove(itemToBeRemoved);
    }


    public void addToSelectedItems(String itemName){
        selectedItem.add(findItemByName(itemName));
    }
    public List<Item> getSelectedItem(){ return this.selectedItem; }

    public int calculateOrderTotal(List<Item> selectedItemList){
        int orderTotal = 0;
        for(Item item: selectedItemList){
          orderTotal =  orderTotal + item.getPrice();
        }
       return orderTotal;
    }

}
