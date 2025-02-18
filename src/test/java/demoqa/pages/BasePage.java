package demoqa.pages;


import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    public void removeBlocks(){
        try {
            $("#fixedban").shouldBe(visible).shouldHave(cssValue("display", "none"));
        } catch (Exception e) {
            System.out.println("Элемент с id 'fixedban' не найден");
        }

        try {
            $("footer").shouldBe(visible).shouldHave(cssValue("display", "none"));
        } catch (Exception e) {
            System.out.println("Футер не найден");
        }
    }
}
