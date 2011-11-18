package uk.co.devooght.stock.client.view.maintabs.product;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import uk.co.devooght.stock.ProductDTO;
import uk.co.devooght.stock.ProductServiceAsync;
import uk.co.devooght.stock.SkuDTO;
import uk.co.devooght.stock.client.root.StockEvents;

import javax.sound.sampled.DataLine;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkuPanel extends ContentPanel {

  private EditorGrid<ModelData> grid;
  private ProductServiceAsync productServiceAsync;
  private Dispatcher dispatcher;
  private ProductDTO product;

  public SkuPanel(final Dispatcher dispatcher, final ProductDTO product, final ProductServiceAsync productServiceAsync) {
    this.dispatcher = dispatcher;
    this.product = product;
    this.productServiceAsync = productServiceAsync;

    setLayout(new FitLayout());

    setBorders(true);
    setHeading("SKU Information");

    ToolBar bar = new ToolBar();

    Button add = new Button("Add New SKU");

    bar.add(add);

    add.addSelectionListener(new SelectionListener<ButtonEvent>() {

      @Override
      public void componentSelected(ButtonEvent ce) {
        SkuDTO sku = new SkuDTO();
        sku.setInventoryLevel(0);
        sku.setPrice(0.00);
        sku.setStockCode(product.getProductCode() + "-1");

        BeanModelFactory factory = BeanModelLookup.get().getFactory(ProductDTO.class);

        BeanModel model = factory.createModel(sku);
        grid.stopEditing();
        grid.getStore().insert(model, 0);
        grid.startEditing(grid.getStore().indexOf(model), 0);
      }

    });

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
            BeanModelFactory factory = BeanModelLookup.get().getFactory(SkuDTO.class);

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

    grid = new EditorGrid<ModelData>(new ListStore<ModelData>(new BaseListLoader(treeProxy)), new ColumnModel(columns()));

//    grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
//      @Override
//      public void selectionChanged(SelectionChangedEvent<ModelData> modelDataSelectionChangedEvent) {
//        AppEvent ev = new AppEvent(StockEvents.PRODUCT_SELECTED);
//        ev.setData(modelDataSelectionChangedEvent.getSelectedItem());
//        dispatcher.dispatch(ev);
//      }
//    });

    add(grid);


    ToolBar toolBar = new ToolBar();

    toolBar.add(new Button("Reset", new SelectionListener<ButtonEvent>() {

      @Override
      public void componentSelected(ButtonEvent ce) {
        grid.getStore().rejectChanges();
      }
    }));

    toolBar.add(new Button("Save", new SelectionListener<ButtonEvent>() {

      @Override
      public void componentSelected(ButtonEvent ce) {
        grid.getStore().commitChanges();
        //load();
      }
    }));

    grid.getStore().addStoreListener(new StoreListener<ModelData>() {
      public void storeUpdate(StoreEvent<ModelData> se) {
        if (se.getOperation() == Record.RecordUpdate.COMMIT) {

          SkuDTO sku = new SkuDTO();
          sku.setPrice((Double) se.getModel().get("price"));
          sku.setStockCode((String) se.getModel().get("stockCode"));
          sku.setInventoryLevel((Integer) se.getModel().get("inventoryLevel"));

          productServiceAsync.saveSku(product, sku, new AsyncCallback<Boolean>() {
            public void onFailure(Throwable caught) {
              Info.display("Failed saving SKU", caught.getMessage());
            }

            public void onSuccess(Boolean result) {
              Info.display("Saved SKU", "");
            }
          });
        }
      }
    });

    setBottomComponent(toolBar);

    load();
  }

  private List<ColumnConfig> columns() {

    ColumnConfig stockCode = new ColumnConfig("stockCode", "Stock Code", 200);
    stockCode.setEditor(new CellEditor(new TextField()));
    ColumnConfig price = new ColumnConfig("price", "Price", 200);
    price.setEditor(new CellEditor(new NumberField()));
    ColumnConfig inventory = new ColumnConfig("inventoryLevel", "Inventory", 200);

    NumberField field = new NumberField();
    field.setPropertyEditorType(Integer.class);

    inventory.setEditor(new CellEditor(field));

    return Arrays.asList(stockCode, price, inventory);
  }
  public void load() {
    grid.getStore().getLoader().load();
  }
}
