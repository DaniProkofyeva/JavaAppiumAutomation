package tests;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);

    }

    @Test
    public void testSaveTwoArticlesDeleteOne()
    {
        String name_of_folder = "Learning programming";
        String first_search = "Java";
        String first_search_substring = "Object-oriented programming language";
        String second_search = "JavaScript";
        String second_search_substring = "Programming language";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_search);
        SearchPageObject.clickByArticleWithSubstring(first_search_substring);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        String first_article_title = ArticlePageObject.getArticleTitle();

        ArticlePageObject.addArticleToMyList(name_of_folder);

        SearchPageObject.clickSearchIcon();

        SearchPageObject.typeSearchLine(second_search);
        SearchPageObject.clickByArticleWithSubstring(second_search_substring);
        ArticlePageObject.waitForTitleElement();

        String second_article_title = ArticlePageObject.getArticleTitle();

        ArticlePageObject.addArticleToExistingList(name_of_folder);

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);

        MyListsPageObject.openFolderByName(name_of_folder);

        MyListsPageObject.swipeByArticleToDelete(first_article_title);

        MyListsPageObject.waitForArticleToDisappearByTitle(first_article_title);

        MyListsPageObject.isArticlePresent(second_article_title);

        MyListsPageObject.waitAndClickArticleByTitle(second_article_title);

        assertEquals("Article title not equal saved exam", second_article_title, ArticlePageObject.getArticleTitle());



    }

}
