package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extentReports;

    private ExtentManager() {
    }

    public static ExtentReports getInstance() {

        if (extentReports == null) {

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter("target/reports/relatorio-testes.html");

            sparkReporter.config().setDocumentTitle(
                    "Relatório de Automação"
            );

            sparkReporter.config().setReportName(
                    "Selenium Java Automation"
            );

            extentReports = new ExtentReports();

            extentReports.attachReporter(sparkReporter);

            extentReports.setSystemInfo(
                    "Projeto",
                    "Selenium Java Automation"
            );

            extentReports.setSystemInfo(
                    "Framework",
                    "Selenium WebDriver"
            );

            extentReports.setSystemInfo(
                    "Linguagem",
                    "Java"
            );
        }

        return extentReports;
    }
}