package demoqa.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class BasePage {
    public void removeBlocks(){
        try {
            $(By.cssSelector("#fixedban")).should(exist);
            executeJavaScript("document.querySelector('#fixedban').style.display='none';");
        } catch (Exception e) {
            System.out.println("Элемент с id 'fixedban' не найден");
        }

        try {
            $(By.tagName("footer")).should(exist);
            executeJavaScript("document.querySelector('footer').style.display='none';");
        } catch (Exception e) {
            System.out.println("Футер не найден");
        }
    }
}
