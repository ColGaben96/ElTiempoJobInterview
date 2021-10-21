package com.eltiempo.test.web;

import com.eltiempo.bl.utils.states.CaseExecutionState;
import com.eltiempo.test.config.Config;
import com.eltiempo.test.config.Consts;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Test01LoginFailure extends Config {

    @BeforeAll
    public static void setUp() {
        createCase(1,
                "Login no satisfactorio",
                """
                        El login no debe dejar ingresar sin las credenciales correctas. Comprobar a toda costa que este
                        requerimiento se cumpla.""",
                true);
        createReport(1,1);
        config();
    }

    @Test
    void makeLogin() {
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
            } catch (Exception e) {
                updateReport(1, CaseExecutionState.FAILED_BY_VALIDATION, getSeconds());
            }
        } catch (NoSuchElementException e) {
            updateReport(1, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
        }
    }

    @Test
    void spamLogin() {
        for (int i = 0; i < 10; i++) {
            makeLogin();
        }
        var user = driver.findElement(By.name("user"));
        user.clear();
        var password = driver.findElement(By.name("password"));
        password.clear();
    }

    @AfterAll
    public static void postRun() {
        endSession();
        updateReport(1, CaseExecutionState.SUCCESSFUL, getSeconds());
    }
}
