package demoqa.pages;


import com.codeborne.selenide.Selenide;

public class BasePage {
    public void removeBlocks(){
        // Скрытие элемента с id "fixedban"
        try {
            Selenide.executeJavaScript("document.getElementById('fixedban').style.display = 'none';");
        } catch (Exception e) {
            System.out.println("Элемент с id 'fixedban' не найден или уже скрыт");
        }

        // Скрытие футера
        try {
            Selenide.executeJavaScript("document.querySelector('footer').style.display = 'none';");
        } catch (Exception e) {
            System.out.println("Футер не найден или уже скрыт");
        }
    }
}
