package com.automation.rehlat.flights.tests.workflows.POC;

import com.automation.rehlat.flights.Labels_Flights;
import com.automation.rehlat.flights.libCommon.Logger;
import com.automation.rehlat.flights.tests.BaseTest;
import org.junit.Test;
import static com.automation.rehlat.flights.Labels_Flights.*;
import static com.automation.rehlat.flights.Labels_Flights.DEPARTURE_MONTH;
import static com.automation.rehlat.flights.Labels_Flights.TO_INTERNATIONAL_AIRPORT_CODE;

public class TicketBooking_WithAppliedCouponCode_And_WithSignIn_FromMenuScreen extends BaseTest {

    @Test
    public void testTicketBookingWithAppliedCouponCodeAndWithSignInFromMenuScreen() throws Exception{
        Logger.beginTest("- Ticket booking with sign in menu screen and with applied coupon code");
//        FlightsScreen.checkSelectLanguageModalIsDisplayed();
        FlightsScreen.selectCountryNameInSelectLanguageModal(KUWAIT_LANGUAGE_COUNTRY_LABEL); // Country name needs to changed while testing for countries other than Kuwait.
        FlightsScreen.checkFlightsTabIsDisplayed();
        FlightsScreen.tapOnMenuButton();
        MenuScreen.checkMenuScreenIsDisplayed();
        if (MenuScreen.isUserSignedIn()){
            Logger.logComment("User is already logged in. so going to signed out from that account and signed with new account");
            MenuScreen.tapOnLogoutButton();
            FlightsScreen.checkFlightsTabIsDisplayed();
            FlightsScreen.tapOnMenuButton();
            MenuScreen.checkMenuScreenIsDisplayed();
        }
        MenuScreen.tapOnSignUpOrSignInButton();
        SignInScreen.checkSignInScreenIsDisplayed();
        SignInScreen.enterLoginCredentials();
        SignInScreen.tapOnLoginButton();
        FlightsScreen.checkFlightsTabIsDisplayed();
        FlightsScreen.tapOnMenuButton();
        MenuScreen.checkUserIsSignedUpSignedInWithCorrectParsingCredentials(Labels_Flights.EMAIL_ID_SIGN_IN);
        MenuScreen.navigateToFlightsTab();
        FlightsScreen.tapOnFromTextField();
//        driver.runAppInBackground(Labels_Hotels.BACKGROUND_TIME_MIN);
        FlightsScreen.checkSearchViewScreenIsDisplayed();
        FlightsScreen.checkKeyboardIsDisplayed();
        FlightsScreen.enterAirportName(FROM_INTERNATIONAL_AIRPORT_NAME); // Automation Defect: search results are not matching w.r.t. the send keys and due to that using Xpath of first element in search results
        FlightsScreen.selectAirportCodeFromSearchResults(FROM_INTERNATIONAL_AIRPORT_CODE);
        FlightsScreen.checkFlightsTabIsDisplayed();
        FlightsScreen.tapOnToTextField();
        FlightsScreen.checkKeyboardIsDisplayed();
        FlightsScreen.enterAirportName(TO_INTERNATIONAL_AIRPORT_NAME);
        FlightsScreen.selectAirportCodeFromSearchResults(TO_INTERNATIONAL_AIRPORT_CODE);
        FlightsScreen.checkFlightsTabIsDisplayed();
        FlightsScreen.tapOnDepartureDateBookingButton();
        FlightsScreen.selectDepartureDate(DEPARTURE_MONTH,DEPARTURE_DAY); // Automation Defect: Due to improper element names in calendar view unable to tap on accurate departure date
        FlightsScreen.tapOnDoneButton();
        FlightsScreen.checkFlightsTabIsDisplayed();
        FlightsScreen.tapOnSearchButton();
        FlightsSearchResultsScreen.checkFlightsSearchResultsScreenIsDisplayed();
        FlightsSearchResultsScreen.tapOnACellInFlightSearchResults(CELL_NUMBER_OF_FLIGHT_SEARCH_RESULTS); //Change this method to tap on a flight cell by sending the cell number as string/integer like this : //  FlightsSearchResultsIos.getTheBookingCostOfSelectedFlightInSearchResults("6");
        if (FlightsSimilarOptionsSearchResultsScreen.checkFlightsSimilarOptionsSearchResultsScreenIsDisplayed()){
            FlightsSimilarOptionsSearchResultsScreen.tapOnFirstSimilarOptionCell();
        }else{
            Logger.logComment("Flights similar options screen is not displayed");
        }
        ReviewBookingScreen.checkReviewBookingScreenIsDisplayed();
        ReviewBookingScreen.compareTheSelectedBookingSeatCostInSearchResultsScreenDisplayedInReviewBookingScreen();
        ReviewBookingScreen.tapOnContinueButton();
        BookingPageScreen.checkBookingPageScreenIsDisplayed();
        if (!BookingPageScreen.isUserIsSignedIn()){
            Logger.logError("User is not logged in in booking page where as logged in menu screen");
        }
        BookingPageScreen.enterUserBookingInfo();
        BookingPageScreen.applyTheCouponCode();
        BookingPageScreen.checkFinalFareCalculationIsCorrect();
        BookingPageScreen.tapOnAdultAddTravellersDetailsButton();
        TravellerDetailsScreen.declineAutoFillPopulateModalIfDisplayed();
        TravellerDetailsScreen.checkTravellersDetailsScreenIsDisplayed();
        TravellerDetailsScreen.enterAdultTravellersDetails(INTERNATIONALS_TRAVELLERS_COUNTRY_NAME);
        TravellerDetailsScreen.tapOnSaveButton();
        BookingPageScreen.checkBookingPageScreenIsDisplayed();
        BookingPageScreen.tapOnContinueButton();
        if (PaymentOptionsScreen.isTicketSoldOutPopUpIsDisplayed()){
            PaymentOptionsScreen.tapOnOkButtonInTicketSoldOutPopup();
            Logger.logComment("Second time selecting the seat for different flight");
            FlightsSearchResultsScreen.checkFlightsSearchResultsScreenIsDisplayed();
            FlightsSearchResultsScreen.tapOnACellInFlightSearchResults(CELL_NUMBER_OF_FLIGHT_SEARCH_RESULTS_WHEN_SOLDOUT_POPUP_INTERRUPTRED); //Change this method to tap on a flight cell by sending the cell number as string/integer like this : //  FlightsSearchResultsIos.getTheBookingCostOfSelectedFlightInSearchResults("6");
            if ( FlightsSimilarOptionsSearchResultsScreen.checkFlightsSimilarOptionsSearchResultsScreenIsDisplayed()){
                FlightsSimilarOptionsSearchResultsScreen.tapOnFirstSimilarOptionCell();
            }else{
                Logger.logComment("Flights similar options screen is not displayed");
            }
            ReviewBookingScreen.checkReviewBookingScreenIsDisplayed();
            ReviewBookingScreen.compareTheSelectedBookingSeatCostInSearchResultsScreenDisplayedInReviewBookingScreen();
            ReviewBookingScreen.tapOnContinueButton();
            BookingPageScreen.checkBookingPageScreenIsDisplayed();
            if (!BookingPageScreen.isUserIsSignedIn()){
                Logger.logError("User is not logged in in booking page where as logged in menu screen");
            }
            BookingPageScreen.enterUserBookingInfo();
            BookingPageScreen.applyTheCouponCode();
            BookingPageScreen.checkFinalFareCalculationIsCorrect();
            BookingPageScreen.tapOnAdultAddTravellersDetailsButton();
            TravellerDetailsScreen.declineAutoFillPopulateModalIfDisplayed();
            TravellerDetailsScreen.checkTravellersDetailsScreenIsDisplayed();
            TravellerDetailsScreen.declineAutoFillPopulateModalIfDisplayed();
            TravellerDetailsScreen.enterAdultTravellersDetails(INTERNATIONALS_TRAVELLERS_COUNTRY_NAME);
            TravellerDetailsScreen.tapOnSaveButton();
            BookingPageScreen.checkBookingPageScreenIsDisplayed();
            BookingPageScreen.tapOnContinueButton();
            if (PaymentOptionsScreen.isTicketSoldOutPopUpIsDisplayed()) {
                PaymentOptionsScreen.tapOnOkButtonInTicketSoldOutPopup();
                Logger.logError("Tried booking seats for two flights, both tickets are sold out......, Please re-run the script with different dates");
            }else {
                PaymentOptionsScreen.checkPaymentOptionsScreenIsDisplayed();
                // KNET PAYMENT PROCESS
                PaymentOptionsScreen.tapOnKnetPaymentGateWay();
                PaymentOptionsScreen.checkKnetPaymentOptionsScreenIsDisplayed();
                PaymentOptionsScreen.selectingBankName(TESTING_BANK_CARD);
                PaymentOptionsScreen.enterCardNumber(); // Automation Defect: Card number text field is not intractable in iOS Platform
                PaymentOptionsScreen.enterPinNumber();
                PaymentOptionsScreen.tapOnSubmitButton();
                PaymentOptionsScreen.checkPostTransactionScreenIsDisplayed();
                PaymentOptionsScreen.tapOnConfirmButton();
                PaymentOptionsScreen.checkTheKnetBookingProcessIsSuccess();

                // CREDIT OR DEBIT CARD PAYMENT PROCESS
//            PaymentOptionsScreen.enterCreditOrDebitCardDetails();
//            PaymentOptionsScreen.enterKeysInThePasswordFieldOf3DSecureCreditOrDebitCardCheckOutPayment();
//            PaymentOptionsScreen.tapOnContinueButtonIn3DSecurePaymentScreenOfCreditOrDebitCardCheckOutPayment();
//            PaymentOptionsScreen.checkTheCreditOrDebitCardBookingProcessIsSuccess();
//        }
            }
        }else {
            Thread.sleep(Labels_Flights.WAIT_TIME_MIN);
            PaymentOptionsScreen.checkPaymentOptionsScreenIsDisplayed();
            // KNET PAYMENT PROCESS
            PaymentOptionsScreen.tapOnKnetPaymentGateWay();
            PaymentOptionsScreen.checkKnetPaymentOptionsScreenIsDisplayed();
            PaymentOptionsScreen.selectingBankName(TESTING_BANK_CARD);
            PaymentOptionsScreen.enterCardNumber(); // Automation Defect: Card number text field is not intractable in iOS Platform
            PaymentOptionsScreen.enterPinNumber();
            PaymentOptionsScreen.tapOnSubmitButton();
            PaymentOptionsScreen.checkPostTransactionScreenIsDisplayed();
            PaymentOptionsScreen.tapOnConfirmButton();
            PaymentOptionsScreen.checkTheKnetBookingProcessIsSuccess();

            // CREDIT OR DEBIT CARD PAYMENT PROCESS
//            PaymentOptionsScreen.enterCreditOrDebitCardDetails();
//            PaymentOptionsScreen.enterKeysInThePasswordFieldOf3DSecureCreditOrDebitCardCheckOutPayment();
//            PaymentOptionsScreen.tapOnContinueButtonIn3DSecurePaymentScreenOfCreditOrDebitCardCheckOutPayment();
//            PaymentOptionsScreen.checkTheCreditOrDebitCardBookingProcessIsSuccess();
        }
        Logger.endTest("- Ticket booking with sign in menu screen and with applied coupon code");
    }
}
