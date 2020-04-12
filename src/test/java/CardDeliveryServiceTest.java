import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryServiceTest {

    @BeforeAll
    public static void setUp() {
        System.setProperty("chromeoptions.args", "--no-sandbox,--headless,--disable-dev-shm-usage");
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    public static void tearDownAll () {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");

        RegistrationCard registrationCard = new RegistrationCard();

        $$("[type='text']").first().setValue(registrationCard.adress);
        $("[data-test-id=name] input").setValue(registrationCard.name);
        $("[name='phone']").setValue(registrationCard.phone);
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
        $(withText("Запланировать")).click();
        $(withText("Перепланировать")).shouldBe(visible).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }
}