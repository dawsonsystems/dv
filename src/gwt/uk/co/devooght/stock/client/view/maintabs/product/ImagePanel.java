package uk.co.devooght.stock.client.view.maintabs.product;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import uk.co.devooght.stock.ProductDTO;

public class ImagePanel extends ContentPanel {

  public ImagePanel(final Dispatcher dispatcher, ProductDTO product) {
    setBorders(true);
    add(new Label("The IMAGESSSSSS!!"));
    setHeaderVisible(false);

    ToolBar bar = new ToolBar();

    bar.add(new Button("Upload New Image"));

    setTopComponent(bar);
  }
}
