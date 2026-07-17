package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    private ScreenshotUtils() {
    }

    public static String capturarScreenshot(
            WebDriver driver,
            String nomeTeste
    ) {
        String dataHora = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(
                        "yyyy-MM-dd_HH-mm-ss"
                ));

        String caminhoArquivo = "target/screenshots/"
                + nomeTeste
                + "_"
                + dataHora
                + ".png";

        File screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        File destino = new File(caminhoArquivo);

        try {
            FileUtils.copyFile(screenshot, destino);

            System.out.println(
                    "Screenshot salvo em: "
                            + destino.getAbsolutePath()
            );

            return destino.getAbsolutePath();

        } catch (IOException erro) {
            throw new RuntimeException(
                    "Erro ao salvar o screenshot.",
                    erro
            );
        }
    }
}