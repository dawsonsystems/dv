package uk.co.devooght.stock;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import uk.co.devooght.stock.ProductDTO;

import java.util.List;

@RemoteServiceRelativePath("rpc")
public interface ProductService extends RemoteService {
  List<ProductDTO> getTree();
  Boolean saveProduct(ProductDTO productDTO);
}
