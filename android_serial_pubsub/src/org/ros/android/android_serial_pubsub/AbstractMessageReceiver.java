package org.ros.android.android_serial_pubsub;

import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Publisher;

/**
 * Created by peter on 2016.05.15..
 */
public abstract class AbstractMessageReceiver extends AbstractNodeMain implements MessageReceiver {
    public final String ROS_TOPIC;
    public final String ROS_MSG_TYPE;
    public final String APP_NAME;

    protected Publisher publisher;

    public AbstractMessageReceiver(String topicName) {
        this("android_serial_pubsub", topicName);
    }

    public AbstractMessageReceiver(String appName, String topicName) {
        this(appName, topicName, "std_msgs/String");
    }

    public AbstractMessageReceiver(String appName, String topicName, String msgType) {
        ROS_TOPIC = topicName;
        ROS_MSG_TYPE = msgType;
        APP_NAME = appName;
    }

    public void onStart(ConnectedNode connectedNode) {
        publisher = connectedNode.newPublisher(ROS_TOPIC, ROS_MSG_TYPE);
    }

    public GraphName getDefaultNodeName() {
        return GraphName.of(APP_NAME + "/" + ROS_TOPIC);
    }
}
