package pages.PatientPortal;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GenericFunctionUtil;

public class AddressPage extends BasePage {

    @FindBy(className = "ng-star-inserted")
    private WebElement loader;

    @FindBy(id = "addressInformation-patientAddress-text")
    private WebElement AddressHeading;

    @FindBy(id = "googlePlaces-address-input")
    private WebElement AddressField;

    @FindBy(id = "warningDialog-continueText")
    private WebElement YesButton;

    public AddressPage(WebDriver driver){
        super(driver);
    }

    public String getHeadingText(){
        GenericFunctionUtil.waitVisibility(driver, AddressHeading);
        return GenericFunctionUtil.getText(AddressHeading);
    }

    public void enterAddress(String address) throws InterruptedException {
        GenericFunctionUtil.sendKeys(AddressField, address, driver);
        GenericFunctionUtil.clickEnter(AddressField);
    }
}
