# Autotests for http://demowebshop.tricentis.com/

## Covered features:

- [x] API + UI users registration
- [x] API + UI users login
- [x] API + UI add products to shopping cart
- [x] UI check out shopping cart

## Technology Stack:

![java](./img/icons/Java.svg "Java")
![gradle](./img/icons/Gradle.svg "Gradle")
![jUnit5](./img/icons/JUnit5.svg "JUnit5")
![selenide](./img/icons/Selenide.svg "Selenide")
![rest-assured](./img/icons/Rest-Assured.svg "Rest-Assured")
![jenkins](./img/icons/Jenkins.svg "Jenkins")
![Selenoid](./img/icons/Selenoid.svg "Selenoid")
![allure-logo](./img/icons/AllureReport.svg "Allure_Report")
![telegram-logo](./img/icons/Telegram.svg "Telegram")
![jira](./img/icons/Jira.svg "Jira")
![testOps](./img/icons/TestOps.svg "TestOps")

Java, Gradle, JUnit5, Selenide, Rest-Assured, Jenkins, Selenoid, Allure Reports, Telegram (уведомления), Jira, TestOps

## Description

You can run tests by configuring the following parameters:

- BROWSER
- BROWSER_VERSION
- BROWSER_SIZE
- REMOTE_DRIVER_URL
- THREADS
  ![Parametrised build](./img/jenkins-demo.png)

### To run tests locally with files app.properties and local.properties:

```
gradle clean test 
```

## Allure reports

### Overview

![overview allure](./img/allure-demo.png)

### Test with steps, attached image, console logs

![selenoid_screen](./img/steps-demo.png)

### Video

![video](./img/demo.gif)

## Allure TestOps

## Launches

![launches](./img/launches-demo.png)

## Test Cases

![test-case](./img/test-case-demo.png)

## Jira integration

![jira](./img/jira-demo.png)

## Telegram report

![telegram](./img/telegram-demo.png)


