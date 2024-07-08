package com.company.tour.view.officeplan;


import com.company.tour.app.OfficePlanUtils;
import com.company.tour.entity.User;
import com.company.tour.view.main.MainView;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import io.jmix.core.FileStorage;
import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.model.feature.*;
import io.jmix.mapsflowui.component.model.layer.VectorLayer;
import io.jmix.mapsflowui.component.model.source.DataVectorSource;
import io.jmix.mapsflowui.component.model.source.VectorSource;
import io.jmix.mapsflowui.kit.component.model.style.Fill;
import io.jmix.mapsflowui.kit.component.model.style.MarkerStyle;
import io.jmix.mapsflowui.kit.component.model.style.Style;
import io.jmix.mapsflowui.kit.component.model.style.image.Anchor;
import io.jmix.mapsflowui.kit.component.model.style.image.CircleStyle;
import io.jmix.mapsflowui.kit.component.model.style.image.IconStyle;
import io.jmix.mapsflowui.kit.component.model.style.stroke.Stroke;
import io.jmix.mapsflowui.kit.component.model.style.text.TextStyle;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "office-plan-view", layout = MainView.class)
@ViewController("OfficePlanView")
@ViewDescriptor("office-plan-view.xml")
public class OfficePlanView extends StandardView {
    
    @ViewComponent("officePlan.vector.source")
    private VectorSource source;

    @ViewComponent("officePlan.dataVector.dataSource")
    private DataVectorSource<User> dataSource;
    @Autowired
    private FileStorage fileStorage;

    @ViewComponent("officePlan.vector")
    private VectorLayer vector;

    @Subscribe
    public void onInit(final InitEvent event) {
        source.addFeature(new PointFeature(OfficePlanUtils.stairPoint));
        source.addFeature(new MultiPointFeature(OfficePlanUtils.freeSitsPoint));

        source.addFeature(new LineStringFeature(OfficePlanUtils.pathToStair));

        source.addFeature(new PolygonFeature(OfficePlanUtils.kitchenAndRestZone));
        source.addFeature(new MultiPolygonFeature(OfficePlanUtils.managerOffices));

        dataSource.setStyleProvider(user -> {
            if (user.getAvatar() == null) {
                return MarkerStyle.createDefaultStyle();
            }
            return new Style()
                    .withImage(new IconStyle()
                            .withAnchor(new Anchor(0.5, 1.2))
                            .withScale(0.08)
                            .withResource(new StreamResource(user.getAvatar().getFileName(),
                                    () -> fileStorage.openStream(user.getAvatar()))))
                    .withText(new TextStyle()
                            .withText(user.getFirstName() + " " + user.getLastName())
                            .withFont("bold 14px sans-serif"));
        });

        Fill fill = new Fill(OfficePlanUtils.VECTOR_FILL_COLOR);
        Stroke stroke = new Stroke()
                .withColor(OfficePlanUtils.VECTOR_STROKE_COLOR)
                .withWidth(4d);

        vector.addStyles(new Style()
                .withFill(fill)
                .withStroke(stroke)
                .withImage(new CircleStyle()
                        .withFill(fill)
                        .withStroke(stroke)
                        .withRadius(6)));
    }
    
    
}