# Test automation framework for mobile applications

The framework allows testing native, web, and hybrid apps on real mobile devices or emulators.
Included demo test connects to the Android emulator, launches the Gmail app, adds a Google account to it, logs in, and finally deletes the account.    

## Stack

- Java 17.  
- Appium java-driver.
- JUnit-jupiter.
- Log4j.
- Gradle.

## Features

- [GmailTest](src/test/java/gmail/GmailTest.java) - test itself.
- [Page Objects](src/test/java/pom) - supplying UI elements locators and page-related methods.   
- [DriverManager](src/test/java/driver/DriverManager.java) driver manager has options to choose driver, start and shoot down driver, Appium server, emulator.  
- [Helper classes](src/test/java/helpers) contains helper methods that do not belong to poms.
- [Test properties](src/test/resources/test.properties) contains test data such as Appium server address and driver capabilities, credentials for the Google account.
