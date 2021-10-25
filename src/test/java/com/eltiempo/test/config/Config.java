package com.eltiempo.test.config;

import com.eltiempo.bl.utils.exception.NotFoundException;
import com.eltiempo.bl.utils.states.CaseExecutionState;
import com.eltiempo.controller.CaseController;
import com.eltiempo.controller.ReportController;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.util.function.Function;

public abstract class Config {

    protected static WebDriver driver;
    protected static Wait wait;
    private static CaseController cases = new CaseController();
    private static ReportController report = new ReportController();
    protected static long timer = System.nanoTime();

    protected static void restartTimer() {
        timer = System.nanoTime();
    }

    protected static int getSeconds() {
        return (int) timer / 1000000000;
    }

    protected static void createCase(long caseID, String caseName, String caseDescription, boolean hasToFail) {
        cases.createCase(caseID, caseName, caseDescription, hasToFail);
    }

    protected static void createReport(long reportID, long caseID) {
        try {
            report.createReport(reportID, cases.getCase(caseID), CaseExecutionState.NEW);
        } catch (NotFoundException e) {
            updateReport(reportID, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
        }
    }

    protected static void updateReport(long reportID, CaseExecutionState state, long timeTaken) {
        report.updateReport(reportID, state, timeTaken);
    }
    public static void config() {
        System.setProperty("webdriver.chrome.driver", "./lib/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to(Consts.HOME_PAGE);
        driver.manage().window().maximize();
    }

    public static void endSession() {
        driver.quit();
    }

    public static void deleteCase(long caseID) {
        cases.deleteCase(caseID);
        report.deleteReport(caseID);
    }

    protected static void capture(long reportID, File screen) {
        report.captureScreen(reportID, screen);
    }
}
