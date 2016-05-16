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
public class UsbMessageReceiver extends AbstractMessageReceiver {
    protected Context context;
    protected TextView textView;

    UsbMessageReceiver(Context context, TextView textView, String topic) {
        super(topic);

        this.context = context;
        this.textView = textView;
    }

    UsbMessageReceiver(Context context, TextView textView) {
        super("arduino_output");

        this.context = context;
        this.textView = textView;
    }

    protected void send(String message) {
        if (publisher != null) {
            std_msgs.String str = (std_msgs.String)publisher.newMessage();
            str.setData(message);
            publisher.publish(str);
        }
        else {
            Toast.makeText(context, "publisher is null, " + message + " lost.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void showInTextView(String message) {
        List<String> lines = new ArrayList<String>();
        lines.add(message);
        lines.addAll(Arrays.asList(textView.getText().toString().split("\n", 5)));
        textView.setText(StringUtils.join(lines, "\n"));
    }

    @Override
    public void receive(String message) {
        showInTextView(message);

        send(message);
    }
}
