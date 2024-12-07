package com.project.projectCS50x.views;

import com.project.projectCS50x.controller.ChatController;
import com.project.projectCS50x.controller.VideogameController;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;


@Route("gamevault/home")
public class HomePageView extends AppLayout {

    private final VideogameController videogameController;
    private final ChatController chatController;

    public HomePageView(VideogameController videogameController,
                        ChatController chatController) {
        this.getElement().setAttribute("theme", "dark")
                .setAttribute("class", "app-layout");
        this.getElement().getStyle().setCursor("pointer");



        this.videogameController = videogameController;
        this.chatController = chatController;

        DrawerToggle toggle = new DrawerToggle();
        toggle.setThemeName("navigation-drawer-toggle");

        H1 title = new H1("Welcome to the Home Page!");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("font-weight", "bold").set("margin", "0 10px 0 0");


        SideNav nav = getSideNav();

        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);


        addToDrawer(scroller);
        addToNavbar(toggle, title);

        Button saveANewGame = new Button("Save a New Game");
        saveANewGame.setIcon(VaadinIcon.PLUS.create());
        saveANewGame.getElement().setAttribute("class", "btn btn-dark");
        saveANewGame.getElement().setAttribute("style", "margin-right: 10px;");
        saveANewGame.addClickListener(e -> {
            UI.getCurrent().navigate("/gamevault/uploadvideogame");
        });
        addToNavbar(saveANewGame);
}

    private SideNav getSideNav() {
        SideNav nav = new SideNav();

        SideNavItem dashboardGames = new SideNavItem("My Videogames",
                VideogamesView.class, VaadinIcon.BOOK.create());

        dashboardGames.getElement().setAttribute("theme", "primary");

        SideNavItem chat = new SideNavItem("Victor's Chat",
                ChatAIView.class, VaadinIcon.CHAT.create());
        chat.getElement().setAttribute("theme", "primary");

        nav.addItem(dashboardGames, chat);
        return nav;
    }
}
