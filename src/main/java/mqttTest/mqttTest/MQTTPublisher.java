package mqttTest.mqttTest;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/*
 * 发布
 */
public class MQTTPublisher {
    public static void publish(){
        try {
            MqttClient client = new MqttClient("tcp://127.0.0.1:61613","mqttserver-pub");
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("admin");
            options.setPassword("password".toCharArray());
            options.setCleanSession(false);
            options.setAutomaticReconnect(true);
            client.connect(options);
//            MqttTopic topic = client.getTopic("msg.topic");/public/TEST/windows
            MqttTopic topic = client.getTopic("/public/TEST/windows");
            MqttMessage message = new MqttMessage("Hello World".getBytes());
            message.setQos(1);
            while(true){
                Thread.sleep(1000);
                MqttDeliveryToken token = topic.publish(message);
                while (!token.isComplete()){
                    token.waitForCompletion(1000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
