package gmail;

import helpers.ContextSwitcher;
import org.junit.jupiter.api.Test;
import pom.*;

class GmailTest extends BaseTest {

    @Test
    void canAddAccountToGmailLogInAndDeleteIt() {
        var android12HomeScreen = new AndroidHomeAndAppsScreen();
        var androidSettings = new AndroidSettings();
        var gmailPage = new GmailNativeApp();
        var switchContext = new ContextSwitcher();
        var gmailInbox = new GmailInbox();

        android12HomeScreen.openGmail();
        gmailPage.skipSuggestionsIfPresent();
        gmailPage.selectGoogleEmail();
        switchContext.toWebViewContext();
        new GmailWebViewAuth().enterCredentials();
        switchContext.toNativeContext();
        gmailPage.acceptSettings();
        gmailPage.removeRecentAppFromMemory(); // had to reload Gmail to get to inbox
        android12HomeScreen.openGmail();
        gmailInbox.searchInMail("Can't believe I made it!");
        android12HomeScreen.openSettings();
        androidSettings.deleteRecentlyAddedAccount();
    }

    @Test
    void removeAcc() {
        var android12HomeScreen = new AndroidHomeAndAppsScreen();
        var androidSettings = new AndroidSettings();

        android12HomeScreen.openSettings();
        androidSettings.deleteRecentlyAddedAccount();
    }

    @Test
    void swipe() {
        var gmailPage = new GmailNativeApp();

        gmailPage.removeRecentAppFromMemory(); // had to reload Gmail to get to inbox
    }

      @Test
    void searchInbox() {
        var android12HomeScreen = new AndroidHomeAndAppsScreen();
        var gmailInbox = new GmailInbox();

        android12HomeScreen.openGmail();
        gmailInbox.searchInMail("What do you get if you multiply six by nine?");
    }
}
