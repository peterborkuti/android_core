package org.ros.android.android_serial_pubsub;


import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import org.ros.message.MessageListener;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Subscriber;

import std_msgs.String;

//import std_msgs.String;

/**
 * Created by peter on 2016.05.16..
 */
public class ROSMessageListener extends UsbMessageReceiver {
    private UsbService usbService;

    public ROSMessageListener(Activity activity, Context context, TextView textView, UsbService usbService) {
        super(activity, context, textView, "toArduino", "roslistener");
        this.usbService = usbService;
    }

    @Override
    public void onStart(ConnectedNode connectedNode) {
        Subscriber<String> subscriber = connectedNode.newSubscriber(ROS_TOPIC, std_msgs.String._TYPE);
        subscriber.addMessageListener(new MessageListener<std_msgs.String>() {
            @Override
            public void onNewMessage(std_msgs.String message) {
                // Only the original thread that created a view hierarchy can touch its views
                // http://stackoverflow.com/questions/5161951/android-only-the-original-thread-that-created-a-view-hierarchy-can-touch-its-vi
                ViewModifier viewModifier = new ViewModifier(textView, message.getData());
                activity.runOnUiThread(viewModifier);

                if (usbService != null) {
                    usbService.write((message.getData() + "\n").getBytes());
                }
            }
        });
    }
}
