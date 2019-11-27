package mqttTest.mqttTest;

import java.util.Date;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class CallBack implements MqttCallback {
	 
    private MqttClient client;
    private MqttConnectOptions options;
 
    public CallBack() {
    }
 
    public CallBack(MqttClient client, MqttConnectOptions options) {
        this.client = client;
        this.options = options;
    }
    //方法实现说明 断线重连方法，如果是持久订阅，重连是不需要再次订阅，如果是非持久订阅，重连是需要重新订阅主题 取决于options.setCleanSession(true); true为非持久订阅
    public void connectionLost(Throwable cause) {
       //失败重连逻辑
        while (true){
            try {
                System.out.println("连接失败重连");
                client.connect(options);
                //发布相关的订阅
                String[] topic = {"msg.topic","dance.topic"};
                int[] qos = {1,1};
                client.subscribe(topic, qos);
                System.out.println("连接失败重连成功");
                break;
            } catch (MqttException e) {
                e.printStackTrace();
                System.out.println("连接失败重连失败");
            }
        }
    }
 
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println(new Date());
        System.out.println("public void messageArrived(String s, MqttMessage mqttMessage)");
        System.out.println(s);
        System.out.println(new String(mqttMessage.getPayload(),"UTF-8"));
    }
 
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken)");
    }
}
