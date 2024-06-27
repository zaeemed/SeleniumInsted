package pages.PatientPortal;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GenericFunctionUtil;

public class PatientInformationPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"patientInformation-caregiver-lastName-input\"]")
    private WebElement CgLastName;

    @FindBy(xpath = "//*[@id=\"patientInformation-caregiver-firstName-input\"]\n")
    private WebElement CgFirstName;

    @FindBy(xpath = "//mat-select[@id='patientInformation-caregiver-relation-select']")
    private WebElement PatientRelation;

    @FindBy(xpath = "//*[@id=\"patientInformation-caregiver-phoneNumber-input\"]")
    private WebElement CgPrimaryPhone;

    @FindBy(xpath = "(//input[@id='patientInformation-email-input'])[2]")
    private WebElement CaregiverEmailAddress;

    public PatientInformationPage(WebDriver driver) {
        super(driver);
    }

    public void enterCaregiverDetails(String relation) throws InterruptedException {
        String DOB = GenericFunctionUtil.generateDOB();
        String time = GenericFunctionUtil.generateTime();
        String lastName = "Automate";
        String firstName = DOB + "T" + time;

        GenericFunctionUtil.onClick(PatientRelation, driver);
        String relationXpath = GenericFunctionUtil.formatString("//mat-option[.//span[text()='%s']]", relation);
        WebElement relationValue = driver.findElement(By.xpath(relationXpath));
        GenericFunctionUtil.onClick(relationValue, driver);

        GenericFunctionUtil.sendKeys(CgLastName, lastName, driver);
        GenericFunctionUtil.sendKeys(CgFirstName, firstName, driver);
        String phoneNumber = GenericFunctionUtil.generateNANPAPhoneNumber();
        GenericFunctionUtil.sendKeys(CgPrimaryPhone, phoneNumber, driver);
        String caregiverEmail = lastName + firstName + "@mailinator.com";
        GenericFunctionUtil.setConfigValue("caregiverEmail", caregiverEmail);
        GenericFunctionUtil.setConfigValue("caregiverRelation", relation);
        GenericFunctionUtil.sendKeys(CaregiverEmailAddress, caregiverEmail, driver );
    }
}
