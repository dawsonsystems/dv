package uk.co.devooght.stock.client.view;

import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import uk.co.devooght.stock.ProductDTO;

public class MainPanel extends LayoutContainer {

  TabPanel tabs;

  public MainPanel() {

    setBorders(false);
    setLayout(new FitLayout());

    tabs = new TabPanel();
    tabs.setBorders(false);
    tabs.setBodyBorder(true);

    tabs.add(dashboard());

    add(tabs);
  }

  public void showProduct(ProductDTO product) {
    TabItem item = tabs.getItemByItemId("" + product.getId());

    if (item == null) {
      item = productWindow(product);
      tabs.add(item);
    }

    tabs.setSelection(item);
  }

  private TabItem dashboard() {
    TabItem item = new TabItem();
    item.setText("Welcome/ Dashboard");
    item.setClosable(false);
    item.setLayout(new CenterLayout());
    item.add(new Html("<h1>Welcome to de Vooght Stock management</h1>"));
    return item;
  }

  private TabItem productWindow(ProductDTO dto) {
    TabItem item = new TabItem();
    item.setId(""+dto.getId());
    item.setClosable(true);

    item.setText(dto.getName() + "-" + dto.getProductCode());

    //TODO, add the other component views, SKU, Image etc

    //TODO, share this setup with the create dialog
    FormPanel formPanel = new FormPanel();

    formPanel.setFieldWidth(300);
    formPanel.setLabelWidth(200);

    TextField<String> name = new TextField<String>();
    name.setFieldLabel("Name");
    name.setAllowBlank(false);
    name.setValue(dto.getName());
    formPanel.add(name);

    TextField<String> code = new TextField<String>();
    code.setFieldLabel("Product Code");
    code.setAllowBlank(false);
    code.setValue(dto.getProductCode());
    formPanel.add(code);

    TextField<String> cost = new TextField<String>();
    cost.setFieldLabel("Cost Price (£)");
    cost.setAllowBlank(false);
    cost.setValue(dto.getCostPrice().toString());
    formPanel.add(cost);

    item.add(formPanel);

    return item;
  }

}
