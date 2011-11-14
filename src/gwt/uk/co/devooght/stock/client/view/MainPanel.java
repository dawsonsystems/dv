package uk.co.devooght.stock.client.view;

import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;

public class MainPanel extends LayoutContainer {


  public MainPanel() {

    setBorders(true);

    setLayout(new CenterLayout());

    add(new Html("<h1>Welcome to de Vooght Stock management</h1>"));

    //TODO, switching of the content of this panel
  }

}
