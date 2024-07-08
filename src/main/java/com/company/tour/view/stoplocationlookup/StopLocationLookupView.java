package com.company.tour.view.stoplocationlookup;


import com.company.tour.entity.Stop;
import com.company.tour.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.maps.utils.GeometryUtils;
import io.jmix.mapsflowui.component.event.MapClickEvent;

@Route(value = "stop-location-lookup-view", layout = MainView.class)
@ViewController("StopLocationLookupView")
@ViewDescriptor("stop-location-lookup-view.xml")
@DialogMode(width = "60em", height = "40em")
public class StopLocationLookupView extends StandardView {

    @ViewComponent
    private InstanceContainer<Stop> stopDc;

    public void setItem(Stop item) {
        stopDc.setItem(item);
    }

    @Subscribe("map")
    public void onMapMapClick(final MapClickEvent event) {
        stopDc.getItem().setLocation(GeometryUtils.createPoint(
                event.getCoordinate().getX(),
                event.getCoordinate().getY()));
    }
}