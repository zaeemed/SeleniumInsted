package pages.PatientPortal;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GenericFunctionUtil;

public class ThankYouPage extends BasePage {

    @FindBy(xpath = "/html/body/app-root/div/div/app-request-confirmation/div/div[2]/div[4]/span\n")
    private WebElement DownloadAppText;

    @FindBy(xpath = "//*[@id=\"requestInformation-backToHome-btn\"]\n")
    private WebElement BackToHomeBtn;

    @FindBy(xpath = "//*[@id=\"requestConfirmation-dateOfSubmission\"]")
    private WebElement DateOfRequest;

    public ThankYouPage(WebDriver driver) {
        super(driver);
    }

    public String getDownloadAppText(){
        GenericFunctionUtil.waitVisibility(driver, DownloadAppText);
        return GenericFunctionUtil.getText(DownloadAppText);
    }

    public String getDateOfSRequest(){
        GenericFunctionUtil.waitVisibility(driver, DateOfRequest);
        return GenericFunctionUtil.getText(DateOfRequest);
    }
}
