package com.project.projectCS50x.views;

import com.project.projectCS50x.controller.ChatController;
import com.project.projectCS50x.controller.VideogameController;
import com.project.projectCS50x.security.SecurityService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route("/gamevault/home")
public class HomePageView extends AppLayout {

    private final VideogameController videogameController;
    private final ChatController chatController;
    private final SecurityService securityService;

    public HomePageView(VideogameController videogameController,
                        ChatController chatController, SecurityService securityService) {
        this.securityService = securityService;
//        createHeader();
        this.getElement().setAttribute("theme", "light")
                .setAttribute("class", "app-layout");



        this.videogameController = videogameController;
        this.chatController = chatController;

        DrawerToggle toggle = new DrawerToggle();
        toggle.setThemeName("navigation-drawer-toggle");

        H1 title = new H1("Welcome Traveller...");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("font-weight", "bold").set("margin", "0 10px 0 0").set("font-style", "italic");


        SideNav nav = getSideNav();
        nav.addClassName("nav");

        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);


        String u = securityService.getAuthenticatedUser().getUsername();
        Button logout = new Button("Log out " + u, e -> securityService.logout());
        logout.getElement().setAttribute("class", "btn btn-dark").setAttribute("style", "margin-right: 10px;");
        addToDrawer(scroller);
        addToNavbar(toggle, title, logout);

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
//
//    private void createHeader() {
//
//        String u = securityService.getAuthenticatedUser().getUsername();
//        Button logout = new Button("Log out " + u, e -> securityService.logout());
//
//        var header = new HorizontalLayout(new DrawerToggle(), logout);
//
//        header.setWidthFull();
//        header.addClassNames(
//                LumoUtility.Padding.Vertical.NONE,
//                LumoUtility.Padding.Horizontal.MEDIUM);
//
//        addToNavbar(header);
//
//    }
}
