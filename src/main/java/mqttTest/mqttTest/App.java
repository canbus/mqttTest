package mqttTest.mqttTest;

import java.io.IOException;
import java.nio.channels.NonWritableChannelException;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		new Thread(new Runnable() {

			public void run() {
				MQTTSubsribe mqttSubsribe = new MQTTSubsribe();
				mqttSubsribe.subsribe();
			}
		}).start();
		System.out.println("start");
		MQTTPublisher mqttPublisher = new MQTTPublisher();
		mqttPublisher.publish();

	}
}
