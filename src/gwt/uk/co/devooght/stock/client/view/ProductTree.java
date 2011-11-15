package uk.co.devooght.stock.client.view;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import uk.co.devooght.stock.ProductServiceAsync;
import uk.co.devooght.stock.ProductDTO;

import javax.inject.Inject;
import java.util.List;

public class ProductTree extends ContentPanel {

  private TreePanel<BeanModel> tree;
  private ProductServiceAsync productServiceAsync;

  @Inject
  public ProductTree(ProductServiceAsync productServiceAsync) {
    this.productServiceAsync = productServiceAsync;

    setLayout(new FitLayout());

    setHeading("Collections and Products");

    setBorders(true);

    RpcProxy treeProxy= new RpcProxy<List<ProductDTO>>() {
    @Override
      protected void load(Object loadConfig,
                AsyncCallback<List<ProductDTO>> callback) {
        ProductTree.this.productServiceAsync.getTree(callback);
      }
    };

    BaseTreeLoader<BeanModel> treeLoader = new BaseTreeLoader<BeanModel>(treeProxy, new TreeBeanModelReader());

    TreeStore<BeanModel> treeStore = new TreeStore<BeanModel>(treeLoader);

    tree = new TreePanel<BeanModel>(treeStore);
    tree.setDisplayProperty("name");

    add(tree);
  }

  public void load() {
    tree.getStore().getLoader().load();
  }

}
