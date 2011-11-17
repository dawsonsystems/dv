package uk.co.devooght.stock.client.view.maintabs.product;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import uk.co.devooght.stock.ProductDTO;
import uk.co.devooght.stock.ProductServiceAsync;
import uk.co.devooght.stock.SkuDTO;
import uk.co.devooght.stock.client.root.StockEvents;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkuPanel extends ContentPanel {

  private Grid<ModelData> grid;
  private ProductServiceAsync productServiceAsync;
  private Dispatcher dispatcher;
  private ProductDTO product;


  public SkuPanel(final Dispatcher dispatcher, final ProductDTO product, final ProductServiceAsync productServiceAsync) {
    this.dispatcher = dispatcher;
    this.product = product;
    this.productServiceAsync = productServiceAsync;

    setBorders(true);
    setHeading("SKU Information");

    ToolBar bar = new ToolBar();

    bar.add(new Button("Add New SKU"));

    setTopComponent(bar);

    RpcProxy treeProxy= new RpcProxy<List<ModelData>>() {
    @Override
      protected void load(Object loadConfig,
                final AsyncCallback<List<ModelData>> callback) {
        productServiceAsync.getSkus(product, new AsyncCallback<List<SkuDTO>>() {
          public void onFailure(Throwable caught) {
            callback.onFailure(caught);
          }

          public void onSuccess(List<SkuDTO> result) {
            List<ModelData> ret = new ArrayList();
            BeanModelFactory factory = BeanModelLookup.get().getFactory(ProductDTO.class);

            for (SkuDTO dto : result) {
              ret.add(factory.createModel(dto));
            }
            callback.onSuccess(ret);
          }
        });
      }
    };

    Button refresh = new Button("Refresh");

    refresh.addSelectionListener(new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent buttonEvent) {
        load();
        buttonEvent.preventDefault();
        buttonEvent.setCancelled(true);
        buttonEvent.stopEvent();
      }
    });

    getHeader().addTool(refresh);

    grid = new Grid<ModelData>(new ListStore<ModelData>(new BaseListLoader(treeProxy)), new ColumnModel(columns()));

//    grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
//      @Override
//      public void selectionChanged(SelectionChangedEvent<ModelData> modelDataSelectionChangedEvent) {
//        AppEvent ev = new AppEvent(StockEvents.PRODUCT_SELECTED);
//        ev.setData(modelDataSelectionChangedEvent.getSelectedItem());
//        dispatcher.dispatch(ev);
//      }
//    });

    add(grid);

    load();
  }

  private List<ColumnConfig> columns() {

    return Arrays.asList(
            new ColumnConfig("stockCode", "Stock Code", 200),
            new ColumnConfig("price", "Price", 200),
            new ColumnConfig("inventoryLevel", "Inventory", 200));
  }
  public void load() {
    grid.getStore().getLoader().load();
  }
}
