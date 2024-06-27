package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class GenericFunctionUtil {
    private static final Random random = new Random();
    static int timeout = Integer.parseInt(getConfigValue("timeout"));

    public static void waitInvisibility(WebDriver driver) throws InterruptedException {
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.pollingEvery(Duration.ofMillis(1000));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ngx-spinner-overlay")));
    }

    public static void waitClickable(WebDriver driver, WebElement element) throws InterruptedException {
        Thread.sleep(7000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.pollingEvery(Duration.ofMillis(1000));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static void waitVisibility(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.pollingEvery(Duration.ofMillis(1000));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static void onClick(WebElement element, WebDriver driver) throws InterruptedException {
        waitInvisibility(driver);
        waitVisibility(driver, element);
        waitClickable(driver, element);
        element.click();
    }
    public static void sendKeys(WebElement element, String text, WebDriver driver) throws InterruptedException {
        waitInvisibility(driver);
        element.sendKeys(text);
    }
    public static void clickEnter(WebElement element) throws InterruptedException {
        Thread.sleep(3000);
        element.sendKeys(Keys.DOWN);
        Thread.sleep(3000);
        element.sendKeys(Keys.ENTER);
    }

    public static String getText(WebElement element){
        return element.getText();
    }

    public static String generateName(int length){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }

    public static String generateDOB(){
        Random random = new Random();
        int startYear = 1950;
        int endYear = 2000;

        int day = random.nextInt(28) + 1; // To ensure a valid day
        int month = random.nextInt(12) + 1; // Month between 1-12
        int year = startYear + random.nextInt(endYear - startYear + 1);

        LocalDate randomDate = LocalDate.of(year, month, day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        return randomDate.format(formatter);
    }

    public static String currentDate(){
        LocalDate currentDate = LocalDate.now();

        // Define a date format for mm/dd/YYYY
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return currentDate.format(formatter);
    }

    public static String generateTime(){
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        return currentTime.format(formatter);
    }

    public static String formatString(String value1, String value2){
        return String.format(value1, value2);
    }

    public static void elementToScrollTo(WebElement element, WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        waitVisibility(driver, element);
    }

    public static String generateNumber(int length){
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than zero.");
        }

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // Generate digits
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // Generates a random digit between 0 and 9
            sb.append(digit);
        }

        return sb.toString();
    }

    public static String generateNANPAPhoneNumber(){
        int areaCode = generateValidCode(true);
        int exchangeCode = generateValidCode(false);
        int subscriberNumber = generateSubscriberNumber();

        return String.format("(%03d) %03d-%04d", areaCode, exchangeCode, subscriberNumber);
    }

    private static int generateValidCode(boolean isAreaCode) {
        int firstDigit, secondDigit, thirdDigit;

        while (true) {
            firstDigit = random.nextInt(8) + 2; // 2 to 9
            secondDigit = random.nextInt(9); // 0 to 8
            thirdDigit = random.nextInt(10); // 0 to 9

            if (isAreaCode) {
                if ((secondDigit == 7 && firstDigit == 3) ||
                        (secondDigit == 6 && firstDigit == 9) ||
                        (secondDigit == thirdDigit)) continue;
            } else {
                if (secondDigit == 1 && thirdDigit == 1) continue;
            }

            break;
        }

        return firstDigit * 100 + secondDigit * 10 + thirdDigit;
    }

    private static int generateSubscriberNumber() {
        return random.nextInt(9000) + 1000; // 1000 to 9999
    }

    public static String getConfigValue(String key){
        ConfigLoader config = ConfigLoader.getInstance();
        return config.getProperty(key);
    }

    public static void setConfigValue(String key, String value){
        ConfigWriter.writeConfig(key, value);
    }
}
