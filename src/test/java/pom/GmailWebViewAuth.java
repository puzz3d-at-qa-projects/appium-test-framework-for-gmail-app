package pom;

import conf.ConfigReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.Waiters.waitToBeClickable;

public class GmailWebViewAuth extends NativeBaseApp {

    @FindBy(css = "input#identifierId")
    WebElement emailField;
    @FindBy(xpath = "//span[text()='Next']")
    WebElement emailNextButton;
    @FindBy(name = "password")
    WebElement passwordField;
    @FindBy(id = "passwordNext")
    WebElement passwordNextButton;
    @FindBy(id = "signinconsentNext")
    WebElement welcomeAgreeButton;
    @FindBy(id = "com.google.android.gm:id/avatar")
    WebElement avatar;

    public void enterCredentials() {
        enterEmail();
        enterPassword();
        welcomeAgree();
        LOGGER.debug("Credentials entered");
    }

    private void enterEmail() {
        waitToBeClickable(emailField, 15).sendKeys(ConfigReader.get().gmailAccEmail());
        emailNextButton.click();
    }

    private void enterPassword() {
        waitToBeClickable(passwordField, 15).sendKeys(ConfigReader.get().gmailAccPassword());
        passwordNextButton.click();
    }

    private void welcomeAgree() {
        welcomeAgreeButton.click();
    }
}
