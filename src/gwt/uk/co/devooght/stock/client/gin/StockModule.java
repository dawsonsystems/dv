package uk.co.devooght.stock.client.gin;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.AbstractModule;
import uk.co.devooght.stock.client.control.ProductTreeController;
import uk.co.devooght.stock.client.view.*;

public class StockModule extends AbstractGinModule {

  static {
    System.out.println("WOOT");
  }

    @Override
    protected void configure() {
//        bind()
      //bind(HomePage.class).asEagerSingleton();

      views();
      controllers();
      util();
    }

    private void util() {
      bind(Dispatcher.class).toProvider(DispatcherProvider.class);
    }

    private void controllers() {
      bind(ProductTreeController.class).asEagerSingleton();
    }

    private void views() {

      bind(HomePage.class).asEagerSingleton();
      bind(ProductTree.class).asEagerSingleton();
      bind(MainPanel.class).asEagerSingleton();
      bind(SearchPanel.class).asEagerSingleton();
      bind(TopPanel.class).asEagerSingleton();
    }

}
