package com.project.projectCS50x.views;

import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/login")
@PageTitle("Login")
public class LoginView extends Div {

    public LoginView() {
        this.addClassName("login-view");
        LoginI18n i18n = LoginI18n.createDefault();

        LoginI18n.Header i18nHeader = new LoginI18n.Header();
        i18nHeader.setTitle("Login");
        i18nHeader.setDescription("Please Log In");
        i18n.setHeader(i18nHeader);

        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("Login");
        i18nForm.setUsername("Username");
        i18nForm.setPassword("Password");
        i18nForm.setSubmit("Log In");
        i18nForm.setForgotPassword("Did you forgot password?");
        i18n.setForm(i18nForm);

        LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
        i18nErrorMessage.setTitle("Wrong password");
        i18nErrorMessage.setMessage(
                "Wrong Password or Username");
        i18n.setErrorMessage(i18nErrorMessage);

        i18n.setAdditionalInformation("We need to recognize you to access into the WasteLand traveller");

        LoginOverlay loginOverlay = new LoginOverlay();
        loginOverlay.setI18n(i18n);
        loginOverlay.setAction("login");
        loginOverlay.setOpened(true);
        loginOverlay.getElement().setAttribute("no-autofocus", "");
        add(loginOverlay);


    }

}
