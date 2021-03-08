package com.aman802.phoneapp;

import android.telecom.Connection;
import android.telecom.DisconnectCause;

public class MyConnection extends Connection {

    @Override
    public void onShowIncomingCallUi() {
        super.onShowIncomingCallUi();
    }

    @Override
    public void onHold() {
        setOnHold();
    }

    @Override
    public void onUnhold() {
        setActive();
    }

    @Override
    public void onAnswer() {
        setActive();
    }

    @Override
    public void onReject() {
        setDisconnected(new DisconnectCause(DisconnectCause.REJECTED));
        destroy();
    }

    @Override
    public void onDisconnect() {
        setDisconnected(new DisconnectCause(DisconnectCause.LOCAL));
        destroy();
    }
}
