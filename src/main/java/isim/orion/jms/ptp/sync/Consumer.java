package isim.orion.jms.ptp.sync;

import java.io.IOException;

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
  
  private final static String QUEUE_NAME = "test-queue";
  private final static String DEFAULT_HOST = "localhost";
  private Channel channel;
  private Connection connection;
  
  public Consumer() throws IOException{
    initChannel();
  }
  
  private void initChannel() throws IOException{
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(DEFAULT_HOST);
    connection = factory.newConnection();
    channel = connection.createChannel();
    
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL + C");
  }

  public String receiveMessage() throws IOException,InterruptedException{
    // callback to buffer the messages
    QueueingConsumer queueConsumer = new QueueingConsumer(channel);
    channel.basicConsume(QUEUE_NAME, true, queueConsumer);
    
    // consumer remains in suspend until message arrives
    while(true){
      QueueingConsumer.Delivery delivery = queueConsumer.nextDelivery();
      String message = new String(delivery.getBody());
      System.out.println(" [x] Received: '" + message + "'");
      return message;
    }
  }

  public void disconnect() throws IOException{
    channel.close();
    connection.close();
  }
  
  public boolean isConnected() {
    return connection.isOpen() && channel.isOpen();
  }
}
