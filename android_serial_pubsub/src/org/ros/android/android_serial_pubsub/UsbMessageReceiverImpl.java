package org.ros.android.android_serial_pubsub;

import android.widget.TextView;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by peter on 2016.05.15..
 */
public class UsbMessageReceiverImpl implements UsbMessageReceiver {
    @Override
    public void receive(TextView textView, String message) {
        List<String> lines = new ArrayList<String>();
        lines.add(message);
        lines.addAll(Arrays.asList(textView.getText().toString().split("\n", 5)));
        textView.setText(StringUtils.join(lines, "\n"));
    }
}
