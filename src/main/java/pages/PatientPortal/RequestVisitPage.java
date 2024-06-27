package pages.PatientPortal;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GenericFunctionUtil;

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
    private WebElement SignInBtn;

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
        String firstName = DOB + "T" + time;
        GenericFunctionUtil.sendKeys(LastName, lastName, driver);
        GenericFunctionUtil.sendKeys(FirstName, firstName, driver);
        GenericFunctionUtil.sendKeys(DateOfBirth, DOB, driver);
        GenericFunctionUtil.onClick(BiologicalSex, driver);
        String genderXpath = GenericFunctionUtil.formatString("//*[@id='RequestorPatientGender-%s']", "Male");
        WebElement genderValue = driver.findElement(By.xpath(genderXpath));
        GenericFunctionUtil.onClick(genderValue, driver);
        String phoneNumber = GenericFunctionUtil.generateNANPAPhoneNumber();
        GenericFunctionUtil.sendKeys(PrimaryPhone, phoneNumber, driver);
        String patientEmail = lastName + firstName + "@mailinator.com";
        GenericFunctionUtil.sendKeys(PatientEmailAddress, patientEmail, driver);
        GenericFunctionUtil.setConfigValue("patientEmail", patientEmail);
        GenericFunctionUtil.setConfigValue("patientFirstName", firstName);
        GenericFunctionUtil.setConfigValue("patientLastName", lastName);
        GenericFunctionUtil.setConfigValue("PatientDOB", DOB);
    }

    public void submitRequestButton() throws InterruptedException {
        GenericFunctionUtil.elementToScrollTo(SignInBtn, driver);
        GenericFunctionUtil.onClick(SubmitBtn, driver);
    }
}
