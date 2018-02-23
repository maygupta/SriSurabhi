package com.socal.connection.sri.surabhi.media.screen;

import android.app.Activity;

import com.socal.connection.sri.surabhi.media.controller.SriSurabhiActivity;
import com.socal.connection.sri.surabhi.media.screen.Event.EventBookedScreen;
import com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.ExploreSriSurbhiScreen;
import com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.SubItem.AllAboutCowsScreen;
import com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.SubItem.GetInvolvedScreen;
import com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.SubItem.GoSevaScreen;
import com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.SubItem.PanchgavyaScreen;
import com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.SubItem.SurabhiConnectScreen;
import com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.SubItem.SurabhiMediaScreen;
import com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.SubItem.VRTScreen;
import com.socal.connection.sri.surabhi.media.screen.Home.HomeScreen;
import com.socal.connection.sri.surabhi.media.screen.News.NewScreen;
import com.socal.connection.sri.surabhi.media.screen.Setup.EmailScreen;
import com.socal.connection.sri.surabhi.media.screen.Setup.LicenseScreen;
import com.socal.connection.sri.surabhi.media.screen.Setup.PickFileScreen;
import com.socal.connection.sri.surabhi.media.screen.Setup.SetupScreen;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.screen.Subscription.SubscriptionViewAllScreen;
import com.socal.connection.sri.surabhi.media.screen.Subscription.SubscriptionScreen;
import com.socal.connection.sri.surabhi.media.utils.Enums.ScreenEnums;


public class ScreenSwitchHandler {
    public static ScreenSwitchHandler screenSwitchHandler = new ScreenSwitchHandler();
    public ScreenEnums currentScreenID = ScreenEnums.SCREEN_NONE;
    private Screen currentScreenObject = null;

    public static ScreenSwitchHandler getScreenSwitchHandler() {
        if (screenSwitchHandler == null) {
            screenSwitchHandler = new ScreenSwitchHandler();
        }
        return screenSwitchHandler;
    }

    public static void clean() {
        if (screenSwitchHandler != null) {
            screenSwitchHandler.cleanUp();
            screenSwitchHandler = null;
        }
    }

    public void setCurrentScreenID(ScreenEnums screenID, Activity activity, Object data, SetupFile setupFile) {
        if (currentScreenObject != null) {
            currentScreenObject.onLeaveScreen();
        }
        currentScreenID = screenID;
        switch (currentScreenID) {
            case SCREEN_EXIT:
                activity.finish();
                break;
            case SCREEN_HOME:
                currentScreenObject = new ExploreSriSurbhiScreen(activity, data, setupFile);
                break;
            case SCREEN_EMAIL:
                currentScreenObject = new EmailScreen(activity, data, setupFile);
            case SCREEN_SETUP:
                currentScreenObject = new SetupScreen(activity, data, setupFile);
                break;
            case SCREEN_LICENCE:
                currentScreenObject = new LicenseScreen(activity, data, setupFile);
                break;
            case SCREEN_PICK_FILE:
                currentScreenObject = new PickFileScreen(activity, data, setupFile);
                break;
            case SCREEN_REFEAR_A_FRIEND:
                currentScreenObject = new ReferAFriendScreen(activity, data, setupFile);
                break;
            case SCREEN_EXPLORE_SRI_SURBHI:
                currentScreenObject = new ExploreSriSurbhiScreen(activity, data, setupFile);
                break;
            case SCREEN_UPCOMING_EVENT:
                currentScreenObject = new EventBookedScreen(activity, data, setupFile);
                break;
            case SCREEN_EDIT_PROFILE:
                currentScreenObject = new EditProfileScreen(activity, data, setupFile);
                break;
            case SCREEN_SUBSCRIPTION:
                currentScreenObject = new SubscriptionScreen(activity, data, setupFile);
                break;
            case SCREEN_SUBSCRIPTION_MEMBER_SHIPS:
                currentScreenObject = new SubscriptionViewAllScreen(activity, data, setupFile);
                break;
            case SCREEN_ALL_ABOUT_COWS_SCREEN:
                currentScreenObject = new AllAboutCowsScreen(activity, data, setupFile);
                break;
            case SCREEN_GO_Seva:
                currentScreenObject = new GoSevaScreen(activity, data, setupFile);
                break;
            case SCREEN_PANCHGAVYA:
                currentScreenObject = new PanchgavyaScreen(activity, data, setupFile);
                break;
            case SCREEN_SURABHI_MEDIA:
                currentScreenObject = new SurabhiMediaScreen(activity, data, setupFile);
                break;
            case SCREEN_VRT:
                currentScreenObject = new VRTScreen(activity, data, setupFile);
                break;
            case SCREEN_SURABHI_CONNECT:
                currentScreenObject = new SurabhiConnectScreen(activity, data, setupFile);
                break;
            case SCREEN_GET_INVOLVED:
                currentScreenObject = new GetInvolvedScreen(activity, data, setupFile);
                break;
            case SCREEN_SUPPORT:
                currentScreenObject = new SupportScreen(activity, data, setupFile);
                break;
            case SCREEN_PAYMENT:
                currentScreenObject = new PaymentScreen(activity, data, setupFile);
                break;
            case SCREEN_NEWS_FEED:
                currentScreenObject = new NewScreen(activity, data, setupFile);
                break;
            default:
                activity.finish();
        }
        activity.invalidateOptionsMenu();
    }

    public Screen getCurrentScreen() {
        return currentScreenObject;
    }

    public ScreenEnums getCurrentScreenID() {
        return currentScreenID;
    }

    public void cleanUp() {
        currentScreenObject = null;
        currentScreenID = ScreenEnums.SCREEN_NONE;
    }

}
