package com.eltiempo.test.web;

import com.eltiempo.bl.utils.states.CaseExecutionState;
import com.eltiempo.test.config.Config;
import com.eltiempo.test.config.Consts;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Test03Dashboard extends Config {

    @BeforeAll
    @Given("User is on Dashboard")
    public static void setUp() {
        createCase(5, "Compra de todos los artículos", """
                El usuario debe poder comprar todos los artículos y la suma en el gran total debe coincidir con el 
                precio de los artículos""",
                false);
        createCase(6, "Compra de todos los artículos + 1", """
                El usuario no debe poder comprar una cantidad mayor a la del inventario.""",
                true);
        createCase(7, "Compra de todos los libros de Java", """
                El usuario debe poder comprar todos los libros de una categoría""",
                false);
        createCase(8, "Compra de todos los libros de Ruby of Rails", """
                El usuario debe poder comprar todos los libros de una categoría""",
                false);
        createCase(9, "Compra de todos los libros de Python Cookbook", """
                El usuario debe poder comprar todos los libros de una categoría""",
                false);
        createCase(10, "Compra de un libro de Java", """
               El usuario debe poder comprar un libro de una categoría""",
                false);
        createCase(11, "Compra de un libro de Ruby of Rails", """
                El usuario debe poder comprar un libro de una categoría""",
                false);
        createCase(12, "Compra de un libro de Python Cookbook", """
                El usuario debe poder comprar un libro de una categoría""",
                false);
        config();
        login();
    }

    @Test
    public static void login() {
        try {
            driver.findElement(By.name("user")).sendKeys(Consts.USERNAME);
            driver.findElement(By.name("password")).sendKeys(Consts.PASSWORD);
            driver.findElement(By.xpath("/html/body/center/div/form/table/tbody/tr[3]/td[2]/input")).click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Then("Buy all the stock")
    public void buyAll() {
        createReport(5,5);
        try {
            driver.findElement(By.xpath("//*[@id=\"listing\"]/tbody/tr[2]/td[4]/input")).sendKeys("5");
            driver.findElement(By.xpath("//*[@id=\"listing\"]/tbody/tr[3]/td[4]/input")).sendKeys("12");
            driver.findElement(By.xpath("//*[@id=\"listing\"]/tbody/tr[4]/td[4]/input")).sendKeys("7");
        } catch (NoSuchElementException e) {
            updateReport(5, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
        }
    }

    @Test
    @Then("Buy one more unit than the Stock")
    public void buyMoreThanAll() {
        createReport(6,6);
        try {
            driver.findElement(By.xpath("//*[@id=\"listing\"]/tbody/tr[2]/td[4]/input")).sendKeys("6");
            driver.findElement(By.xpath("//*[@id=\"listing\"]/tbody/tr[3]/td[4]/input")).sendKeys("13");
            driver.findElement(By.xpath("//*[@id=\"listing\"]/tbody/tr[4]/td[4]/input")).sendKeys("8");
        } catch (NoSuchElementException e) {
            updateReport(6, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
        }
    }

    @Test
    @Then("Buy all the stock of Java Books")
    public void buyAllJava() {
        createReport(7,7);
        try {
            driver.findElement(By.xpath("//*[@id=\"listing\"]/tbody/tr[2]/td[4]/input")).sendKeys("5");
        } catch (NoSuchElementException e) {
            updateReport(7, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
        }
    }

    @Test
    @Then("Buy one of stock of Java Books")
    public void buy1Java() {
        createReport(10,10);
        try {
            driver.findElement(By.xpath("//*[@id=\"listing\"]/tbody/tr[2]/td[4]/input")).sendKeys("1");
        } catch (NoSuchElementException e) {
            updateReport(10, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
        }
    }

    @Test
    @Then("Buy all the stock of Ruby for Rails Book")
    public void buyAllRor() {
        createReport(8,8);
        try {
            driver.findElement(By.xpath("//*[@id=\"listing\"]/tbody/tr[3]/td[4]/input")).sendKeys("12");
        } catch (NoSuchElementException e) {
            updateReport(8, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
        }
    }

    @Test
    @Then("Buy one of stock of Ruby for Rails Book")
    public void buy1Ror() {
        createReport(11,11);
        try {
            driver.findElement(By.xpath("//*[@id=\"listing\"]/tbody/tr[3]/td[4]/input")).sendKeys("1");
        } catch (NoSuchElementException e) {
            updateReport(11, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
        }
    }

    @Test
    @Then("Buy all the stock of Python Cookbook")
    public void buyAllPyCook() {
        createReport(9,9);
        try {
            driver.findElement(By.xpath("//*[@id=\"listing\"]/tbody/tr[4]/td[4]/input")).sendKeys("7");
        } catch (NoSuchElementException e) {
            updateReport(9, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
        }
    }

    @Test
    @Then("Buy one of stock of Python Cookbook")
    public void buy1PyCook() {
        createReport(12,12);
        try {
            driver.findElement(By.xpath("//*[@id=\"listing\"]/tbody/tr[4]/td[4]/input")).sendKeys("1");
        } catch (NoSuchElementException e) {
            updateReport(12, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
        }
    }

    @Test
    @But("When clicking the Add button, the grand total must coincide with the books value")
    public void addAllStock() {
        try {
            driver.findElement(By.xpath("//*[@id=\"available\"]/input[1]")).click();
            var total = driver.findElement(By.id("total")).getText();
            try {
                Assertions.assertEquals(total, String.valueOf(6350));
                capture(5, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
            } catch (AssertionError err) {
                capture(5, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
                updateReport(5, CaseExecutionState.FAILED_BY_VALIDATION, getSeconds());
                postRun();
            }
        } catch (NoSuchElementException e) {
            updateReport(5, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
        }
        updateReport(5, CaseExecutionState.SUCCESSFUL, getSeconds());
    }

    @Test
    @But("When clicking the Add button, an error should display due to there is no more units available")
    public void addMoreThanAllStock() {
        try {
            driver.findElement(By.xpath("//*[@id=\"available\"]/input[1]")).click();
            var total = driver.findElement(By.id("total")).getText();
            try {
                Assertions.assertEquals(total, "error");
                capture(6, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
            } catch (AssertionError err) {
                capture(6, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
                updateReport(6, CaseExecutionState.FAILED_BY_VALIDATION, getSeconds());
                postRun();
            }
        } catch (NoSuchElementException e) {
            updateReport(6, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
        }
        updateReport(6, CaseExecutionState.SUCCESSFUL, getSeconds());
    }

    @Test
    @But("When clicking the Add button, the grand total must coincide with the Java books value")
    public void addJavaBooks() {
        try {
            driver.findElement(By.xpath("//*[@id=\"available\"]/input[1]")).click();
            var total = driver.findElement(By.id("total")).getText();
            try {
                Assertions.assertEquals(total, String.valueOf(1500));
                capture(7, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
            } catch (AssertionError err) {
                capture(7, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
                updateReport(7, CaseExecutionState.FAILED_BY_VALIDATION, getSeconds());
                postRun();
            }
        } catch (NoSuchElementException e) {
            updateReport(7, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
        }
    }

    @Test
    @But("When clicking the Add button, the grand total must coincide with the Ruby for Rails books value")
    public void addRubyBooks() {
        try {
            driver.findElement(By.xpath("//*[@id=\"available\"]/input[1]")).click();
            var total = driver.findElement(By.id("total")).getText();
            try {
                Assertions.assertEquals(total, String.valueOf(2400));
                capture(8, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
            } catch (AssertionError err) {
                capture(8, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
                updateReport(8, CaseExecutionState.FAILED_BY_VALIDATION, getSeconds());
                postRun();
            }
        } catch (NoSuchElementException e) {
            updateReport(8, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
        }
    }

    @Test
    @But("When clicking the Add button, the grand total must coincide with Python the books value")
    public void addPythonBooks() {
        try {
            driver.findElement(By.xpath("//*[@id=\"available\"]/input[1]")).click();
            var total = driver.findElement(By.id("total")).getText();
            try {
                Assertions.assertEquals(total, String.valueOf(2450));
                capture(9, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
            } catch (AssertionError err) {
                capture(9, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
                updateReport(9, CaseExecutionState.FAILED_BY_VALIDATION, getSeconds());
                postRun();
            }
        } catch (NoSuchElementException e) {
            updateReport(9, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
        }
    }

    @Test
    @But("When clicking the Add button, the grand total must coincide with the Java Book value")
    public void addJavaBook() {
        try {
            driver.findElement(By.xpath("//*[@id=\"available\"]/input[1]")).click();
            var total = driver.findElement(By.id("total")).getText();
            try {
                Assertions.assertEquals(total, String.valueOf(300));
                capture(10, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
            } catch (AssertionError err) {
                capture(10, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
                updateReport(10, CaseExecutionState.FAILED_BY_VALIDATION, getSeconds());
                postRun();
            }
        } catch (NoSuchElementException e) {
            updateReport(10, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
        }
    }

    @Test
    @But("When clicking the Add button, the grand total must coincide with the Roby for Rails book value")
    public void addRubyBook() {
        try {
            driver.findElement(By.xpath("//*[@id=\"available\"]/input[1]")).click();
            var total = driver.findElement(By.id("total")).getText();
            try {
                Assertions.assertEquals(total, String.valueOf(200));
                capture(11, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
            } catch (AssertionError err) {
                capture(11, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
                updateReport(11, CaseExecutionState.FAILED_BY_VALIDATION, getSeconds());
                postRun();
            }
        } catch (NoSuchElementException e) {
            updateReport(11, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
        }
    }

    @Test
    @But("When clicking the Add button, the grand total must coincide with the Python book value")
    public void addPythonBook() {
        try {
            driver.findElement(By.xpath("//*[@id=\"available\"]/input[1]")).click();
            var total = driver.findElement(By.id("total")).getText();
            try {
                Assertions.assertEquals(total, String.valueOf(350));
                capture(12, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
            } catch (AssertionError err) {
                capture(12, ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
                updateReport(12, CaseExecutionState.FAILED_BY_VALIDATION, getSeconds());
                postRun();
            }
        } catch (NoSuchElementException e) {
            updateReport(12, CaseExecutionState.FAILED_BY_EXECUTION, getSeconds());
            postRun();
        }
    }



    @AfterAll
    public static void postRun() {
        deleteCase(5);
        deleteCase(6);
        deleteCase(7);
        deleteCase(8);
        deleteCase(9);
        deleteCase(10);
        deleteCase(11);
        deleteCase(12);
        endSession();
    }

}
