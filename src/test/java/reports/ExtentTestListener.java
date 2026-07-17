package reports;

import com.aventstack.extentreports.ExtentTest;
import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;

public class ExtentTestListener implements ITestListener {

    private static final ThreadLocal<ExtentTest> testeAtual =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult resultado) {

        ExtentTest teste = ExtentManager
                .getInstance()
                .createTest(
                        resultado.getMethod().getMethodName()
                );

        testeAtual.set(teste);
    }

    @Override
    public void onTestSuccess(ITestResult resultado) {

        testeAtual.get().pass(
                "Teste executado com sucesso."
        );
    }

    @Override
    public void onTestFailure(ITestResult resultado) {

        ExtentTest teste = testeAtual.get();

        teste.fail(resultado.getThrowable());

        WebDriver driver = DriverFactory.getDriver();

        if (driver != null) {
            try {
                String caminhoScreenshot =
                        ScreenshotUtils.capturarScreenshot(
                                driver,
                                resultado.getName()
                        );

                teste.addScreenCaptureFromPath(
                        caminhoScreenshot,
                        "Evidência da falha"
                );

            } catch (Exception erro) {
                teste.warning(
                        "Não foi possível anexar o screenshot: "
                                + erro.getMessage()
                );
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult resultado) {

        testeAtual.get().skip(
                "Teste ignorado."
        );
    }

    @Override
    public void onFinish(ITestContext contexto) {

        ExtentManager.getInstance().flush();

        testeAtual.remove();
    }
}