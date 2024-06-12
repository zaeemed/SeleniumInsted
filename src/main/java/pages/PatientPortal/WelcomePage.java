package pages.PatientPortal;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GenericFunctionUtil;

import java.util.Objects;

public class WelcomePage extends BasePage {

    @FindBy(id = "I am a Patient-chip")
    private WebElement patientField;

    @FindBy(id = "I take care of a Patient-chip")
    private WebElement caregiverField;

    @FindBy(id = "newPatientForm-insurance")
    private WebElement insuranceField;

    public WelcomePage(WebDriver driver){
        super(driver);
    }

    public void selectRequesterPill(String requester) throws InterruptedException {
        if(Objects.equals(requester, "I am a Patient"))
            GenericFunctionUtil.onClick(patientField, driver);
        else if (Objects.equals(requester, "I take care of a Patient")) {
            GenericFunctionUtil.onClick(caregiverField, driver);
        }
    }

    public void enterValue(String value) throws InterruptedException {

        GenericFunctionUtil.sendKeys(insuranceField, value, driver);
        GenericFunctionUtil.clickEnter(insuranceField);

    }

}
