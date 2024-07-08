package com.company.tour.view.tourexplorer;


import com.company.tour.app.TourExplorerUtils;
import com.company.tour.entity.City;
import com.company.tour.entity.CityDistrict;
import com.company.tour.entity.Stop;
import com.company.tour.entity.Tour;
import com.company.tour.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.GeoMap;
import io.jmix.mapsflowui.component.model.source.DataVectorSource;
import io.jmix.mapsflowui.kit.component.model.style.Fill;
import io.jmix.mapsflowui.kit.component.model.style.Style;
import io.jmix.mapsflowui.kit.component.model.style.image.Anchor;
import io.jmix.mapsflowui.kit.component.model.style.image.IconStyle;
import io.jmix.mapsflowui.kit.component.model.style.stroke.Stroke;
import io.jmix.mapsflowui.kit.component.model.style.text.TextStyle;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

@Route(value = "tour-explorer-view", layout = MainView.class)
@ViewController("TourExplorerView")
@ViewDescriptor("tour-explorer-view.xml")
public class TourExplorerView extends StandardView {
    @ViewComponent
    private CollectionContainer<City> citiesDc;
    @ViewComponent
    private EntityComboBox<City> citiesComboBox;
    @ViewComponent("map.districtsLayer.districtsSource")
    private DataVectorSource<CityDistrict> districtsSource;
    @ViewComponent("map.toursLayer.toursSource")
    private DataVectorSource<Tour> toursSource;

    @ViewComponent("map.stopsLayer.stopsSource")
    private DataVectorSource<Stop> stopsSource;
    @ViewComponent
    private GeoMap map;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        if (CollectionUtils.isNotEmpty(citiesDc.getItems())) {
            citiesComboBox.setValue(TourExplorerUtils.getBerlinCityFrom(citiesDc.getItems()));
        }
    }

    @Subscribe
    public void onInit(final InitEvent event) {
        districtsSource.setStyleProvider(district -> new Style()
                .withFill(new Fill(TourExplorerUtils.getDistrictFillColor(district.getName())))
                .withStroke(new Stroke()
                        .withColor(TourExplorerUtils.getDistrictStrokeColor(district.getName()))
                        .withWidth(2d)));
        toursSource.setStyleProvider(tour -> new Style()
                .withStroke(new Stroke()
                        .withWidth(6d)
                        .withLineDash(List.of(15d, 10d))
                        .withColor(tour.getRouteColor())));

        stopsSource.setStyleProvider(stop -> new Style()
                .withImage(new IconStyle()
                        .withScale(0.05)
                        .withSrc("icons/map/sight-icon.png")
                        .withAnchor(new Anchor(0.5, 1.15)))
                .withText(new TextStyle()
                        .withOffsetY(10)
                        .withText(stop.getName())
                        .withFont("bold 14px sans-serif")));

        stopsSource.addGeoObjectClickListener(stopGeoObjectClickEvent -> {
            map.setCenter(stopGeoObjectClickEvent.getItem().getLocation().getCoordinate());
        });
    }
    
    
}