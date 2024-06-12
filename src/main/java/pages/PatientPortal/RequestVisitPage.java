package pages.PatientPortal;

import base.BasePage;
import io.cucumber.java.nl.Stel;
import io.cucumber.plugin.event.StepDefinition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GenericFunctionUtil;

import javax.xml.xpath.XPath;

public class RequestVisitPage extends BasePage {

    @FindBy(id = "patientInformation-RequestAVisitText")
    private WebElement RequestVisitHeader;

    @FindBy(id = "patientInformation-requestVisitMessage-text")
    private WebElement RequestVisitMessage;

    @FindBy(xpath = "//*[@id=\"patientInformation-lastName-input\"]")
    private WebElement LastName;

    @FindBy(xpath = "//*[@id=\"patientInformation-firstName-input\"]")
    private WebElement FirstName;

    @FindBy(xpath = "//*[@id=\"patientInformation-birthDate-input\"]")
    private WebElement DateOfBirth;


    @FindBy(xpath = "//mat-select[@id='patientInformation-genderEnum-select']")
    private WebElement BiologicalSex;

    @FindBy(xpath = "//span[@class='mat-select-placeholder']")
    private WebElement PreferredLanguage;

    @FindBy(xpath = "//*[@id=\"patientInformation-phoneNumber-input\"]")
    private WebElement PrimaryPhone;

    @FindBy(xpath = "//*[@id=\"patientInformation-email-input\"]")
    private WebElement PatientEmailAddress;

    @FindBy(xpath = "//*[@id=\"patientInformation-submitForm-btn\"]")
    private WebElement SubmitBtn;

    @FindBy(className = "sign-in-text")
    private WebElement SigninBtn;

    private String patientEmail;

    public RequestVisitPage(WebDriver driver) {
        super(driver);
    }

    public String getHeadingText(){
        GenericFunctionUtil.waitVisibility(driver, RequestVisitHeader);
        return GenericFunctionUtil.getText(RequestVisitHeader);
    }

    public String getMessageText(){
        GenericFunctionUtil.waitVisibility(driver, RequestVisitMessage);
        return GenericFunctionUtil.getText(RequestVisitMessage);
    }

    public void enterDetails() throws InterruptedException {
        String DOB = GenericFunctionUtil.generateDOB();
        String time = GenericFunctionUtil.generateTime();
        String lastName = "Automate";
        String firstName = DOB + "-" + time;
        GenericFunctionUtil.sendKeys(LastName, lastName, driver);
        GenericFunctionUtil.sendKeys(FirstName, firstName, driver);
        GenericFunctionUtil.sendKeys(DateOfBirth, DOB, driver);
        GenericFunctionUtil.onClick(BiologicalSex, driver);
        String genderXpath = GenericFunctionUtil.formatString("//*[@id='RequestorPatientGender-%s']", "Male");
        WebElement genderValue = driver.findElement(By.xpath(genderXpath));
        GenericFunctionUtil.onClick(genderValue, driver);
        String phoneNumber = "380" + GenericFunctionUtil.generateNumber(7);
        GenericFunctionUtil.sendKeys(PrimaryPhone, phoneNumber, driver);
        patientEmail = lastName + firstName + "@mailinator.com";
        GenericFunctionUtil.sendKeys(PatientEmailAddress, patientEmail, driver );
    }

    public void submitRequestButton() throws InterruptedException {
        GenericFunctionUtil.elementToScrollTo(SigninBtn, driver);
        GenericFunctionUtil.onClick(SubmitBtn, driver);
    }
}
