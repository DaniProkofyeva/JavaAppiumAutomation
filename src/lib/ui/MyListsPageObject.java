package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    public static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

    private static String getFolderXpathByName(String folderName)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}",folderName);
    }

    private static String getSavedArticleXpathByTitle(String title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}",title);
    }

    public MyListsPageObject(AppiumDriver driver){
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        this.waitForElementAndClick(By.xpath(getFolderXpathByName(name_of_folder)), "Cannot find folder by name: " + name_of_folder, 5);
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        this.swipeElementToLeft(By.xpath(getSavedArticleXpathByTitle(article_title)), "Cannot find saved article");
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(By.xpath(article_xpath),"Cannot find saved article with title: " + article_title,15);
    }

    public void isArticlePresent(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.assertElementPresent(By.xpath(article_xpath),"Cannot find saved article with title: " + article_title);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(By.xpath(article_xpath),"Saved article still present with title: " + article_title,15);
    }

    public void waitAndClickArticleByTitle(String article_title) {
        this.waitForElementAndClick(By.xpath(getSavedArticleXpathByTitle(article_title)), "Cannot find article by title: " + article_title, 5);
    }
}
