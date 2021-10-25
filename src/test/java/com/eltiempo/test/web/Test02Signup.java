package com.eltiempo.test.web;

import com.eltiempo.bl.utils.states.CaseExecutionState;
import com.eltiempo.test.config.Config;
import com.eltiempo.test.config.Consts;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Test02Signup extends Config {

    @BeforeAll
    public static void setUp() {
        createCase(3, "Registro Fallido", """
                """, true);
        createReport(3,3);
        config();
    }

    @Test
    public void clickRegister() {
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
    public void fillForm() {

    }

    @AfterAll
    public static void postRun() {
        endSession();
    }
}
