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
  private Channel channel;
  private Connection connection;
  
  public Producer() throws IOException{
    initChannel();
  }
  
  public void sendMessage(String message) throws IOException{
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    System.out.println(" [x] Sent '" + message + "'");
    cleanUp();
  }
  
  private void initChannel() throws IOException{
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    connection = factory.newConnection();
    channel = connection.createChannel();
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
  }
  
  private void cleanUp() throws IOException{
    if(channel != null)
      channel.close();
    if(connection != null)
      connection.close();
  }
}
