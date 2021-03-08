package com.aman802.phoneapp;

import android.content.Intent;
import android.telecom.Call;
import android.telecom.InCallService;

public class CallService extends InCallService {

    OutgoingCallObject outgoingCallObject = new OutgoingCallObject();

    @Override
    public void onCallAdded(Call call) {
        super.onCallAdded(call);
        outgoingCallObject.setCall(call);

        //Add call to start CallActivity
        Intent intent = new Intent(this, CallActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("userName", SharedPreference.getCurrentCallerName(this));
        intent.setData(call.getDetails().getHandle());
        startActivity(intent);

    }

    @Override
    public void onCallRemoved(Call call) {
        super.onCallRemoved(call);
        outgoingCallObject.setCall(null);
    }
}
