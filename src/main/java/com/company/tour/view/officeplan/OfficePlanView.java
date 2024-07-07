package com.company.tour.view.officeplan;


import com.company.tour.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "office-plan-view", layout = MainView.class)
@ViewController("OfficePlanView")
@ViewDescriptor("office-plan-view.xml")
public class OfficePlanView extends StandardView {
}