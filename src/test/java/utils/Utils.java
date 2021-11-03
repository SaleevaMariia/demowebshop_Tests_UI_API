package utils;

import com.codeborne.selenide.SelenideElement;
import data.Product;
import dictionaries.Menu;
import pages.ListOfProductsPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.refresh;

public class Utils {

    public static void scrollToElementAndClick(SelenideElement element) {
        int yScrollPosition = element.getLocation().getY();
        executeJavaScript("window.scroll(0, " + yScrollPosition + ");");
        element.click();
    }

    public static double getTotalSum(List<Product> productList) {
        double sum = 0;
        for (Product p : productList) {
            sum += p.getPrice();
        }
        return sum;
    }

    public static void addProductToShoppingCard(Menu menu, ListOfProductsPage listOfProductsPage, List<Product> productList) {
        listOfProductsPage.getMenu().navigateTo(menu);
        productList.add(listOfProductsPage.addRandomProductToCart());
        refresh();
    }
}
