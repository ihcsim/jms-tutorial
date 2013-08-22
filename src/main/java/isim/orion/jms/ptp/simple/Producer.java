package isim.orion.jms.ptp.simple;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Sends messages to the message queue.
 * @author isim
 *
 */
public class Producer {

  private final static String QUEUE_NAME = "hello";
  
  public boolean sendMessage() throws IOException{
    
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    String message = "Hello World";
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    System.out.println(" [x] Sent '" + message + "'");
    channel.close();
    connection.close();
    return true;
  }
}
