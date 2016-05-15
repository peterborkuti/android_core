package org.ros.android.android_serial_pubsub;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang.StringUtils;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;
import org.ros.node.topic.Publisher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by peter on 2016.05.15..
 */
public class UsbMessageReceiverImpl extends AbstractNodeMain implements UsbMessageReceiver {
    private Publisher publisher;
    private Context context;

    UsbMessageReceiverImpl(Context context) {
        this.context = context;
    }

    public void onStart(ConnectedNode connectedNode) {
        publisher = connectedNode.newPublisher(ROS_OUTPUT_TOPIC, "std_msgs/String");
    }

    public GraphName getDefaultNodeName() {
        return GraphName.of("android_serial_pubsub/" + ROS_OUTPUT_TOPIC);
    }

    private void send(String message) {
        if (publisher != null) {
            std_msgs.String str = (std_msgs.String)publisher.newMessage();
            str.setData(message);
            publisher.publish(str);
            Toast.makeText(context, message + " was sent", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "publisher is null!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void receive(TextView textView, String message) {
        List<String> lines = new ArrayList<String>();
        lines.add(message);
        lines.addAll(Arrays.asList(textView.getText().toString().split("\n", 5)));
        textView.setText(StringUtils.join(lines, "\n"));

        send(message);
    }
}
