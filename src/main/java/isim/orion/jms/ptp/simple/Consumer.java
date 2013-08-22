package isim.orion.jms.ptp.simple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Reads messages from the message queue.
 * @author isim
 *
 */
public class Consumer {
  
  private final static String QUEUE_NAME = "default";
  private Channel channel;
  private Connection connection;
  
  private List<String> receivedMessages; 
  
  public Consumer() throws IOException{
    initChannel();
    initReceivedMessagesBuffer();
  }
  
  private void initChannel() throws IOException{
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    connection = factory.newConnection();
    channel = connection.createChannel();
    
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL + C");
  }
  
  private void initReceivedMessagesBuffer() {
    receivedMessages = new ArrayList<String>();
  }
  
  public void receiveMessage() throws IOException,InterruptedException{
    // callback to buffer the messages
    QueueingConsumer queueConsumer = new QueueingConsumer(channel);
    channel.basicConsume(QUEUE_NAME, true, queueConsumer);
    while(true){
      QueueingConsumer.Delivery delivery = queueConsumer.nextDelivery();
      String message = new String(delivery.getBody());
      System.out.println(" [x] Received: '" + message + "'");
      receivedMessages.add(message);
    }
  }
  
  public String lastReceivedMessage(){
    return receivedMessages.get(receivedMessages.size() - 1);
  }
  
  public int numReceivedMessages(){
    return receivedMessages.size();
  }
}
