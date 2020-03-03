import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import static com.codeborne.selenide.Selenide.open;

import com.github.javafaker.Faker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Locale;

public class CardDeliveryServiceTest {

    @BeforeAll
    public static void setUp() {
        System.setProperty("chromeoptions.args", "--no-sandbox,--headless,--disable-dev-shm-usage");
    }

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        Faker faker = new Faker(new Locale("ru"));

        String name = faker.name().fullName();
        String adress = faker.address().cityName();
        String phone = faker.phoneNumber().phoneNumber();


        $$("[type='text']").first().setValue(adress);
        $("[data-test-id=name] input").setValue(name);
        $("[name='phone']").setValue(phone);
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
        $(withText("Запланировать")).click();
        $(withText("Перепланировать")).shouldBe(visible).click();
        $(withText("Успешно!")).waitUntil(visible, 5000);
    }
}