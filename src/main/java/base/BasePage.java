package base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement esperarElemento(By elemento) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(elemento)
        );
    }

    protected void escrever(By elemento, String texto) {

        WebElement campo = esperarElemento(elemento);

        campo.clear();
        campo.sendKeys(texto);

    }

    protected void clicar(By elemento) {

        esperarElemento(elemento).click();

    }

    protected String obterTexto(By elemento) {

        return esperarElemento(elemento).getText();

    }

    protected boolean estaVisivel(By elemento) {

        return esperarElemento(elemento).isDisplayed();

    }

}