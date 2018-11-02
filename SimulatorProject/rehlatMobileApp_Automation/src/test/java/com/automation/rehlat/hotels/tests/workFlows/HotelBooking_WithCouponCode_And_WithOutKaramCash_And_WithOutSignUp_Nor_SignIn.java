package com.automation.rehlat.hotels.tests.workFlows;

import com.automation.rehlat.hotels.libCommon.Logger;
import com.automation.rehlat.hotels.pages.interfaces.SelectRooms;
import com.automation.rehlat.hotels.tests.BaseTest;
import org.junit.Test;

import static com.automation.rehlat.hotels.Labels_Hotels.*;

public class HotelBooking_WithCouponCode_And_WithOutKaramCash_And_WithOutSignUp_Nor_SignIn extends BaseTest {
    @Test
    public void testTicketBooking_WithCouponCode_And_WithOutKaramCash_And_WithOutSignUp_Nor_SignIn() throws Exception{
        Logger.beginTest("Ticket booking with coupon code and without karam cash and without sign in nor sign up");
        HotelsScreen.tapOnHotelsTab();
        HotelsScreen.checkHotelScreenISDisplayed();
        HotelsScreen.tapOnMenuButton();
        MenuScreen.checkMenuScreenIsDisplayed();
        if (MenuScreen.isUserSignedIn()){
            MenuScreen.tapOnLogoutButton();
            HotelsScreen.tapOnHotelsTab();
        }else {
            MenuScreen.navigateToFlightsTab();
        }
        HotelsScreen.checkHotelScreenISDisplayed();
        HotelsScreen.tapOnSearchButton();
        HotelsScreen.sendKeysToSearchResultsScreen("dxb");
        String selectedName = HotelsScreen.tapOnFirstCityNameInSearchResults("Dubai, United Arab Emirates");
        HotelsScreen.checkTheSearchTextFieldIsFilledWithSelectedNameOf(selectedName);
        HotelsScreen.tapOnCheckInButton();
        HotelsScreen.tapOnCheckInOptionInCalendarView();
        HotelsScreen.selectCheckInDate(CHECK_IN_MONTH,CHECK_IN_DAY);
        HotelsScreen.tapOnDoneButtonInCalendarView();
        HotelsScreen.tapOnCheckOutButton();
        HotelsScreen.selectCheckOutDate(CHECK_OUT_MONTH,CHECK_OUT_DAY);
        HotelsScreen.tapOnDoneButtonInCalendarView();
        HotelsScreen.tapOnAdultAndChildLayout();
        HotelsScreen.setTheGuestCountTo(1,1,2); //Todo:- Discuss with stake holder or dev on allowing the selection of child more than adults
//        HotelsScreen.tapOnAddRoomButton();
//        HotelsScreen.setTheGuestCountTo(2,1,1); //Todo:- Discuss with stake holder or dev on allowing the selection of child more than adults
//        HotelsScreen.tapOnAddRoomButton();
//        HotelsScreen.setTheGuestCountTo(3,1,1); //Todo:- Discuss with stake holder or dev on allowing the selection of child more than adults
//        HotelsScreen.tapOnAddRoomButton();
//        HotelsScreen.setTheGuestCountTo(4,1,1); //Todo:- Discuss with stake holder or dev on allowing the selection of child more than adults
//        HotelsScreen.tapOnAddRoomButton();
//        HotelsScreen.setTheGuestCountTo(5,1,1); //Todo:- Discuss with stake holder or dev on allowing the selection of child more than adults
        HotelsScreen.tapOnDoneButtonOnRoomListView();
        HotelsScreen.checkHotelScreenISDisplayed();
        HotelsScreen.tapOnCheckAvailabilityButton();
        HotelsSearchResultsScreen.checkTheHotelsSRPScreenIsDisplayed();
        HotelsSearchResultsScreen.getThePriceOfHotelAndTapOnItsCardView(1);
        if (HotelsProfileScreen.isSoldOutAlertIsDisplayed()){
            Logger.logStep("Sold out alert is displayed, so re selecting the hotel from SRP");
            HotelsProfileScreen.tapOnSeeAvailablePropertiesButtonInSoldOutAlert();
            HotelsSearchResultsScreen.checkTheHotelsSRPScreenIsDisplayed();
            HotelsSearchResultsScreen.getThePriceOfHotelAndTapOnItsCardView(2);
            if (HotelsProfileScreen.isSoldOutAlertIsDisplayed()){
                Logger.logStep("Sold out alert is displayed even after selecting the new hotel from SRP, so re changing the hotel dates from hotel profile screen");
                HotelsProfileScreen.tapOnChangeYourDatesButtonInSoldOutAlert();
                HotelsScreen.tapOnCheckInOptionInCalendarView();
                HotelsScreen.selectCheckInDate(SECOND_CHECK_IN_MONTH,"1");
                HotelsScreen.selectCheckOutDate(SECOND_CHECK_OUT_MONTH,"2");
                HotelsScreen.tapOnDoneButtonInCalendarView();
                if (HotelsProfileScreen.isSoldOutAlertIsDisplayed()){
                    Logger.logError("Tried selecting new hotel and changing the dates still shows the sold out alert");
                }
            }
        }
        HotelsProfileScreen.checkTheHotelProfileScreenIsDisplayed();
        HotelsProfileScreen.compareSelectedHotelPriceInSRPAndInHotelProfileScreens();
        HotelsProfileScreen.tapOnSelectRoomButton();
        SelectRoomsScreen.checkSelectRoomScreenIsDisplayed();
        SelectRoomsScreen.tapOnSelectButtonInRoomCellNumber(1,1); // Todo:- Yet to discuss with the dev as multiple individual rooms selection is not possible [Eg: One user, multiple types of room selections are not possible]
        SelectRoomsScreen.compareSelectedRoomPriceInSelectRoomScreenAndHotelProfileScreen(1);
        SelectRoomsScreen.tapOnContinueButton();
        if (HotelsProfileScreen.isSoldOutAlertIsDisplayed()){
            Logger.logStep("Sold out alert is displayed, so re selecting the hotel from SRP");
            SelectRoomsScreen.tapOnSeeAvailablePropertiesButtonInSoldOutAlert();
            HotelsSearchResultsScreen.checkTheHotelsSRPScreenIsDisplayed();
            HotelsSearchResultsScreen.getThePriceOfHotelAndTapOnItsCardView(2);
            if (HotelsProfileScreen.isSoldOutAlertIsDisplayed()){
                Logger.logStep("Sold out alert is displayed even after selecting the new hotel from SRP when sold out alert is displayed in hotel profile screen");
                HotelsProfileScreen.tapOnChangeYourDatesButtonInSoldOutAlert();
                HotelsScreen.tapOnCheckInOptionInCalendarView();
                HotelsScreen.selectCheckInDate(SECOND_CHECK_IN_MONTH,"2");
                HotelsScreen.selectCheckOutDate(SECOND_CHECK_OUT_MONTH,"3");
                HotelsScreen.tapOnDoneButtonInCalendarView();
                if (HotelsProfileScreen.isSoldOutAlertIsDisplayed()){
                    Logger.logError("Tried selecting new hotel and changing the dates still shows the sold out alert");
                }
            }
            HotelsProfileScreen.checkTheHotelProfileScreenIsDisplayed();
            HotelsProfileScreen.compareSelectedHotelPriceInSRPAndInHotelProfileScreens();
            HotelsProfileScreen.tapOnSelectRoomButton();
            SelectRoomsScreen.checkSelectRoomScreenIsDisplayed();
            SelectRoomsScreen.tapOnSelectButtonInRoomCellNumber(1,1); // Todo:- Yet to discuss with the dev as multiple individual rooms selection is not possible [Eg: One user, multiple types of room selections are not possible]
            SelectRoomsScreen.compareSelectedRoomPriceInSelectRoomScreenAndHotelProfileScreen(1);
            SelectRoomsScreen.tapOnContinueButton();
            if (SelectRoomsScreen.isHotelsSoldOutAlertIsDisplayed()) {
                Logger.logError("Tried two times still shows the sold out alert even after selecting the new hotel from SRP when sold out alert is displayed in hotel profile screen");
            }
        }
        if (BookingSummaryScreen.isFareJumpAlertIsDisplayed()){
            BookingSummaryScreen.tapOnProceedButtonInFareJumpAlert();
        }
        BookingSummaryScreen.checkBookingPageScreenIsDisplayed();
        if (BookingSummaryScreen.isUserIsSignedIn()){
            Logger.logError("User is logged out in menu screen but in booking summary screens shows as signed in again..,");
        }
        BookingSummaryScreen.enterUserBookingInfo();
        BookingSummaryScreen.applyTheCouponCode();
        BookingSummaryScreen.disableKaramPointsToggleSwitch();
        BookingSummaryScreen.checkFinalFareCalculationIsCorrect();
        BookingSummaryScreen.tapOnAddGuestTravellersDetailsButton();
        GuestTravellersDetailsScreen.declineAutoFillPopulateModalIfDisplayed();
        GuestTravellersDetailsScreen.checkTravellersDetailsScreenIsDisplayed();
        GuestTravellersDetailsScreen.enterTravellersDetailsForPassengers(1,1,2);
        GuestTravellersDetailsScreen.tapOnSaveButton();
//        GuestTravellersDetailsScreen.enterTravellersDetailsForPassengers(2,1,1);
//        GuestTravellersDetailsScreen.tapOnSaveButton();
//        GuestTravellersDetailsScreen.enterTravellersDetailsForPassengers(3,1,1);
//        GuestTravellersDetailsScreen.tapOnSaveButton();
//        GuestTravellersDetailsScreen.enterTravellersDetailsForPassengers(4,1,1);
//        GuestTravellersDetailsScreen.tapOnSaveButton();
//        GuestTravellersDetailsScreen.enterTravellersDetailsForPassengers(5,1,1);
//        GuestTravellersDetailsScreen.tapOnSaveButton();
        BookingSummaryScreen.tapOnContinueButton();
//        if (HotelsProfileScreen.isSoldOutAlertIsDisplayed()){
//            Logger.logStep("Sold out alert is displayed, so re selecting the hotel from SRP");
//            HotelsProfileScreen.tapOnSeeAvailablePropertiesButtonInSoldOutAlert();
//            HotelsSearchResultsScreen.checkTheHotelsSRPScreenIsDisplayed();
//            HotelsSearchResultsScreen.getThePriceOfHotelAndTapOnItsCardView(2);
//            if (HotelsProfileScreen.isSoldOutAlertIsDisplayed()){
//                Logger.logStep("Sold out alert is displayed even after selecting the new hotel from SRP, so re changing the hotel dates from hotel profile screen");
//                HotelsProfileScreen.tapOnChangeYourDatesButtonInSoldOutAlert();
//                HotelsScreen.tapOnCheckInOptionInCalendarView();
//                HotelsScreen.selectCheckInDate(SECOND_CHECK_IN_MONTH,"1");
//                HotelsScreen.selectCheckOutDate(SECOND_CHECK_OUT_MONTH,"2");
//                HotelsScreen.tapOnDoneButtonInCalendarView();
//                if (HotelsProfileScreen.isSoldOutAlertIsDisplayed()){
//                    Logger.logError("Tried selecting new hotel and changing the dates still shows the sold out alert");
//                }
//            }
//        }
        PaymentOptionsScreen.checkPaymentOptionsScreenIsDisplayed();
        PaymentOptionsScreen.compareTheFinalPaymentDisplayedInPaymentsCheckOutScreenWithPaymentDisplayedInReviewBookingScreen();
        // KNET PAYMENT PROCESS
//        PaymentOptionsScreen.tapOnKnetPaymentGateWay();
//        PaymentOptionsScreen.checkKnetPaymentOptionsScreenIsDisplayed();
//        PaymentOptionsScreen.selectingBankName(TESTING_BANK_CARD);
//        PaymentOptionsScreen.enterCardNumber(); // Automation Defect: Card number text field is not intractable in iOS Platform
//        PaymentOptionsScreen.enterPinNumber();
//        PaymentOptionsScreen.tapOnSubmitButton();
//        PaymentOptionsScreen.checkPostTransactionScreenIsDisplayed();
//        PaymentOptionsScreen.tapOnConfirmButton();
//        PaymentOptionsScreen.checkTheKnetBookingProcessIsSuccess();

//                 CREDIT OR DEBIT CARD PAYMENT PROCESS
//        PaymentOptionsScreen.enterCreditOrDebitCardDetails();
//        PaymentOptionsScreen.enterKeysInThePasswordFieldOf3DSecureCreditOrDebitCardCheckOutPayment();
//        PaymentOptionsScreen.tapOnContinueButtonIn3DSecurePaymentScreenOfCreditOrDebitCardCheckOutPayment();
//        PaymentOptionsScreen.checkTheCreditOrDebitCardBookingProcessIsSuccess();
        Logger.endTest("Ticket booking with coupon code and without karam cash and without sign in nor sign up");
    }
}
