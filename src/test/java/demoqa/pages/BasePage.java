package demoqa.pages;


import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    public void removeBlocks(){
        try {
            $("#fixedban").shouldNotBe(visible);
            Selenide.executeJavaScript("document.getElementById('fixedban').style.display = 'none';");
        } catch (Exception e) {
            System.out.println("Элемент с id 'fixedban' не найден или уже скрыт");
        }

        try {
            $("footer").shouldNotBe(visible);
            Selenide.executeJavaScript("document.querySelector('footer').style.display = 'none';");
        } catch (Exception e) {
            System.out.println("Футер не найден или уже скрыт");
        }



    }
}
