package pages;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BaseClass {

    @FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    private WebElement dashboardTitle;

    @FindBy(xpath = "//span[text()='Admin']")
    private WebElement adminMenu;

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenu;

    @FindBy(xpath = "//span[text()='Leave']")
    private WebElement leaveMenu;

    @FindBy(xpath = "//span[text()='Recruitment']")
    private WebElement recruitmentMenu;

    @FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
    private WebElement userDropdown;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutOption;

    public DashboardPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean isDashboardDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(dashboardTitle));
        return dashboardTitle.isDisplayed();
    }

    public String getDashboardTitle() {
        return dashboardTitle.getText();
    }

    public void clickAdminMenu() {
        adminMenu.click();
    }

    public void clickPIMMenu() {
        pimMenu.click();
    }

    public void clickLeaveMenu() {
        leaveMenu.click();
    }

    public void clickRecruitmentMenu() {
        recruitmentMenu.click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(userDropdown));
        userDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutOption));
        logoutOption.click();
    }
}
