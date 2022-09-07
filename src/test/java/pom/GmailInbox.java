package pom;

import com.google.common.collect.ImmutableMap;
import driver.DriverManager;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class GmailInbox extends NativeBaseApp {

    @AndroidFindBy(id = "com.google.android.gm:id/open_search_bar_text_view")
    WebElement searchInMail;
    @AndroidFindBy(id = "com.google.android.gm:id/open_search_view_edit_text")
    WebElement searchInput;

    @AndroidFindBy(id = "com.google.android.gm:id/empty_icon")
    WebElement emptySearchIcon;

    public void searchInMail(String stringToSearch) {
        searchInMail.click();
        searchInput.sendKeys(stringToSearch);
        DriverManager.getDriver().executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
        emptySearchIcon.click(); // just to confirm "No matches for search string"
    }
}
