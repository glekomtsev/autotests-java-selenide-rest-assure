package demoqa.tests;

import demoqa.pages.TextBoxPage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class TextBoxTest extends BaseTest {

    private final String userName = "Uzzle";
    private final String userEmail = "Uzzle@ya.ru";
    private final String userCurrentAddress = "Address 1";
    private final String userPermanentAddress = "Address 2";

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    public void positiveTextBoxTest() {


        step("Открываем страницу с формой", () -> {
            open("/text-box");
            textBoxPage.removeBlocks();
        });

        step("Заполняем форму", () -> {
            textBoxPage
                    .setUserName(userName)
                    .setUserEmail(userEmail)
                    .setCurrentAddress(userCurrentAddress)
                    .setPermanentAddress(userPermanentAddress);
        });

        step("Отправляем форму", textBoxPage::submitForm);

        step("Проверяем результаты", () -> {
            textBoxPage.verifyFormData(
                    userName,
                    userEmail,
                    userCurrentAddress,
                    userPermanentAddress
            );
        });
    }
}
