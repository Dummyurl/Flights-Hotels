package com.automation.rehlat.flights.tests.workflows.onlyDepartureBookings.internationalFlightBookings;

import com.automation.rehlat.flights.Labels_Flights;
import com.automation.rehlat.flights.libCommon.Logger;
import com.automation.rehlat.flights.tests.BaseTest;
import org.junit.Test;

import static com.automation.rehlat.flights.Labels_Flights.*;

public class OneWayBooking_InternationalTicket_WithAppliedCouponCode_And_WithSignUp_FromMenuScreen extends BaseTest {
    @Test
    public void testTicketBookingWithAppliedCouponCodeAndWithSignUpFromMenuScreen() throws Exception{
        Logger.beginTest("OneWay_International_Ticket booking with applied coupon code and with sign Up from menu screen");
        printCurrentTestRunningInformation();
        Labels_Flights.FLIGHT_BOOKING_TYPE = INTERNATIONAL_FLIGHT_BOOKING;
        createNewSignUpEmailId();
        FlightsScreen.selectCountryNameInSelectLanguageModal(Labels_Flights.CURRENT_USER_COUNTRY_NAME); // Country name needs to changed while testing for countries other than Kuwait.
        FlightsScreen.checkFlightsTabIsDisplayed();
        FlightsScreen.tapOnMenuButton();
        MenuScreen.checkMenuScreenIsDisplayed();
        if (MenuScreen.isUserSignedIn()){
            Logger.logComment("User is already logged in. so going to logged out from that account and signed up with new account");
            MenuScreen.tapOnLogoutButton();
            FlightsScreen.checkFlightsTabIsDisplayed();
            FlightsScreen.tapOnMenuButton();
            MenuScreen.checkMenuScreenIsDisplayed();
        }
        MenuScreen.tapOnSignUpOrSignInButton();
        SignInScreen.checkSignInScreenIsDisplayed();
        SignInScreen.tapOnCreateAccountButton();
        SignUpScreen.checkSignUpScreenIsDisplayed();
        SignUpScreen.enterSignUpCredentials();
        SignUpScreen.tapOnSignUpButton();
        FlightsScreen.checkFlightsTabIsDisplayed();
        FlightsScreen.tapOnMenuButton();
        MenuScreen.checkUserIsSignedUpSignedInWithCorrectParsingCredentials(Labels_Flights.EMAIL_ID_SIGN_UP);
        MenuScreen.navigateToFlightsTab();
        FlightsScreen.tapOnFromTextField();
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
        FlightsScreen.setThePassengersCountTo(1,1,0); // This should be same as travellers details passengers count
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
            Logger.logError("User is not signed in in booking page where as signed in menu screen");
        }
        BookingPageScreen.enterUserBookingInfo();
        BookingPageScreen.applyTheCouponCode();
        BookingPageScreen.enableKaramPointsToggleSwitch();
        BookingPageScreen.tapOnAdultAddTravellersDetailsButton();
        if (platform.equalsIgnoreCase(Labels_Flights.IOS)){
            TravellerDetailsScreen.acceptAutoFillPopulateModalIfDisplayed(2);
        }else {
            TravellerDetailsScreen.EnterTravellersDetailsForPassengers(2); // This should be same as passengers count in flights tab
        }
        TravellerDetailsScreen.tapOnSaveButton();
        BookingPageScreen.checkBookingPageScreenIsDisplayed();
        BookingPageScreen.checkFinalFareCalculationIsCorrect();
        BookingPageScreen.tapOnContinueButton();
        if (PaymentOptionsScreen.isTicketSoldOutPopUpIsDisplayed()){
            PaymentOptionsScreen.tapOnOkButtonInTicketSoldOutPopup();
            Logger.logStep("Second time selecting the seat for different flight");
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
                Logger.logError("User is not signed in in booking page where as signed in menu screen");
            }
            BookingPageScreen.enterUserBookingInfo();
            BookingPageScreen.applyTheCouponCode();
            BookingPageScreen.enableKaramPointsToggleSwitch();
            BookingPageScreen.tapOnAdultAddTravellersDetailsButton();
            TravellerDetailsScreen.EnterTravellersDetailsForPassengers(2); // This should be same as passengers count in flights tab
            TravellerDetailsScreen.tapOnSaveButton();
            BookingPageScreen.checkBookingPageScreenIsDisplayed();
            BookingPageScreen.checkFinalFareCalculationIsCorrect();
            BookingPageScreen.tapOnContinueButton();
            if (PaymentOptionsScreen.isTicketSoldOutPopUpIsDisplayed()) {
                addTestResultStatusToExecutionResultsJsonFile(Labels_Flights.testCaseName,"false Tickets Are Sold Out In Two Attempts");// Todo:- By Default setting the test case execution status as false
                Logger.logError("Tried booking seats for two flights, both tickets are sold out......, Please re-run the script with different dates");
            }else {
                PaymentOptionsScreen.checkPaymentOptionsScreenIsDisplayed();
                PaymentOptionsScreen.compareTheFinalPaymentDisplayedInPaymentsCheckOutScreenWithPaymentDisplayedInReviewBookingScreen();
                if (Labels_Flights.platform.equalsIgnoreCase(IOS)){
                    // KNET PAYMENT PROCESS
//                PaymentOptionsScreen.tapOnKnetPaymentGateWay();
//                PaymentOptionsScreen.checkKnetPaymentOptionsScreenIsDisplayed();
//                PaymentOptionsScreen.selectingBankName(TESTING_BANK_CARD);
//                PaymentOptionsScreen.enterCardNumber(); // Automation Defect: Card number text field is not intractable in iOS Platform
//                PaymentOptionsScreen.enterPinNumber();
//                PaymentOptionsScreen.tapOnSubmitButton();
//                PaymentOptionsScreen.checkPostTransactionScreenIsDisplayed();
//                PaymentOptionsScreen.tapOnConfirmButton();
//                PaymentOptionsScreen.checkTheKnetBookingProcessIsSuccess();

                    // CREDIT OR DEBIT CARD PAYMENT PROCESS
            PaymentOptionsScreen.enterCreditOrDebitCardDetails();
            PaymentOptionsScreen.enterKeysInThePasswordFieldOf3DSecureCreditOrDebitCardCheckOutPayment();
            PaymentOptionsScreen.tapOnContinueButtonIn3DSecurePaymentScreenOfCreditOrDebitCardCheckOutPayment();
            PaymentOptionsScreen.checkTheCreditOrDebitCardBookingProcessIsSuccess();
                }
            }
        }
            else {
            Thread.sleep(Labels_Flights.WAIT_TIME_MIN);
            PaymentOptionsScreen.checkPaymentOptionsScreenIsDisplayed();
            PaymentOptionsScreen.compareTheFinalPaymentDisplayedInPaymentsCheckOutScreenWithPaymentDisplayedInReviewBookingScreen();
            if (Labels_Flights.platform.equalsIgnoreCase(IOS)){
                // KNET PAYMENT PROCESS
//            PaymentOptionsScreen.tapOnKnetPaymentGateWay();
//            PaymentOptionsScreen.checkKnetPaymentOptionsScreenIsDisplayed();
//            PaymentOptionsScreen.selectingBankName(TESTING_BANK_CARD);
//            PaymentOptionsScreen.enterCardNumber(); // Automation Defect: Card number text field is not intractable in iOS Platform
//            PaymentOptionsScreen.enterPinNumber();
//            PaymentOptionsScreen.tapOnSubmitButton();
//            PaymentOptionsScreen.checkPostTransactionScreenIsDisplayed();
//            PaymentOptionsScreen.tapOnConfirmButton();
//            PaymentOptionsScreen.checkTheKnetBookingProcessIsSuccess();
                // CREDIT OR DEBIT CARD PAYMENT PROCESS
            PaymentOptionsScreen.enterCreditOrDebitCardDetails();
            PaymentOptionsScreen.enterKeysInThePasswordFieldOf3DSecureCreditOrDebitCardCheckOutPayment();
            PaymentOptionsScreen.tapOnContinueButtonIn3DSecurePaymentScreenOfCreditOrDebitCardCheckOutPayment();
            PaymentOptionsScreen.checkTheCreditOrDebitCardBookingProcessIsSuccess();
            }

        }
        Logger.endTest(" - Ticket booking with applied coupon code and with signUp from menu screen");
        addTestResultStatusToExecutionResultsJsonFile(Labels_Flights.testCaseName,"true");
    }
}
