package uk.co.devooght.stock.client.root;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import uk.co.devooght.stock.client.view.HomePage;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class StockEntryPoint implements EntryPoint {
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
//        StockGinjector ginjector = GWT.create(StockGinjector.class);
        RootLayoutPanel.get().add(new HomePage());
    }
}
