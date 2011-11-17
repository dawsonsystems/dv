package uk.co.devooght.stock.client.view.maintabs.product;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import uk.co.devooght.stock.ProductDTO;
import uk.co.devooght.stock.client.root.StockEvents;

import java.math.BigDecimal;

public class BasicInfoPanel extends FormPanel {

  private TextField<String> name;
  private TextField<String> code;
  private TextField<String> cost;
  private ProductDTO product;

  public BasicInfoPanel(final Dispatcher dispatcher, ProductDTO product) {
    this.product = product;
    setFieldWidth(300);
    setLabelWidth(200);

     //TODO, share this setup with the create dialog
    setHeading("Product Information");
    name = new TextField<String>();
    name.setFieldLabel("Name");
    name.setAllowBlank(false);
    name.setValue(product.getName());
    add(name);

    code = new TextField<String>();
    code.setFieldLabel("Product Code");
    code.setAllowBlank(false);
    code.setValue(product.getProductCode());
    add(code);

    cost = new TextField<String>();
    cost.setFieldLabel("Cost Price (£)");
    cost.setAllowBlank(false);
    cost.setValue(product.getCostPrice().toString());
    add(cost);

    ButtonBar buttons = new ButtonBar();

    Button save = new Button("Save");

    save.addSelectionListener(new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent buttonEvent) {
        if (isValid()) {
          AppEvent event = new AppEvent(StockEvents.SAVE_PRODUCT);
          event.setData(getProduct());
          dispatcher.dispatch(event);
        }
      }
    });

    buttons.add(save);

    add(buttons);
  }
  private ProductDTO getProduct() {
    product.setName(name.getValue());
    product.setCostPrice(new BigDecimal(cost.getValue()));
    product.setProductCode(code.getValue());

    return product;
  }
}
