package mqttTest.mqttTest;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

/*
 * 订阅
 */
public class MQTTSubsribe {
    public static String subsribe() {
        try {
            //创建MqttClient
            MqttClient client = new MqttClient("tcp://127.0.0.1:61613", "java_client23432ere");
            //创建连接可选项信息
            MqttConnectOptions conOptions = new MqttConnectOptions();
            //
            conOptions.setUserName("admin");
            conOptions.setPassword("password".toCharArray());
            conOptions.setCleanSession(false);
            conOptions.setAutomaticReconnect(true);
            //回调处理类
            CallBack callback = new CallBack(client,conOptions);
            client.setCallback(callback);
            //连接broker
            client.connect(conOptions);
            //发布相关的订阅
            String[] topic = {"msg.topic","dance.topic","/public/TEST/#"};
            int[] qos = {1,1,1};
            client.subscribe(topic, qos);
            //client.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }
}
