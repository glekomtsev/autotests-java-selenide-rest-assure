package demoqa.tests;

import org.junit.jupiter.api.Test;
import demoqa.pages.PracticeFormPage;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class PracticeFormTest extends BaseTest {

    private final String firstName = "Oleg";
    private final String lastName = "Popov";
    private final String email = "uzze@ya.ri";
    private final String gender = "Other";
    private final String userNumber = "1234567890";
    private final String month = "September";
    private final String year = "1998";
    private final String day = "01";
    private final String subject = "Arts";
    private final String hobby1 = "Sports";
    private final String hobby2 = "Reading";
    private final String hobby3 = "Music";
    private final String picturePath = "tiger.jpg";
    private final String currentAddress = "Russia Moscow 1";
    private final String state = "Haryana";
    private final String city = "Karnal";

    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @Test
    public void positivePracticeFormTest() {


        step("Открываем страницу с формой", () -> {
            open("/automation-practice-form");
            practiceFormPage.removeBlocks();
        });

        step("Заполняем форму", () -> {
            practiceFormPage
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(email)
                    .selectGender(gender)
                    .setUserNumber(userNumber)
                    .setDateOfBirth(day, month, year)
                    .setSubject(subject)
                    .selectHobbies(hobby1, hobby2, hobby3)
                    .uploadPicture(picturePath)
                    .setCurrentAddress(currentAddress)
                    .selectStateAndCity(state, city);
        });

        step("Отправляем форму", practiceFormPage::submitForm);

        step("Проверяем результаты", () -> {
            practiceFormPage
                    .verifyModalAppears()
                    .verifyFormData(
                            firstName, lastName,
                            email,
                            gender,
                            userNumber,
                            day + " " + month + "," + year,
                            subject,
                            hobby1 + ", " + hobby2 + ", " + hobby3,
                            picturePath,
                            currentAddress,
                            state + " " + city
                    );
        });
    }
}