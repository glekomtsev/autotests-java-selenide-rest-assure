package demoqa.pages;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class BasePage {
    public void removeBlocks() {
        try {
            $("#fixedban").shouldBe(visible);
            Selenide.executeJavaScript("document.getElementById('fixedban').style.setProperty('display', 'none', 'important');");
            $("#fixedban").should(hidden);
        } catch (Exception e) {
            System.out.println("Элемент с id 'fixedban' не найден или уже скрыт");
        }


        try {
            $("footer").shouldBe(visible);
            Selenide.executeJavaScript("document.querySelector('footer').style.setProperty('display', 'none', 'important');");
            $("footer").should(hidden);
        } catch (Exception e) {
            System.out.println("Footer не найден или уже скрыт");
        }

        try {
            Selenide.executeJavaScript("document.querySelector('iframe').style.setProperty('display', 'none', 'important');");
        } catch (Exception e) {
            System.out.println("Iframe не найден или уже скрыт: " + e.getMessage());
        } finally {
            switchTo().defaultContent();
        }

    }
}