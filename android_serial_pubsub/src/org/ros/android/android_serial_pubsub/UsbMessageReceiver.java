package org.ros.android.android_serial_pubsub;

import android.widget.TextView;

import org.ros.node.AbstractNodeMain;
import org.ros.node.NodeMain;

/**
 * Created by peter on 2016.05.15..
 */
public interface UsbMessageReceiver extends NodeMain {
    public static final String ROS_OUTPUT_TOPIC = "arduino_output";

    public void receive(TextView textView, String message);

}
