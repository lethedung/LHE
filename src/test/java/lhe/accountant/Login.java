package lhe.accountant;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.accountant.AccountantPageObject;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

import static commons.GlobalConstants.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Login extends BaseTest {
    private String account, password, accountInvalid, passwordInvalid;
    private String browserName;
    private AccountantPageObject accountantPage;

    @BeforeClass
    public void beforeClass() {
        browserName = "chrome";
        accountantPage = new AccountantPageObject(ACCOUNTANT_LOGIN);
        account = ACCOUNTANT_ACCOUNT;
        accountInvalid = "hoangtest";
        password = ACCOUNTANT_PASSWORD;
        passwordInvalid = "hoangtest@11";
    }

    @Test
    public void TC_01_Login_Empty_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login Empty Data");
        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Click Account Textbox");
        accountantPage.clickToAccountTextbox();

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Click Password Textbox");
        accountantPage.clickToPasswordTextbox();

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Click Account Textbox again");
        accountantPage.clickToAccountTextbox();

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: Verify Error Message At Account Textbox");
        assertEquals(accountantPage.getErrorMessageAtAccountTextbox(), "Bạn cần điền vào mục này");

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 05: Verify Error Message At Password Textbox");
        assertEquals(accountantPage.getErrorMessageAtPasswordTextbox(), "Bạn cần điền vào mục này");
    }

    @Test
    public void TC_02_Login_Invalid() {
        accountantPage.inputToAccountTextbox(accountInvalid);
        accountantPage.inputToPasswordTextbox(passwordInvalid);
        accountantPage.clickToEyeIcon();
        accountantPage.clickToLoginButton();
        accountantPage.getErrorTextModal();
        assertEquals(accountantPage.getErrorTextModal(), "Sai tên hoặc sai mật khẩu");

        accountantPage.closeErrorPopup();
    }

    @Test
    public void TC_03_Login_Valid() {
        accountantPage.openLoginPage();
        accountantPage.inputToAccountTextbox(account);
        accountantPage.inputToPasswordTextbox(password);
        accountantPage.clickToLoginButton();
        assertTrue(accountantPage.dashboardTextIsDisplayed());
    }
}
