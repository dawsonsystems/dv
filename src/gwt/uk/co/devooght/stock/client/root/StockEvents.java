package uk.co.devooght.stock.client.root;


import com.extjs.gxt.ui.client.event.EventType;

public class StockEvents {

  //user wants to create a new product
  public static EventType CREATE_PRODUCT = new EventType();
  //user is saving a product, either new or updating.
  public static EventType SAVE_PRODUCT = new EventType();

  public static EventType CREATE_COLLECTION = new EventType();

  public static EventType START_APP = new EventType();
  public static EventType REFRESH_PRODUCTS = new EventType();

  public static EventType PRODUCT_SELECTED = new EventType();
}
