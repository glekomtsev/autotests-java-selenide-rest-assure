package demoqa.pages;


import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    public void removeBlocks(){
        try {
            $("#fixedban").shouldBe(visible);
            Selenide.executeJavaScript("document.getElementById('fixedban').style.display = 'none';");
            $("#fixedban").should(hidden);
        } catch (Exception e) {
            System.out.println("Элемент с id 'fixedban' не найден или уже скрыт");
        }


        try {
            $("footer").shouldBe(visible);
            Selenide.executeJavaScript("document.querySelector('footer').style.display = 'none';");
            $("footer").should(hidden);
        } catch (Exception e) {
            System.out.println("Footer не найден или уже скрыт");
        }

        try {
            $("iframe").shouldBe(visible);
            Selenide.executeJavaScript("document.querySelector('iframe').style.display = 'none';");
            $("iframe").should(hidden);
        } catch (Exception e) {
            System.out.println("'iframe' не найден или уже скрыт");
        }



    }
}
