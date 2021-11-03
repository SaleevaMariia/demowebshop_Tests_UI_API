package pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dictionaries.Menu;
import dictionaries.SubMenu;
import io.qameta.allure.Step;
import pages.ListOfProductsPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MenuOfProducts {

    private SelenideElement self = $(".header-menu");
    private ElementsCollection menuElements = self.$$(".top-menu a"),
            subMenuElements = $$(".sub-category-item a");

    @Step("Navigating to menu item {menu}")
    public ListOfProductsPage navigateTo(Menu menu) {
        menuElements.find(text(menu.getValue())).click();
        switch (menu) {
            case COMPUTERS:
                subMenuElements.find(text(SubMenu.ACCESSORIES.getValue())).click();
                break;
            case ELECTRONICS:
                subMenuElements.find(text(SubMenu.CELL_PHONES.getValue())).click();
                break;
        }
        return new ListOfProductsPage();
    }
}
