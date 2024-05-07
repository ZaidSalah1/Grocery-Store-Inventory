package grocery.store.inventory;
import java.util.ArrayList;
import javafx.scene.control.Label;


public class Inventory extends Item {

    static ArrayList<Item> inventory = new ArrayList<>();
    private String categoty;

    public Inventory() {
    }

    public Inventory(String categoty) {
        this.categoty = categoty;
    }

    public void newItem(String type, int quantity, double price) {

        if (findItem(type, true) == -1) {//if = -1 that's mean this item not exsist so add new item
            inventory.add(new Item(type).setQuantity(quantity).setPrice(price));
        } 
    }

    //newItem method for brand type
    public void newItem(String brand, String type, int quantity, double price) {

        if (findItem(type, true, brand) == -1) {//if = -1 that's mean this item not exsist so add new item
            inventory.add(new Brand(brand, type).setQuantity(quantity).setPrice(price));
        }
    }

    public int findItem(String type, boolean warningIfFound) {
        int itemsFound = 0;//counter
        int index = -1;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getType().equals(type)) {//if index of inventory = type
                itemsFound++;
                index = i;
                warningIfFound = true;
            }
        }
        if (itemsFound > 0 && itemsFound <= 1 && warningIfFound == true) {      
            return 0;// return 0 if this item found before
        } 
        return -1;// return -1 if item not found
    }

    public int findItem(String type, boolean warningIfFound, String brand) {
        int itemsFound = 0;//counter
        int index = -1;
        Item item = null;
        for (int i = 0; i < inventory.size(); i++) {
            item = inventory.get(i);
            if (type.equals(item.getType()) && item instanceof Brand && brand.equals(((Brand) item).getBrand())) {
                itemsFound++;
                index = i;
                warningIfFound = true;
            }
        }
        if (itemsFound > 0 && itemsFound <= 1 && warningIfFound == true) {
            return 0;// return 0 if this item found before
        }
        return -1;// return -1 if item not found
    }

    public void deleteItem(String type, int quantity, double price) {
        if (findItem(type, true) != -1) {
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getType().equals(type) && inventory.get(i).getQuantity() == quantity
                        && inventory.get(i).getPrice() == price) {
                    inventory.remove(i);

                }
            }
        }
     
    }

    public int deleteItem(String brand, String type, int quantity, double price) {
        Item item = null;

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getType().equals(type) && inventory.get(i).getQuantity() == quantity
                    && inventory.get(i).getPrice() == price) {
                inventory.remove(i);
                return 1;// Item found
            }
        }
        return 0;//cannot find the item
    }

    //Update method for item type "quantity" 
    public void update(String type, int qtyIncrease) {
        if (findItem(type, true) != -1) {
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getType().equals(type)) {
                    qtyIncrease = qtyIncrease + inventory.get(i).getQuantity();
                    inventory.get(i).setQuantity(qtyIncrease);
                }
            }
        }
    }

    //Update method for item "Price" 
    public void update(String type, double adjustmentFactor) {
        if (findItem(type, true) != -1) {
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getType().equals(type)) {
                    adjustmentFactor = adjustmentFactor + inventory.get(i).getPrice();
                    inventory.get(i).setPrice(adjustmentFactor);
                }
            }
        } else {
            System.out.println("Cannot find " + type);
        }
    }

    //Update method for brand type "quantity" 
    public void update(String brand, String type, int qtyIncrease) {
        if (findItem(type, true, brand) != -1) {
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getType().equals(type)) {
                    qtyIncrease = qtyIncrease + inventory.get(i).getQuantity();
                    inventory.get(i).setQuantity(qtyIncrease);
                }
            }
        } 
    }

    //Update method for brand type "Price" 
    public void update(String brand, String type, double adjustmentFactor) {
        if (findItem(type, true, brand) != -1) {
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getType().equals(type)) {
                    adjustmentFactor = adjustmentFactor + inventory.get(i).getPrice();
                    inventory.get(i).setPrice(adjustmentFactor);
                }
            }
        }       
    }

}

