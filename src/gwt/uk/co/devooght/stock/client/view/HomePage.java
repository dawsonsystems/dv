package uk.co.devooght.stock.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;

public class HomePage extends DockLayoutPanel {

    public HomePage() {
        super(Style.Unit.EM);

        add(new Label("WIBBLE MUNCHER"));

    }



}
