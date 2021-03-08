package com.aman802.phoneapp;

import android.os.Build;
import android.telecom.Connection;
import android.telecom.ConnectionRequest;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class ConnectionService extends android.telecom.ConnectionService {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Connection onCreateOutgoingConnection(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request) {
        Connection connection = new MyConnection();
        connection.setConnectionProperties(Connection.PROPERTY_SELF_MANAGED);
        connection.setCallerDisplayName(SharedPreference.getCurrentCallerName(this), TelecomManager.PRESENTATION_ALLOWED);
        return connection;
    }

    @Override
    public void onCreateOutgoingConnectionFailed(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request) {
        Toast.makeText(this, "Call cannot be placed", Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Connection onCreateIncomingConnection(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request) {
        Connection connection = new MyConnection();
        connection.setConnectionProperties(Connection.PROPERTY_SELF_MANAGED);
        connection.setCallerDisplayName(SharedPreference.getCurrentCallerName(this), TelecomManager.PRESENTATION_ALLOWED);
        return connection;
    }

    @Override
    public void onCreateIncomingConnectionFailed(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request) {
        Toast.makeText(this, "Call cannot be placed", Toast.LENGTH_SHORT).show();
    }
}
