package hotel;

import common.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.hotel.AuthenticationPage;
import pages.hotel.RegistrationInfoPage;
import pages.hotel.sections.HeaderSection;

public class RegistrationTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = Utils.setUpDriver();
        driver.get("https://hotel-testlab.coderslab.pl/en/");
    }

    @Test
    public void registerNewUser() {
        // arrange
        String email = Utils.generateEmail();
        String firstName = "Jan";
        String lastName = "Kowalski";
        String password = "qwerty";

        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        RegistrationInfoPage registrationInfoPage = new RegistrationInfoPage(driver);
        HeaderSection headerSection = new HeaderSection(driver);

        // act
        headerSection.goToAuthentication();
        authenticationPage.registerAs(email);
        registrationInfoPage.fillUserRegistrationData(firstName, lastName, password);

        // assert
        Assertions.assertEquals("Your account has been created.", registrationInfoPage.getRegistrationSuccessMessage());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
