package com.aman802.phoneapp;

import android.telecom.Call;
import android.telecom.VideoProfile;

public class OutgoingCallObject {
    private static Call call;

    private final Object callback = new Call.Callback() {
        @Override
        public void onStateChanged(Call call, int state) {
            super.onStateChanged(call, state);
        }
    };

    public void setCall(Call call) {
        if (OutgoingCallObject.call != null) {
            OutgoingCallObject.call.unregisterCallback((Call.Callback) callback);
        }

        if (call != null) {
            call.registerCallback((Call.Callback) callback);
        }

        OutgoingCallObject.call = call;
    }

    public void answer() {
        if (OutgoingCallObject.call != null) {
            OutgoingCallObject.call.answer(VideoProfile.STATE_AUDIO_ONLY);
        }
    }

    public void hangup() {
        if (OutgoingCallObject.call != null) {
            OutgoingCallObject.call.disconnect();
        }
    }
}
