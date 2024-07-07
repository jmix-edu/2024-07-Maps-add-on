package com.company.tour.view.osmtile;


import com.company.tour.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "osm-tile-view", layout = MainView.class)
@ViewController("OsmTileView")
@ViewDescriptor("osm-tile-view.xml")
public class OsmTileView extends StandardView {
}