package com.eltiempo.test.web;

import com.eltiempo.bl.utils.states.CaseExecutionState;
import com.eltiempo.test.config.Config;
import com.eltiempo.test.config.Consts;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Test01Login extends Config {

    @BeforeAll
    @Given("I am on login")
    public static void setUp() {
        createCase(1,
                "Login no satisfactorio",
                """
                        El login no debe dejar ingresar sin las credenciales correctas. Comprobar a toda costa que este
                        requerimiento se cumpla.""",
                true);
        createCase(2,
                "Login satisfactorio",
                """
                        El login debe dejar ingresar con las credenciales correctas. Comprobar a toda costa que este
                        requerimiento se cumpla.""", false);
        createReport(1,1);
        createReport(2,2);
        config();
    }

    @Test
    @Then("User inputs bad user and password credentials and Error Message should display")
    public void makeBadLogin() {
        try {
            var user = driver.findElement(By.name("user"));
            user.sendKeys("badUser");
            var password = driver.findElement(By.name("password"));
            password.sendKeys("badPassword");
            var login = driver.findElement(By.xpath("/html/body/center/div/form/table/tbody/tr[3]/td[2]/input"));
            login.click();
            var errorMessage = driver.findElement(By.id("errorMessage"));
            try {
                Assertions.assertEquals(errorMessage.getText(), Consts.INVALID_ERROR);
                capture(1, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
            } catch (AssertionError e) {
                capture(1, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
                updateReport(1, CaseExecutionState.FAILED_BY_VALIDATION, getSeconds());
                return;
            }
        } catch (NoSuchElementException e) {
            updateReport(1, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            return;
        }
        updateReport(1, CaseExecutionState.SUCCESSFUL, getSeconds());
    }

    @Test
    @Then("User inputs correct user and passwords credentials and Dashboard should load")
    public void makeGoodLogin() {
        try {
            var user = driver.findElement(By.name("user"));
            user.sendKeys(Consts.USERNAME);
            var password = driver.findElement(By.name("password"));
            password.sendKeys(Consts.PASSWORD);
            var login = driver.findElement(By.xpath("/html/body/center/div/form/table/tbody/tr[3]/td[2]/input"));
            login.click();
            try {
                Assertions.assertEquals(driver.getCurrentUrl(), Consts.DASHBOARD_PAGE);
                capture(2, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
            } catch (AssertionError e) {
                capture(2, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
                updateReport(2, CaseExecutionState.FAILED_BY_VALIDATION, getSeconds());
                return;
            }
        } catch (NoSuchElementException e) {
            updateReport(2, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            return;
        }
        updateReport(2, CaseExecutionState.SUCCESSFUL, getSeconds());
    }

    @AfterAll
    @And("Session should be done")
    public static void postRun() {
        endSession();
    }
}
