package tests;

import base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

public class AdminTest extends BaseClass {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    AdminPage adminPage;

    @BeforeMethod
    public void loginBeforeTest() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        adminPage = new AdminPage();

        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );
        dashboardPage.clickAdminMenu();
    }

    @Test(priority = 1, description = "Verify Admin page is displayed")
    public void testAdminPageDisplayed() {
        ExtentTest extentTest = ExtentReportManager.createTest("Admin Page Test");

        try {
            extentTest.log(Status.INFO, "Verifying Admin page is displayed");
            Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Admin page not displayed");

            extentTest.log(Status.PASS, "Admin Page test PASSED");

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtil.captureScreenshot("testAdminPageDisplayed");
            extentTest.addScreenCaptureFromPath(screenshotPath);
            extentTest.log(Status.FAIL, "Test FAILED: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 2, description = "Verify searching a user in Admin")
    public void testSearchUser() {
        ExtentTest extentTest = ExtentReportManager.createTest("Search Admin User Test");

        try {
            extentTest.log(Status.INFO, "Searching for Admin user");
            adminPage.searchUser("Admin");

            extentTest.log(Status.PASS, "Search User test PASSED");

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtil.captureScreenshot("testSearchUser");
            extentTest.addScreenCaptureFromPath(screenshotPath);
            extentTest.log(Status.FAIL, "Test FAILED: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }
}
