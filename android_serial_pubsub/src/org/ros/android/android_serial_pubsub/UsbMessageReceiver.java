package org.ros.android.android_serial_pubsub;

import android.app.Activity;
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
    protected Activity activity;

    protected UsbMessageReceiver(Activity activity, Context context, TextView textView, String topic, String nodeName) {
        super(nodeName, topic);

        this.context = context;
        this.textView = textView;
        this.activity = activity;
    }

    public UsbMessageReceiver(Activity activity, Context context, TextView textView) {
        this(activity, context, textView, "fromArduino", "usbreceiver");
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

    protected class ViewModifier implements Runnable {
        private TextView view;
        private String message;
        ViewModifier(TextView view, String message) {
            this.message = message;
            this.view = view;
        }

        @Override
        public void run() {
            List<String> lines = new ArrayList<String>();
            lines.add(message);
            lines.addAll(Arrays.asList(view.getText().toString().split("\n", 5)));
            String line = StringUtils.join(lines, "\n");
            view.setText(line);
        }
    }

    @Override
    public void receive(String message) {
        // Only the original thread that created a view hierarchy can touch its views
        // http://stackoverflow.com/questions/5161951/android-only-the-original-thread-that-created-a-view-hierarchy-can-touch-its-vi
        ViewModifier viewModifier = new ViewModifier(textView, message);
        activity.runOnUiThread(viewModifier);

        send(message);
    }
}
