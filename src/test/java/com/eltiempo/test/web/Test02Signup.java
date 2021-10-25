package com.eltiempo.test.web;

import com.eltiempo.bl.utils.states.CaseExecutionState;
import com.eltiempo.test.config.Config;
import com.eltiempo.test.config.Consts;
import io.cucumber.core.backend.CucumberBackendException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.opentest4j.AssertionFailedError;

public class Test02Signup extends Config {

    @BeforeAll
    @Given("I am on signup")
    public static void setUp() {
        createCase(3, "Registro Fallido", """
                El usuario deberá llenar el formulario, pero no debe aceptar los términos y condiciones.""",
                true);
        createCase(4, "Registro Exitoso", """
                EL usuario deberá llenar el formulario, aceptando los términos y condiciones""",
                false);
        config();
        clickRegister();
    }

    @Test
    public static void clickRegister() {
        createReport(3,3);
        try {
            var register = driver.findElement(By.xpath("/html/body/center/div/a"));
            register.click();
            try {
                Assertions.assertEquals(driver.getCurrentUrl(), Consts.REGISTER_PAGE);
                capture(3, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
            } catch (AssertionError as) {
                capture(3, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
                updateReport(3, CaseExecutionState.FAILED_BY_VALIDATION, getSeconds());
                return;
            }
        } catch (NoSuchElementException e) {
            updateReport(3, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            return;
        }
        updateReport(3, CaseExecutionState.SUCCESSFUL, getSeconds());
    }

    @Test
    @Then("User will fill the form but won't check the tos agreement.")
    public void fillFormButNoCheck() {
        try {
            driver.findElement(By.id("uid")).sendKeys(Consts.REG_USERNAME);
            driver.findElement(By.id("pid")).sendKeys(Consts.REG_PASSWORD);
            driver.findElement(By.id("pid2")).sendKeys(Consts.REG_PASSWORD);
            driver.findElement(By.xpath("/html/body/center/div/form/div/table/tbody/tr[4]/td[2]/input[1]")).click();
            driver.findElement(By.id("taid")).sendKeys(Consts.REG_ADDRESS);
            driver.findElement(By.id("btaid")).sendKeys(Consts.REG_BILLING_ADDRESS);
        } catch (NoSuchElementException e) {
            updateReport(3, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            return;
        }
        updateReport(3, CaseExecutionState.SUCCESSFUL, getSeconds());
    }

    @Test
    @Then("User will fill the form but will check the tos agreement.")
    public void fillFormButCheck() {
        createReport(4, 4);
        try {
            driver.findElement(By.id("uid")).sendKeys(Consts.REG_USERNAME);
            driver.findElement(By.id("pid")).sendKeys(Consts.REG_PASSWORD);
            driver.findElement(By.id("pid2")).sendKeys(Consts.REG_PASSWORD);
            driver.findElement(By.xpath("/html/body/center/div/form/div/table/tbody/tr[4]/td[2]/input[1]")).click();
            driver.findElement(By.id("taid")).sendKeys(Consts.REG_ADDRESS);
            driver.findElement(By.id("btaid")).sendKeys(Consts.REG_BILLING_ADDRESS);
            driver.findElement(By.name("agree")).click();
        } catch (NoSuchElementException e) {
            updateReport(4, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            return;
        }
        updateReport(4, CaseExecutionState.SUCCESSFUL, getSeconds());
    }

    @Test
    @And("Should pass to the next step or show an alert that the process was successful.")
    public void registerChecked() {
        try {
            driver.findElement(By.xpath("/html/body/center/div/form/input[2]")).click();
            var alert = driver.switchTo().alert();
            try {
                Assertions.assertEquals(alert.getText(), Consts.REG_ACCEPTED_TOS);
                capture(4,((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
            } catch (AssertionError | UnhandledAlertException err) {
                capture(4,((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
                updateReport(4, CaseExecutionState.FAILED_BY_VALIDATION, getSeconds());
                postRun();
                return;
            }
        } catch (NoSuchElementException e) {
            updateReport(4, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
            return;
        }
        updateReport(4, CaseExecutionState.SUCCESSFUL, getSeconds());
        postRun();
    }

    @Test
    @And("Should display an alert where the user hasn't accepted the tos before.")
    public void registerUnchecked() {
        try {
            driver.findElement(By.xpath("/html/body/center/div/form/input[2]")).click();
            var alert = driver.switchTo().alert();
            try {
                Assertions.assertEquals(alert.getText(), Consts.REG_NOACCEPTED_TOS);
                capture(3,((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
            } catch (AssertionFailedError | UnhandledAlertException | CucumberBackendException err) {
                updateReport(3, CaseExecutionState.FAILED_BY_VALIDATION, getSeconds());
                capture(3,((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
                postRun();
                return;
            }
        } catch (NoSuchElementException e) {
            updateReport(3, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
            return;
        }
        updateReport(3, CaseExecutionState.SUCCESSFUL, getSeconds());
        postRun();
    }

    @AfterAll
    public static void postRun() {
        deleteCase(3);
        deleteCase(4);
        endSession();
    }
}
