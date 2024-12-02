package com.project.projectCS50x.views;

import com.project.projectCS50x.controller.VideogameController;
import com.project.projectCS50x.model.Videogame;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.List;

@Route("/gamevault/videogames")
public class VideogamesView extends AppLayout {

    private final VideogameController videogameController;
    public VideogamesView(VideogameController videogameController) {
        this.videogameController = videogameController;
        DrawerToggle toggle = new DrawerToggle();
        toggle.setThemeName("navigation-drawer-toggle");

        H1 title = new H1("My Collection");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");


        SideNav nav= getSideNav();
        HorizontalLayout horizontalnav=getHorizontalNavigation();

        addToNavbar(horizontalnav);
        addToNavbar(toggle);
        addToNavbar(title);

        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);

        VerticalLayout header = new VerticalLayout(toggle, title);
        header.setClassName(LumoUtility.Padding.MEDIUM);
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.setWidthFull();

        addToDrawer(scroller);
        refreshVideogames();








    }

    private HorizontalLayout getHorizontalNavigation() {

        HorizontalLayout navigation = new HorizontalLayout();
        navigation.setSpacing(true);
        navigation.setWidthFull();
        navigation.setAlignItems(FlexComponent.Alignment.CENTER);

        return navigation;
    }

    private SideNav getSideNav() {
        SideNav nav = new SideNav();

        SideNavItem dashboardGames = new SideNavItem("Videogames",
                VideogamesView.class, VaadinIcon.BOOK.create());

        nav.addItem(dashboardGames);
        return nav;
    }

    private void refreshVideogames() {
        List<Videogame> videogames = videogameController.getAllVideogames();
        VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);
        content.setPadding(true);

        for (Videogame videogame : videogames) {
            //div da cambiare
            Div gameEntry = new Div();
            gameEntry.getStyle().set("border", "1px solid lightgray")
                    .set("padding", "10px")
                    .set("margin-bottom", "5px");

            gameEntry.add(new Paragraph("Title: " + videogame.getTitle()));
            gameEntry.add(new Paragraph("Platform: " + videogame.getPlatform()));
            gameEntry.add(new Paragraph("Genre: " + videogame.getGenre()));
            gameEntry.add(new Paragraph("Played Date: " + videogame.getPlayedDate()));
            gameEntry.add(new Paragraph("Personal Rating: " + videogame.getPersonalRating()));
            gameEntry.add(new Paragraph("Game Status: " + videogame.getGamestatus()));

            content.add(gameEntry);
        }

        if (videogames.isEmpty()) {
            content.add(new Paragraph("No videogames found."));
        }

        setContent(content);
    }





    }


