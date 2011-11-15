package uk.co.devooght.stock;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

public interface ProductServiceAsync {
  void getTree(AsyncCallback<List<ProductDTO>> callback);
}
