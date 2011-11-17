package uk.co.devooght.stock.client.view.maintabs;

import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import uk.co.devooght.stock.ProductDTO;
import uk.co.devooght.stock.client.root.StockEvents;

import java.math.BigDecimal;

public class ProductTab extends TabItem {

  private Dispatcher dispatcher;
  private ProductDTO product;
  private TextField<String> name;
  private TextField<String> code;
  private TextField<String> cost;

  public ProductTab(Dispatcher dispatcher, ProductDTO productDTO) {
    this.dispatcher = dispatcher;
    this.product = productDTO;
    setup();
  }

  private void setup() {
    setId(""+product.getId());
    setClosable(true);

    setText(product.getName() + "-" + product.getProductCode());

    //TODO, add the other component views, SKU, Image etc

    //TODO, share this setup with the create dialog
    final FormPanel formPanel = new FormPanel();

    formPanel.setFieldWidth(300);
    formPanel.setLabelWidth(200);

    name = new TextField<String>();
    name.setFieldLabel("Name");
    name.setAllowBlank(false);
    name.setValue(product.getName());
    formPanel.add(name);

    code = new TextField<String>();
    code.setFieldLabel("Product Code");
    code.setAllowBlank(false);
    code.setValue(product.getProductCode());
    formPanel.add(code);

    cost = new TextField<String>();
    cost.setFieldLabel("Cost Price (£)");
    cost.setAllowBlank(false);
    cost.setValue(product.getCostPrice().toString());
    formPanel.add(cost);

    ButtonBar buttons = new ButtonBar();

    Button save =new Button("Save");

    save.addSelectionListener(new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent buttonEvent) {
        if (formPanel.isValid()) {
          AppEvent event = new AppEvent(StockEvents.SAVE_PRODUCT);
          event.setData(getProduct());
          dispatcher.dispatch(event);
        }
      }
    });

    buttons.add(save);

    formPanel.setBottomComponent(buttons);

    add(formPanel);
  }

  private ProductDTO getProduct() {
    product.setName(name.getValue());
    product.setCostPrice(new BigDecimal(cost.getValue()));
    product.setProductCode(code.getValue());

    return product;
  }

}
