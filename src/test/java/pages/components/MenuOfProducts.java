package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dictionaries.Menu;
import dictionaries.SubMenu;
import io.qameta.allure.Step;
import pages.ListOfProductsPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MenuOfProducts {

    private SelenideElement self = $(".header-menu");
    private ElementsCollection menuElements = self.$$(".top-menu a");
    private ElementsCollection subMenuElements = $$(".sub-category-item a");

    @Step("Navigating to menu item {menu}")
    public ListOfProductsPage navigateTo(Menu menu){
        menuElements.find(Condition.text(menu.getValue())).click();
        switch (menu) {
            case COMPUTERS:
                subMenuElements.find(Condition.text(SubMenu.ACCESSORIES.getValue())).click();
                break;
            case ELECTRONICS:
                subMenuElements.find(Condition.text(SubMenu.CELL_PHONES.getValue())).click();
                break;
        }
        return new ListOfProductsPage();
    }

}
