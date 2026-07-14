package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;


public class LoginTest extends BaseTest {

    @Test
    public void deveRealizarLogin() {

        LoginPage login = new LoginPage(driver);

        login.acessarSite();

        login.login(
                "standard_user",
                "secret_sauce"
        );

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html"
        );
    }

    @Test
    public void deveFalharLoginComSenhaInvalida() {

        LoginPage login = new LoginPage(driver);

        login.acessarSite();

        login.login(
                "standard_user",
                "senha_errada"
        );

        Assert.assertEquals(
                login.obterMensagemErro(),
                "Epic sadface: Username and password do not match any user in this service"
        );
    }

    }

