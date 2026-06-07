package pages;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminPage extends BaseClass {

    @FindBy(xpath = "//a[text()='Add']")
    private WebElement addUserButton;

    @FindBy(xpath = "//label[text()='User Role']/following::div[@class='oxd-select-text-input'][1]")
    private WebElement userRoleDropdown;

    @FindBy(xpath = "//label[text()='Status']/following::div[@class='oxd-select-text-input'][1]")
    private WebElement statusDropdown;

    @FindBy(xpath = "//label[text()='Employee Name']/following::input[@placeholder='Type for hints...']")
    private WebElement employeeNameField;

    @FindBy(xpath = "//label[text()='Username']/following::input[@class='oxd-input oxd-input--active'][1]")
    private WebElement usernameField;

    @FindBy(xpath = "//label[text()='Password']/following::input[@type='password'][1]")
    private WebElement passwordField;

    @FindBy(xpath = "//label[text()='Confirm Password']/following::input[@type='password'][1]")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement searchUsernameField;

    public AdminPage() {
        PageFactory.initElements(driver, this);
    }

    public void clickAddUser() {
        wait.until(ExpectedConditions.elementToBeClickable(addUserButton));
        addUserButton.click();
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(password);
    }

    public void enterEmployeeName(String name) {
        employeeNameField.clear();
        employeeNameField.sendKeys(name);
    }

    public void clickSave() {
        saveButton.click();
    }

    public void searchUser(String username) {
        wait.until(ExpectedConditions.visibilityOf(searchUsernameField));
        searchUsernameField.clear();
        searchUsernameField.sendKeys(username);
        searchButton.click();
    }
}
