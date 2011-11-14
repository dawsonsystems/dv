package uk.co.devooght.stock.client.view;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;

public class ProductTree extends ContentPanel {

  private TreePanel<ModelData> tree;

  public ProductTree() {
    setLayout(new FitLayout());

    setHeading("Collections and Products");

    setBorders(true);
    tree = new TreePanel(new TreeStore());
    add(tree);
  }

}
