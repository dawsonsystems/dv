package uk.co.devooght.stock.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import uk.co.devooght.stock.client.gin.StockGinjector;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class StockEntryPoint implements EntryPoint {
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        StockGinjector ginjector = GWT.create(StockGinjector.class);
        RootLayoutPanel.get().add(ginjector.getHomePage());
    }
}
