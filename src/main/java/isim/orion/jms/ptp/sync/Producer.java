package isim.orion.jms.ptp.sync;

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

  private final static String QUEUE_NAME = "test-queue";
  private final static String DEFAULT_HOST = "localhost";
  private Channel channel;
  private Connection connection;
  
  public Producer() throws IOException{
    initChannel();
  }
  
  private void initChannel() throws IOException{
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(DEFAULT_HOST);
    connection = factory.newConnection();
    channel = connection.createChannel();
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
  }
  
  public void sendMessage(String message) throws IOException{
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    System.out.println(" [x] Sent '" + message + "'");
    disconnect();
  }
  
  public boolean isConnected(){
    return connection.isOpen() && channel.isOpen();
  }
  
  public void disconnect() throws IOException{
    channel.close();
    connection.close();
  }
}
