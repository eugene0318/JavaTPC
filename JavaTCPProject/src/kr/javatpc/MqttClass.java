package kr.javatpc;

import java.util.UUID;
import org.eclipse.paho.client.mqttv3.*;
public class MqttClass implements MqttCallback{
	private MqttClient client = null;
	public MqttClass() {
		new Thread(task1).start();
	}
	private ReceiveEventListner listner = null; //GUI
	
    Runnable task1 = new Runnable() {
    	 @Override
    	 public void run() {
    		 try {
				String clientId = UUID.randomUUID().toString();
				//new MqttClient()
				client =new MqttClient("tcp://192.168.35.252:1883", clientId);
				MqttConnectOptions connopt = new MqttConnectOptions();
				connopt.setCleanSession(true);
				client.connect(connopt);
				client.setCallback(MqttClass.this);
				client.subscribe("dht11"); //수신자 설정
				
				new IoTFrame(MqttClass.this); //GUI
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
    	 }
    };
    public void sendMessage(String payload) {
    	MqttMessage message = new MqttMessage();
    	message.setPayload(payload.getBytes());
    	try {
			if(client.isConnected()) {
				client.publish("led", message);
			}
		} catch (MqttException e) {
			// TODO: handle exception
			System.out.println("error1"+e.getStackTrace()+e.getMessage());
		}
    }
	@Override
	public void connectionLost(Throwable arg0) {
		try {
			System.out.println("disconnect");
			client.close();
		} catch (MqttException e) {
			// TODO: handle exception
			System.out.println("error" + e.getMessage());
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
		
	}
	public void setMyEventListener(ReceiveEventListner listner) {
		this.listner = listner;
	}
	@Override
	public void messageArrived(String topic, MqttMessage msg) throws Exception {
		// TODO Auto-generated method stub
		listner.recvMsg(topic, msg); //GUI 쪽의 recvMsg
	}
    
    
    
}
