package lhe.npp;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.npp.NppHomePageObject;
import pageObjects.npp.NppLoginPageObject;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

import static commons.GlobalConstants.NPP_LOGIN;
import static org.testng.Assert.assertEquals;

public class Login extends BaseTest {

    private String account, password, accountInvalid, passwordInvalid;
    private String browserName;

    private NppLoginPageObject loginPage;
    private NppHomePageObject homePage;

    @BeforeClass
    public void beforeClass() {
        browserName = "chrome";
        loginPage = new NppLoginPageObject(NPP_LOGIN);
        homePage = new NppHomePageObject();
        account = "npp.lhe";
        accountInvalid = "hoangtest";
        password = "lhe@1234";
        passwordInvalid = "hoangtest@11";
    }

    @Test
    public void TC_01_Login_Empty_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login Empty Data");
        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Click Account Textbox");
        loginPage.clickToAccountTextbox();

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Click Password Textbox");
        loginPage.clickToPasswordTextbox();

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Click Account Textbox again");
        loginPage.clickToAccountTextbox();

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: Verify Error Message At Account Textbox");
        assertEquals(loginPage.getErrorMessageAtAccountTextbox(), "Bạn cần điền vào mục này");

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 05: Verify Error Message At Password Textbox");
        assertEquals(loginPage.getErrorMessageAtPassworkTextbox(), "Bạn cần điền vào mục này");
    }

    @Test
    public void TC_02_Login_Invalid(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login Invalid Data");
        ExtentTestManager.getTest().log(Status.INFO, "Login Invalid - Step 01: Input Invalid Account to Account Textbox");
        loginPage.inputToAccountTextbox(accountInvalid);
        ExtentTestManager.getTest().log(Status.INFO, "Login Invalid - Step 02: Input Invalid Password to Account Textbox");
        loginPage.inputToPasswordTextbox(passwordInvalid);
        ExtentTestManager.getTest().log(Status.INFO, "Login Invalid - Step 03: Click Hide/Show Password");
        loginPage.clickToEyeIcon();
        ExtentTestManager.getTest().log(Status.INFO, "Login Invalid - Step 04: Click Login");
        loginPage.clickToLoginButton();
        loginPage.getErrorTextModal();
        assertEquals(loginPage.getErrorTextModal(), "Sai tên hoặc sai mật khẩu");
        ExtentTestManager.getTest().log(Status.INFO, "Login Invalid - Step 05: Close Error Popup");
        loginPage.closeErrorPopup();
    }

    @Test
    public void TC_03_Login_Valid(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login Invalid Data");
        loginPage.openLoginPage();
        ExtentTestManager.getTest().log(Status.INFO, "Login Valid - Step 01: Input Valid Account to Account Textbox");
        loginPage.inputToAccountTextbox(account);
        ExtentTestManager.getTest().log(Status.INFO, "Login Valid - Step 02: Input Invalid Password to Password Textbox");
        loginPage.inputToPasswordTextbox(password);
        ExtentTestManager.getTest().log(Status.INFO, "Login Valid - Step 03: Click Login");
        loginPage.clickToLoginButton();
    }
}
