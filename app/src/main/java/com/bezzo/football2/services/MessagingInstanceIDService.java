package com.bezzo.football2.services;

import com.bezzo.core.util.*;
import com.bezzo.football2.*;
import com.bezzo.football2.di.component.*;
import com.google.firebase.iid.*;

/**
 * Created by bezzo on 21/02/18.
 */

public class MessagingInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        AppLogger.d("Refresh Firebase Token : "+ refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }

    public void sendRegistrationToServer(String refreshedToken){
        // request to api for refreshed token
    }
}
