package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage {
        //This is the main class and just call functions
        private final WebDriver driver;
        private final By searchDropdownBox = By.id("nav-search-dropdown-card");
        private final By computers = By.id("searchDropdownBox");
        private final By twotabsearchtextbox = By.id("twotabsearchtextbox");
        private final By searchBtn = By.id("nav-search-submit-button");
        private final By onlyLaptopItems = By.xpath("//*[@id='departments']/ul/li[8]");
        private final By checkBoxPrime = By.xpath("//li[@id = 'p_85/21345931031']/span");
        private final By allItems = By.className("s-product-image-container");
        private final By primeItems = By.xpath("//span[contains(@class,'aok-relative')]");

        public MainPage(WebDriver driver){
            this.driver = driver;
        }

        public String selectCategories() {
                //selecting categories
                driver.findElement(searchDropdownBox).click();

                WebElement comp = driver.findElement(computers);
                Select select = new Select(comp); //selecting specific category
                select.selectByVisibleText("Bilgisayarlar");
                return select.getFirstSelectedOption().getText();
        }
        public String searchBox() {
                // search whatever you want
                driver.findElement(twotabsearchtextbox).sendKeys("lenovo ideapad gaming 3");
                driver.findElement(searchBtn).click();

                String testDropdownList = driver.findElement(twotabsearchtextbox).getAttribute("value");

                driver.findElement(onlyLaptopItems).click();
                driver.findElement(checkBoxPrime).click();

                return testDropdownList;
        }
        public List<WebElement> verifyAllItemsArePrime1() {
                waitFor(allItems);
                List<WebElement> allItemsLocator = driver.findElements(allItems);
                System.out.println("All items number are: " + allItemsLocator.size());
                return allItemsLocator;
        }
        public List<WebElement> verifyAllItemsArePrime2() {
                waitFor(primeItems);
                List<WebElement>  primeItemsLocator = driver.findElements(primeItems);
                System.out.println("Prime items number are: " + primeItemsLocator.size());
                return primeItemsLocator;
        }
        public void waitFor(By Locator){
                WebDriverWait wait = new WebDriverWait(driver,5);
                wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
        }

        public AddItemToChart addPrimeItemsToChart() {
                List<WebElement> firstItem = driver.findElements(allItems);
                firstItem.get(0).click();
                return new AddItemToChart(driver);
        }


}