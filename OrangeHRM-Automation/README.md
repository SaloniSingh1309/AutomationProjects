# OrangeHRM Automation Framework

A Selenium WebDriver automation framework for testing OrangeHRM application using Java, TestNG, and Page Object Model design pattern.

## 🛠️ Tech Stack
- Java 11
- Selenium WebDriver 4.15
- TestNG 7.8
- Maven
- WebDriverManager
- Extent Reports 5
- Apache POI (Excel Data Driven)
- Page Object Model (POM)

## 📁 Project Structure

```
OrangeHRM-Automation/
├── src/
│   ├── main/java/
│   │   ├── base/
│   │   │   └── BaseClass.java          # Driver setup & teardown
│   │   ├── pages/
│   │   │   ├── LoginPage.java          # Login page elements & actions
│   │   │   ├── DashboardPage.java      # Dashboard page elements & actions
│   │   │   ├── EmployeePage.java       # PIM module elements & actions
│   │   │   └── AdminPage.java          # Admin module elements & actions
│   │   └── utils/
│   │       ├── ConfigReader.java       # Read config.properties
│   │       ├── ExcelUtil.java          # Excel data reader
│   │       ├── ExtentReportManager.java# Report generation
│   │       └── ScreenshotUtil.java     # Screenshot capture
│   └── test/
│       ├── java/tests/
│       │   ├── LoginTest.java          # Login test cases
│       │   ├── AdminTest.java          # Admin module test cases
│       │   └── EmployeeTest.java       # PIM module test cases
│       └── resources/
│           └── config.properties       # Configuration file
├── reports/                            # Extent Reports HTML output
├── screenshots/                        # Failure screenshots
├── testng.xml                          # TestNG suite configuration
└── pom.xml                             # Maven dependencies
```

## ⚙️ Configuration

Update `src/test/resources/config.properties`:

```properties
url=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
browser=chrome
username=Admin
password=admin123
implicitWait=10
explicitWait=20
```

## 🚀 How to Run

### Run all tests via Maven:
```bash
mvn clean test
```

### Run specific test class:
```bash
mvn clean test -Dtest=LoginTest
```

### Run via TestNG XML:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

## 📊 Test Cases Covered

### Login Module
- ✅ Valid login with correct credentials
- ✅ Invalid login with wrong credentials
- ✅ Login with empty username
- ✅ Login with empty password
- ✅ Logout functionality

### Admin Module
- ✅ Admin page is displayed
- ✅ Search user functionality

### PIM (Employee) Module
- ✅ Add new employee
- ✅ Search employee

## 📈 Reports

After execution, find the HTML report at:
```
reports/ExtentReport.html
```

## 🌐 Application Under Test
- URL: https://opensource-demo.orangehrmlive.com
- Username: Admin
- Password: admin123
