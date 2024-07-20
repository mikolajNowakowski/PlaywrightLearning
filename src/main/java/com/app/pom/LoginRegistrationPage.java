package com.app.pom;

import com.app.annotations.LazyAutowired;
import com.app.annotations.WebPage;
import com.app.model.locators.LocatorsProperties;
import org.springframework.beans.factory.annotation.Qualifier;

@WebPage
public class LoginRegistrationPage extends BasePage {

    @LazyAutowired
    @Qualifier("loginRegistrationPageLocators")
    private LocatorsProperties loginRegistrationPageProp;

    private String registrationEmail = "registrationEmail";
    private String registrationPassword = "registrationPassword";
    private String registerSubmitButton = "registerSubmitButton";
    private String loginUsername = "loginUsername";
    private String loginPassword = "loginPassword";
    private String submitLoginButton = "submitLoginButton";
    private String rememberMeCheckbox = "rememberMeCheckbox";


    public LoginRegistrationPage fillRegistrationMail(String email) {
        fill(registrationEmail, email, loginRegistrationPageProp);
        return this;
    }

    public LoginRegistrationPage typeRegistrationMail(String email) {
        type(registrationEmail, email, loginRegistrationPageProp);
        return this;
    }

    public LoginRegistrationPage fillRegistrationPassword(String password) {
        fill(registrationPassword, password, loginRegistrationPageProp);
        return this;
    }

    public LoginRegistrationPage typeRegistrationPassword(String password) {
        type(registrationPassword, password, loginRegistrationPageProp);
        return this;
    }

    public LoginRegistrationPage fillLoginMail(String email) {
        fill(loginUsername, email, loginRegistrationPageProp);
        return this;
    }

    public LoginRegistrationPage fillLoginPassword(String password) {
        fill(loginPassword, password, loginRegistrationPageProp);
        return this;
    }

    public LoginRegistrationPage submitRegistration(){
        click(registerSubmitButton,loginRegistrationPageProp);
        return this;
    }

    public LoginRegistrationPage submitLogin(){
        click(submitLoginButton,loginRegistrationPageProp);
        return this;
    }

}
