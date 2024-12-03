package com.project.projectCS50x.views;

import com.project.projectCS50x.controller.VideogameController;
import com.project.projectCS50x.model.Videogame;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
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
public class VideogamesView extends Div {

    private final VideogameController videogameController;
    public VideogamesView(VideogameController videogameController) {
        this.videogameController = videogameController;
        Grid<Videogame> videogameGrid = new Grid<>(Videogame.class, false); // false for read-only>
        videogameGrid.addColumn(Videogame::getTitle).setHeader("Title");
        videogameGrid.addColumn(Videogame::getGenre).setHeader("Genre");
        videogameGrid.addColumn(Videogame::getPlatform).setHeader("Platform");
        videogameGrid.addColumn(Videogame::getPlayedDate).setHeader("Played Date");
        videogameGrid.addColumn(Videogame::getGamestatus).setHeader("Game Status");
        videogameGrid.addColumn(Videogame::getPersonalRating).setHeader("Personal Rating");
//        videogameGrid.addColumn(createPictureRenderer()).setHeader("Picture")
//                .setAutoWidth(true).setFlexGrow(0);
        //videogameGrid.addColumn(Videogame::getImage).setHeader("Image");

        videogameGrid.setItems(videogameController.getAllVideogames());
        videogameGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        videogameGrid.getElement().getStyle().set("border-collapse", "collapse");
        videogameGrid.getElement().getStyle().set("border", "1px solid #ccc");

        add(videogameGrid);


        Button goBackToHome = new Button("Back to Home");
        goBackToHome.getElement().setAttribute("class", "btn btn-dark");
        goBackToHome.addClickListener(e -> {
            UI.getCurrent().navigate("/gamevault/home");
        });
        add(goBackToHome);

//        refreshVideogames();

    }

//    private HorizontalLayout getHorizontalNavigation() {
//
//        HorizontalLayout navigation = new HorizontalLayout();
//        navigation.setSpacing(true);
//        navigation.setWidthFull();
//        navigation.setAlignItems(FlexComponent.Alignment.CENTER);
//
//        return navigation;
//    }


//    private void refreshVideogames() {
//        UI.getCurrent().getPage().reload();
//
//    }





    }


