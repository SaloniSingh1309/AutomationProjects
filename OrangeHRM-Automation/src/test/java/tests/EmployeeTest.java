package tests;

import base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EmployeePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

public class EmployeeTest extends BaseClass {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    EmployeePage employeePage;

    @BeforeMethod
    public void loginBeforeTest() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        employeePage = new EmployeePage();

        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );
        dashboardPage.clickPIMMenu();
    }

    @Test(priority = 1, description = "Verify adding a new employee")
    public void testAddEmployee() {
        ExtentTest extentTest = ExtentReportManager.createTest("Add Employee Test");

        try {
            extentTest.log(Status.INFO, "Clicking Add Employee");
            employeePage.clickAddEmployee();

            extentTest.log(Status.INFO, "Entering employee details");
            employeePage.enterFirstName("John");
            employeePage.enterMiddleName("Michael");
            employeePage.enterLastName("Doe");

            extentTest.log(Status.INFO, "Saving employee");
            employeePage.clickSave();

            extentTest.log(Status.PASS, "Add Employee test PASSED");

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtil.captureScreenshot("testAddEmployee");
            extentTest.addScreenCaptureFromPath(screenshotPath);
            extentTest.log(Status.FAIL, "Test FAILED: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 2, description = "Verify searching for an employee")
    public void testSearchEmployee() {
        ExtentTest extentTest = ExtentReportManager.createTest("Search Employee Test");

        try {
            extentTest.log(Status.INFO, "Searching for employee");
            employeePage.searchEmployee("John");

            String recordCount = employeePage.getRecordCount();
            extentTest.log(Status.INFO, "Record count: " + recordCount);

            Assert.assertNotNull(recordCount, "No records found");
            extentTest.log(Status.PASS, "Search Employee test PASSED");

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtil.captureScreenshot("testSearchEmployee");
            extentTest.addScreenCaptureFromPath(screenshotPath);
            extentTest.log(Status.FAIL, "Test FAILED: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }
}
