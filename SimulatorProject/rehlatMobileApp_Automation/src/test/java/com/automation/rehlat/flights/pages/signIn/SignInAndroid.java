package com.automation.rehlat.flights.pages.signIn;

import com.automation.rehlat.flights.Labels_Flights;
import com.automation.rehlat.flights.libCommon.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInAndroid extends SignInBase{

    public static final String TOUCH_ID_ACCESS_MODAL_TITLE = "Would you like to login using your TouchID?";
    public static final String NO_BUTTON_ON_TOUCH_ID_ACCESS_MODAL = "No";
    public static final String LOGIN_BUTTON = "com.app.rehlat:id/login_with_rehlat";
    public static final String CREATE_ACCOUNT_BUTTON = "com.app.rehlat:id/signup";
    public static final String FORGET_PASSWORD_BUTTON = "com.app.rehlat:id/forgot_pwd_textview";
    public static final String EMAIL_TEXT_FIELD_OF_SIGN_IN_SCREEN = "com.app.rehlat:id/signinEmailEditText";
    public static final String PASSWORD_TEXT_FIELD_OF_SIGN_IN_SCREEN = "com.app.rehlat:id/signinPwdEditText";
    public static final String CREDENTIALS_PICKER_TITLE = "com.google.android.gms:id/credentials_picker_title";
    public static final String NONE_OF_THE_ABOVE_BUTTON_IN_CREDENTIALS_PICKER_VIEW = "com.google.android.gms:id/cancel";




    /**
     * Check sign in screen is displayed
     */
    @Override
    public void checkSignInScreenIsDisplayed() {
        Logger.logAction("Checking the sign in screen is displayed or not ?");
        try {
            runAppInBackground(1);
            if (!Labels_Flights.ANDROID_CAPABILITIES_DEVICE_TYPE.equalsIgnoreCase("emulator")){
                closeThePickerTitleIfDisplayed();
            }
            runAppInBackground(2);
            if (isElementDisplayedById(LOGIN_BUTTON) && isElementDisplayedById(CREATE_ACCOUNT_BUTTON) && isElementDisplayedById(FORGET_PASSWORD_BUTTON)){
                Logger.logStep("Sign In screen is displayed");
            }else {
                Logger.logError("Sign In screen is not displayed");
            }
        }catch (Exception exception){
            Logger.logError("Encountered error: Unable to check the current active screen name");
        }
    }

    /**
     * Close the email picker view
     */
    public static void closeThePickerTitleIfDisplayed() {
        Logger.logAction("Closing the picker title");
        try{
            Thread.sleep(1000);
            runAppInBackground(3);//Todo:- Discuss with developer for final solution
            if (isElementDisplayedById(CREDENTIALS_PICKER_TITLE)){
                driver.findElementById(NONE_OF_THE_ABOVE_BUTTON_IN_CREDENTIALS_PICKER_VIEW).click();
            }else {
                Logger.logComment("Credentials picker view is not displayed in the current active screen");
            }
        }catch (Exception Exception){
            Logger.logError("Encountered error: Unable to close the picker title");
        }
    }

    /**
     * Decline the touch id access set up Popup if displayed
     */
    @Override
    public void declineTheTouchIdAccessSetUpPopupIfDisplayed() {
        Logger.logAction("Declining the touch id access popup if if displayed");
        try {
            Logger.logStep("For Android platforms, touch id access set up is not implemented");
//            if (isElementDisplayedById(TOUCH_ID_ACCESS_MODAL_TITLE)){
//                Logger.logStep("Touch id access popup is displayed and going to decline it by tapping on no button");
//                driver.findElement(By.id(NO_BUTTON_ON_TOUCH_ID_ACCESS_MODAL)).click();
//            }else {
//                Logger.logComment("Touch id access alert is not displayed");
//            }
        }catch (Exception exception){
            Logger.logError("Encountered error: Unable to check the current active screen name");
        }
    }

    /**
     * Tap on create account button
     */
    @Override
    public void tapOnCreateAccountButton() {
        Logger.logAction("Taping on create account button");
        try {
            if (isElementDisplayedById(CREATE_ACCOUNT_BUTTON)){
                driver.findElement(By.id(CREATE_ACCOUNT_BUTTON)).click();
                Logger.logStep("Create account button is tapped");
            }else {
                Logger.logComment(CREATE_ACCOUNT_BUTTON+" - element name is not displayed in the current active screen");
            }
        }catch (Exception exception){
            Logger.logError("Encountered error: Unable to tap on button name :- "+CREATE_ACCOUNT_BUTTON);
        }
    }

    /**
     * Entering login credentials
     */
    @Override
    public void enterLoginCredentials() {
        Logger.logAction("Entering login credentials");
        try {
            enterEmailId();
            enterPassword();
        }catch (Exception exception){
            Logger.logError("Encountered error: Unable to enter the login credentials");
        }
    }

    /**
     * Entering login credentials
     */
    public static void enterEmailId() {
        Logger.logStep("Entering email id :- "+ Labels_Flights.EMAIL_ID_SIGN_IN);
        try {
            runAppInBackground(1);
            if (isElementDisplayedById(EMAIL_TEXT_FIELD_OF_SIGN_IN_SCREEN)){
                driver.findElementById(EMAIL_TEXT_FIELD_OF_SIGN_IN_SCREEN).sendKeys(Labels_Flights.EMAIL_ID_SIGN_IN);
                Logger.logComment(Labels_Flights.EMAIL_ID_SIGN_IN+":- email id is parsed");
                closeTheKeyboard_Android();
            }else {
                Logger.logError("unable to find the element name :- "+EMAIL_TEXT_FIELD_OF_SIGN_IN_SCREEN);
            }
        }catch (Exception exception){
            Logger.logError("Encountered error: Unable to enter the login credentials");
        }
    }

    /**
     * Entering login credentials
     */
    public static void enterPassword() {
        Logger.logStep("Entering password :- "+ Labels_Flights.PASSWORD);
        try {
            if (isElementDisplayedById(PASSWORD_TEXT_FIELD_OF_SIGN_IN_SCREEN)){
                driver.findElement(By.id(PASSWORD_TEXT_FIELD_OF_SIGN_IN_SCREEN)).sendKeys(Labels_Flights.PASSWORD);
                Logger.logComment(Labels_Flights.PASSWORD +":- password is parsed");
                closeTheKeyboard_Android();
            }else {
                Logger.logError("unable to find the element name :- "+PASSWORD_TEXT_FIELD_OF_SIGN_IN_SCREEN);
            }
        }catch (Exception exception){
            Logger.logError("Encountered error: Unable to enter the login credentials");
        }
    }

    /**
     * Tap on Login button
     */
    @Override
    public void tapOnLoginButton() {
        Logger.logAction("Tapping on Login button");
        try {
            if (isElementDisplayedById(LOGIN_BUTTON)){
                boolean status = findElementByIdAndClick(LOGIN_BUTTON);
                if (status == true){
                    Logger.logComment("Tapped on login button");
                }else {
                    Logger.logError("Didn't tapped in login button");
                }
                waitTillTheSignInProgressIndicatorIsInvisibleById_ANDROID(Labels_Flights.ANDROID_ACTIVITY_INDICATOR);
                declineTheSyncPreviousTravellersDataModalView_Android();
            }else {
                Logger.logError(LOGIN_BUTTON+" - element name is not displayed in the current active screen");
            }
        }catch (Exception exception){
            Logger.logError("Encountered error: Unable to tap on the element name :- "+LOGIN_BUTTON);
        }
    }

    /**
     * Checking all the fields are filled with information is valid or not
     */
    public static void checkAllTheFieldsAreFilledWithValidInformation() {
        Logger.logAction("Checking the entered information is valid or not");
        try {

        }catch (Exception exception){
            Logger.logError("Encountered error: Unable to check all the fields are filled with valid information or not ?");
        }
    }

    /**
     * Wait till the progress indicator is invisible
     * @throws Exception
     */
    public static void waitTillTheSignInProgressIndicatorIsInvisibleById_ANDROID( String parsingID) throws Exception{
        int count =1;
        while (count< Labels_Flights.MIN_ATTEMPTS){
            try{
                if (isElementDisplayedById(parsingID)){
                    Logger.logStep("Waiting till the activity indicator is invisible in the current active screen");
                    driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(parsingID)));
                }else {
                    Logger.logStep("Activity indicator is not displayed in the current active screen");
                }
            }catch (Exception exception){
                Logger.logComment(count+" :- time trying to find the progress indicator element name");
            }
            Thread.sleep(Labels_Flights.WAIT_TIME_MIN);
            count++;
        }
    }
}
