# SauceDemo Automation Framework

This project is an automation testing framework built for the SauceDemo application using:

* Selenium WebDriver
* TestNG
* Page Object Model (POM)
* Extent Reports (with screenshots on failure)
* Log4j2 Logging
* Maven for dependency management
* Java

## Features

| Feature                | Description                                            |
| ---------------------- | ------------------------------------------------------ |
| Page Object Model      | Maintains clean separation of UI locators & test steps |
| TestNG                 | Organizes test execution & reporting                   |
| Extent HTML Report     | Auto generated after every test run                    |
| Screenshots on Failure | Added in Extent report for debug                       |
| Log4j2 Logging         | Console + file logs for each test                      |
| Config Driven          | URL / credentials stored in `config.properties`        |

## Project Structure

```
SauceDemoAssignment
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com.saucedemo.base          # Base setup for driver + reporting
│   │   │   ├── com.saucedemo.pages         # Page classes (Page Factory)
│   │   │   ├── com.saucedemo.utils         # DriverFactory, Config, Reporting, Screenshot utils
│   │   │
│   │   └── resources
│   │       ├── config.properties           # URL + credentials
│   │       └── log4j2.xml                  # Logging configuration
│   │
│   └── test
│       └── java
│           └── com.saucedemo.tests         # TestNG test classes
│
├── reports                                  # Extent test reports + screenshots
├── logs                                     # log4j execution logs
└── pom.xml
```

## Setup Requirements

| Tool    | Version       |
| ------- | ------------- |
| Java    | 17 LTS        |
| Maven   | 3.8+          |
| Browser | Chrome / Edge |

### Verify Java Version:

```
java -version
```

## Install Dependencies

```
mvn clean install
```

## Run Tests

Run all test cases:

```
mvn test
```

Run a specific test class:

```
mvn -Dtest=LoginTests test
```

Run a specific test method:

```
mvn -Dtest=LoginTests#loginSuccessTest test
```

## View Test Report

After running tests, open:

```
reports/ExtentReport.html
```

## Screenshots on Failure

Failed tests automatically capture screenshots and attach to the report.

Screenshots stored at:

```
reports/screenshots/
```

## Default Test Credentials

Located in:

```
src/main/resources/config.properties
```

Example:

```
url=https://www.saucedemo.com/
username=standard_user
password=secret_sauce
```

## Test Scenarios Covered

| Test Case                                 | Status |
| ----------------------------------------- | ------ |
| Login Success                             | ✅      |
| Login Failure (locked_out_user)           | ✅      |
| Add Product to Cart & validate cart count | ✅      |
| Failure Test with Screenshot Capture      | ✅      |

## Author

**Akshay Srivastava**

GitHub: [https://github.com/rnsakshay](https://github.com/rnsakshay)
