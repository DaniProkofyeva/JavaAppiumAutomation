package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
    TITLE = "org.wikipedia:id/view_page_title_text",
    FOOTER_ELEMENT = "//*[@text='View page in browser']",
    OPTION_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
    ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
    MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "//*[@text='OK']",
    CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
    EXISTING_LIST_TPL = "//*[@resource-id='org.wikipedia:id/item_title'][@text='{FOLDER}']";


    //----------TEMPLATE METHODS------------
    private static String getFolderElement(String folder)
    {
        return EXISTING_LIST_TPL.replace("{FOLDER}", folder);
    }
    //----------TEMPLATE METHODS------------

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(
                By.id(TITLE),
                "Cannot find article on page",
                15
        );
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTION_BUTTON),
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list",
                5
        );
        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find 'Got it' tip overlay",
                5
        );
        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input to set name of article folder",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press OK button",
                15
        );
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find X link",
                5
        );
    }

    public void assertArticleHasTitle() {
        this.assertElementPresent(By.xpath(TITLE),"Article has no title");
    }

    public void addArticleToExistingList(String name_of_folder)
    {
        this.waitForElementAndClick(By.xpath(OPTION_BUTTON), "Cannot find options", 5);

        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON), "Cannot find option to add article to reading list", 7);

        this.waitForElementAndClick(By.xpath(getFolderElement(name_of_folder)),"Cannot find existing folder to save article",5);

    }

}
