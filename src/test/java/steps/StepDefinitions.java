package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.PatientPortal.*;
import org.junit.Assert;
import utils.GenericFunctionUtil;

public class StepDefinitions {

    private final WebDriver driver = Hooks.getDriver();
    private WelcomePage welcomePage;
    private AddressPage addressPage;
    private RequestVisitPage requestVisitPage;
    private ThankYouPage thankYouPage;
    private PatientInformationPage patientInformationPage;


    @Given("User is on welcome screen")
    public void welcomeScreen() {
        driver.get("https://insted-patient-qa.vicenna.com/welcome");
        welcomePage = new WelcomePage(driver);
    }

    @When("User clicks on {string} pill")
    public void userClicks(String flowName) throws InterruptedException {
        welcomePage.selectRequesterPill(flowName);
    }

    @And("User enters insurance {string}")
    public void userTextInput(String insurance) throws InterruptedException {
        welcomePage.enterValue(insurance);
    }

    @Then("User is on address screen")
    public void addressScreen() {
        addressPage = new AddressPage(driver);
        String heading = addressPage.getHeadingText();
        Assert.assertEquals("User is not on Address Page, Assertion failed", "Patient Address", heading);
    }

    @And("User enters address {string}")
    public void userEntersAddress(String address) throws InterruptedException {
        addressPage.enterAddress(address);
    }

    @Then("User is on {string} screen")
    public void requestAVisitScreen(String screenName) {
        requestVisitPage = new RequestVisitPage(driver);
        String heading = requestVisitPage.getHeadingText();
        Assert.assertEquals(String.format("User is not on %s Page, Assertion failed", screenName), screenName, heading);
        String message = requestVisitPage.getMessageText();
        String text = "Great! You are in our service area. You can request a visit and we will get back to you during hours of operation from 8 AM to 10 PM.";
        Assert.assertEquals("Message is different, Assertion failed", text, message);
    }

    @And("User enters {string} details")
    public void userEntersPatientDetails(String persona) throws InterruptedException {
        requestVisitPage.enterDetails();
        if(persona.contains("caregiver")){
            patientInformationPage = new PatientInformationPage(driver);
            patientInformationPage.enterCaregiverDetails();
        }
    }

    @And("User clicks on Submit Button")
    public void userClicksOnSubmitButton() throws InterruptedException {
        requestVisitPage.submitRequestButton();
    }

    @Then("User is on Thank you screen")
    public void thankYouScreen() {
        thankYouPage = new ThankYouPage(driver);
        String currentDate = GenericFunctionUtil.currentDate();
        String dateOfSRequest = thankYouPage.getDateOfSRequest();
        Assert.assertEquals(String.format("Date of Request is not same as %s , Assertion failed", currentDate), currentDate, dateOfSRequest);
    }

}