
import com.github.javafaker.Faker;
import java.util.Locale;

public class RegistrationCard {

    Faker faker = new Faker(new Locale("ru"));

    String name = faker.name().fullName();
    String adress = faker.address().cityName();
    String phone = faker.phoneNumber().phoneNumber();
}
