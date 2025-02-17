package demoqa.pages;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class BasePage {
    public void removeBlocks(){
        try {
            executeJavaScript("document.querySelector('#fixedban').style.display='none';");
        } catch (Exception e) {
            System.out.println("Элемент с id 'fixedban' не найден");
        }

        try {
            executeJavaScript("document.querySelector('footer').style.display='none';");
        } catch (Exception e) {
            System.out.println("Футер не найден");
        }
    }
}
