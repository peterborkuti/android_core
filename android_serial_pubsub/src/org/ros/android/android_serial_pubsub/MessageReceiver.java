package org.ros.android.android_serial_pubsub;

import android.widget.TextView;

import org.ros.node.AbstractNodeMain;
import org.ros.node.NodeMain;

/**
 * Created by peter on 2016.05.15..
 */
public interface MessageReceiver extends NodeMain {

    public void receive(String message);

}
