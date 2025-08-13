package demoqa.tests;

import com.github.javafaker.Faker;
import demoqa.pages.TextBoxPage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class TextBoxTest extends BaseTest {
    Faker faker = new Faker();

    private final String userName = faker.name().username();
    private final String userEmail = faker.internet().emailAddress();
    private final String userCurrentAddress = faker.address().fullAddress();
    private final String userPermanentAddress = faker.address().fullAddress();

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
