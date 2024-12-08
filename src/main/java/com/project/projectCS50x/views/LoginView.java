package com.project.projectCS50x.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route("/login")
@PageTitle("Login")

public class LoginView extends Div {

    public LoginView() {

        LoginI18n i18n = LoginI18n.createDefault();

        setSizeFull();

        LoginI18n.Header i18nHeader = new LoginI18n.Header();
        i18nHeader.setTitle("Login");
        i18nHeader.setDescription("Identify yourself traveller");
        i18n.setHeader(i18nHeader);

        LoginI18n.Form loginForm = i18n.getForm();
        loginForm.setTitle("Login");
        loginForm.setUsername("Username");
        loginForm.setPassword("Password");
        loginForm.setSubmit("Log In");
        loginForm.setForgotPassword("Did you forget the secret word didn't you?");

        i18n.setForm(loginForm);

        LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
        i18nErrorMessage.setTitle("Wrong secret word traveller!");
        i18nErrorMessage.setMessage(
                "I don't know you!");
        i18n.setErrorMessage(i18nErrorMessage);

        i18n.setAdditionalInformation("We need to recognize you in order to access into the WasteLand, traveller");

        LoginOverlay loginOverlay = new LoginOverlay();
        loginOverlay.setI18n(i18n);
        loginOverlay.setAction("login");
        loginOverlay.setOpened(true);
        loginOverlay.getElement().setAttribute("theme", "light");

        add(loginOverlay);

    }

}
