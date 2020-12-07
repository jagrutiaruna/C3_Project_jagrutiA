import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest{
    Restaurant restaurant;
    public Restaurant createRestaurantInstance(){

        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
      return restaurant;
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){

        restaurant = createRestaurantInstance();
        LocalTime runTime     = LocalTime.parse("13:30:00");
        Restaurant restaurantSpy = Mockito.spy(restaurant);
        Mockito.when(restaurantSpy.getCurrentTime()).thenReturn(runTime);
        boolean openRestaurant = restaurantSpy.isRestaurantOpen();
        assertTrue(openRestaurant);


    }


    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        restaurant = createRestaurantInstance();
        LocalTime runTime     = LocalTime.parse("10:00:00");
        Restaurant restaurantSpy = Mockito.spy(restaurant);
        Mockito.when(restaurantSpy.getCurrentTime()).thenReturn(runTime);
        boolean openRestaurant = restaurantSpy.isRestaurantOpen();
        assertEquals(false,openRestaurant);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        restaurant = createRestaurantInstance();
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        restaurant =createRestaurantInstance();
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        restaurant = createRestaurantInstance();
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //TDD
    //Take parameter - List of Items to Calculate the order total,
    // the method should bring the corresponding price from Item class
    // return value should be total price of selected items

    
    @Test
    public void calculateOrderTotal_takes_listOfItems_as_parameter_and_returns_total_cost(){
        restaurant = createRestaurantInstance();
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("coffee",20);
        restaurant.addToSelectedItem("coffee");
        restaurant.addToSelectedItem("Vegetable lasagne");
        int orderTotal = restaurant.calculateOrderTotal(restaurant.getSelectedItem());
        assertEquals(289,orderTotal);

    }

}