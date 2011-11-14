package uk.co.devooght.stock.client.view;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;

public class TopPanel extends LayoutContainer {

  public TopPanel() {
    setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
    add(new Button("Create Product"));
    add(new Button("Create Collection"));
    setHeight(30);
  }

}

