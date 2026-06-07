package pages;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmployeePage extends BaseClass {

    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement addEmployeeLink;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@placeholder='Middle Name']")
    private WebElement middleNameField;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lastNameField;

    @FindBy(xpath = "//div[@class='orangehrm-employee-container']//input[@class='oxd-input oxd-input--active']")
    private WebElement employeeIdField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement searchEmployeeField;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span']")
    private WebElement recordCount;

    public EmployeePage() {
        PageFactory.initElements(driver, this);
    }

    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeLink));
        addEmployeeLink.click();
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void enterMiddleName(String middleName) {
        middleNameField.clear();
        middleNameField.sendKeys(middleName);
    }

    public void enterLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void clickSave() {
        saveButton.click();
    }

    public void addEmployee(String firstName, String middleName, String lastName) {
        clickAddEmployee();
        enterFirstName(firstName);
        enterMiddleName(middleName);
        enterLastName(lastName);
        clickSave();
    }

    public void searchEmployee(String employeeName) {
        wait.until(ExpectedConditions.visibilityOf(searchEmployeeField));
        searchEmployeeField.clear();
        searchEmployeeField.sendKeys(employeeName);
        searchButton.click();
    }

    public String getRecordCount() {
        wait.until(ExpectedConditions.visibilityOf(recordCount));
        return recordCount.getText();
    }

    public boolean isEmployeeFound(String employeeName) {
        try {
            WebElement employeeRow = driver.findElement(
                    By.xpath("//div[contains(text(),'" + employeeName + "')]")
            );
            return employeeRow.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
