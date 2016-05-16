package org.ros.android.android_serial_pubsub;


import android.content.Context;
import android.widget.TextView;

import org.apache.commons.logging.Log;
import org.ros.message.MessageListener;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Subscriber;

import std_msgs.String;

/**
 * Created by peter on 2016.05.16..
 */
public class ROSMessageListener extends UsbMessageReceiver {
    ROSMessageListener(Context context, TextView textView) {
        super(context, textView, "arduino_input");

    }

    @Override
    public void onStart(ConnectedNode connectedNode) {
        Subscriber<String> subscriber = connectedNode.newSubscriber(ROS_TOPIC, ROS_MSG_TYPE);
        subscriber.addMessageListener(new MessageListener<String>() {
            @Override
            public void onNewMessage(std_msgs.String message) {
                showInTextView(message.getData());
            }
        });
    }
}
