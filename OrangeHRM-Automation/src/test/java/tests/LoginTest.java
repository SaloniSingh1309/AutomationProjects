package tests;

import base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

public class LoginTest extends BaseClass {

    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testValidLogin() {
        ExtentTest extentTest = ExtentReportManager.createTest("Valid Login Test");

        try {
            loginPage = new LoginPage();
            dashboardPage = new DashboardPage();

            String username = ConfigReader.getProperty("username");
            String password = ConfigReader.getProperty("password");

            extentTest.log(Status.INFO, "Navigating to OrangeHRM login page");
            loginPage.login(username, password);

            extentTest.log(Status.INFO, "Verifying dashboard is displayed");
            Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard is not displayed after login");

            extentTest.log(Status.PASS, "Valid login test PASSED");

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtil.captureScreenshot("testValidLogin");
            extentTest.addScreenCaptureFromPath(screenshotPath);
            extentTest.log(Status.FAIL, "Test FAILED: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 2, description = "Verify login fails with invalid credentials")
    public void testInvalidLogin() {
        ExtentTest extentTest = ExtentReportManager.createTest("Invalid Login Test");

        try {
            loginPage = new LoginPage();

            extentTest.log(Status.INFO, "Entering invalid credentials");
            loginPage.login("invalidUser", "invalidPassword");

            String errorMsg = loginPage.getErrorMessage();
            extentTest.log(Status.INFO, "Error message received: " + errorMsg);

            Assert.assertEquals(errorMsg, "Invalid credentials", "Error message mismatch");
            extentTest.log(Status.PASS, "Invalid login test PASSED");

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtil.captureScreenshot("testInvalidLogin");
            extentTest.addScreenCaptureFromPath(screenshotPath);
            extentTest.log(Status.FAIL, "Test FAILED: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 3, description = "Verify login fails with empty username")
    public void testEmptyUsername() {
        ExtentTest extentTest = ExtentReportManager.createTest("Empty Username Login Test");

        try {
            loginPage = new LoginPage();

            extentTest.log(Status.INFO, "Entering empty username");
            loginPage.login("", "admin123");

            String errorMsg = loginPage.getErrorMessage();
            Assert.assertTrue(errorMsg.contains("Required"), "Validation message not displayed");

            extentTest.log(Status.PASS, "Empty username test PASSED");

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtil.captureScreenshot("testEmptyUsername");
            extentTest.addScreenCaptureFromPath(screenshotPath);
            extentTest.log(Status.FAIL, "Test FAILED: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 4, description = "Verify login fails with empty password")
    public void testEmptyPassword() {
        ExtentTest extentTest = ExtentReportManager.createTest("Empty Password Login Test");

        try {
            loginPage = new LoginPage();

            extentTest.log(Status.INFO, "Entering empty password");
            loginPage.login("Admin", "");

            String errorMsg = loginPage.getErrorMessage();
            Assert.assertTrue(errorMsg.contains("Required"), "Validation message not displayed");

            extentTest.log(Status.PASS, "Empty password test PASSED");

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtil.captureScreenshot("testEmptyPassword");
            extentTest.addScreenCaptureFromPath(screenshotPath);
            extentTest.log(Status.FAIL, "Test FAILED: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 5, description = "Verify user can logout successfully")
    public void testLogout() {
        ExtentTest extentTest = ExtentReportManager.createTest("Logout Test");

        try {
            loginPage = new LoginPage();
            dashboardPage = new DashboardPage();

            extentTest.log(Status.INFO, "Logging in with valid credentials");
            loginPage.login(
                    ConfigReader.getProperty("username"),
                    ConfigReader.getProperty("password")
            );

            Assert.assertTrue(dashboardPage.isDashboardDisplayed());

            extentTest.log(Status.INFO, "Clicking logout");
            dashboardPage.logout();

            Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page not displayed after logout");
            extentTest.log(Status.PASS, "Logout test PASSED");

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtil.captureScreenshot("testLogout");
            extentTest.addScreenCaptureFromPath(screenshotPath);
            extentTest.log(Status.FAIL, "Test FAILED: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }
}
