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
---


## Technologies Used
 - Java 17: Core programming language
 - Maven: Build automation and dependency management
 - Selenium: Browser automation
 - TestNG: Test execution framework
 - SLF4J & Logback: Logging framework
 - Allure: Test reporting
---
## Installation and execution

1. Clone the repository:
```
git clone https://github.com/katerynaburla/sauce-demo.git
cd sauce-demo
```
2. Build the project:
```
mvn clean install
```
3. tests execution
```
mvn clean test
```
4. allure report generate and view 
```
mvn io.qameta.allure:allure-maven:2.10.0:serve
```
---
## License
Author: Kateryna Burla

