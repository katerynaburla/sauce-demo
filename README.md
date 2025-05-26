# Sauce Demo. Test Automation Project

This is a test example of a UI automation framework. 
The project is built using Selenium, TestNG, and Allure for comprehensive test reporting.

---
## Prerequisites
- Java 17
- Maven
- Chrome browser
- Allure command-line tool
---
## Project Structure

```
project-root/
├── src/
│   ├── main/java/        # Framework code
│   │   ├── components/   # Page Object classes
│   │   ├── config        # Configuration classes 
│   │   ├── pages/        # Page Object classes
│   │   ├── utils/        # Utility classes
│   │   └── enums/        # All the enums 
│   ├── test/java/        # TestNG test cases
│   └── test/resources/   # Configuration files
└── target/
└── allure-results/   # Test execution results
```


### Key Features:

- Modular design
- Reusable components
- Parameterized tests for flexible execution
- Well-organized structure for easier maintenance
---
### Page Object Models (POMs) implementation
 - A page object represents a part of web application  
 - Captures element selectors in one place
 - Create reusable code to avoid repetition

### WebDriver Implementation
- Singleton pattern for WebDriver management
- Thread-safe implementation
- Custom wait strategies

### Reporting implementation
   - Detailed test execution reports
   - Test result visualization
   - Step-by-step execution details
   - Failure analysis
   - Attachments

### Continuous Integration
 - GitHub Actions CI/CD integration
 - Automated test execution
 - Nightly test execution
 - Report generation
---

## Technologies Used
- Java 17: Core programming language
- Maven: Build automation and dependency management
- Selenium: Browser automation
- TestNG: Test execution framework
- SLF4J & Logback: Logging framework
- Allure: Test reporting
- GitHub Actions: CI/CD tool
---

## Installation and execution

### 1. Clone the repository:
```
git clone https://github.com/katerynaburla/sauce-demo.git
cd sauce-demo
```
### 2. Build the project:
```
mvn clean install
```

### 3. Tests execution:
 - to run all tests: 
```
mvn test
``` 
 - to run a specific test (e.g., LoginTests.java): 
```
mvn test -Dtest=LoginTests
 ```
 - to run tests in a few threads (e.g., 2): 
```
mvn clean test -Dthread-count=2
```

### 4.allure report generate and view 
```
mvn allure::serve
```
---
## License
Author: Kateryna Burla

