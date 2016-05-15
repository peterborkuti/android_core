package org.ros.android.android_serial_pubsub;

import android.widget.TextView;

/**
 * Created by peter on 2016.05.15..
 */
public interface UsbMessageReceiver {

    public void receive(TextView textView, String message);

}
