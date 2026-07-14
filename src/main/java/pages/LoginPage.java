package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By txtUsuario = By.id("user-name");
    private By txtSenha = By.id("password");
    private By btnLogin = By.id("login-button");
    private By lblErro = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void acessarSite() {
        driver.get("https://www.saucedemo.com");
    }

    private void informarUsuario(String usuario) {
        escrever(txtUsuario, usuario);
    }

    private void informarSenha(String senha) {
        escrever(txtSenha, senha);
    }

    private void clicarLogin() {
        clicar(btnLogin);
    }

    public void login(String usuario, String senha) {

        informarUsuario(usuario);
        informarSenha(senha);
        clicarLogin();

    }

    public String obterMensagemErro() {

        return obterTexto(lblErro);

    }

}