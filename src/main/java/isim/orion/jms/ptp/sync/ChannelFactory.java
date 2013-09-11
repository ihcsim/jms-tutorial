package isim.orion.jms.ptp.sync;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Connection to a message queue.
 * @author isim
 *
 */
public class ChannelFactory {

  /**
   * Open a connection channel to the specified message queue.
   * @param queue Name of the message queue.
   * @param host Hostname of the entity hosting the message queue.
   */
  public static Channel open(String queue, String host) throws IOException{
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(host);
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    channel.queueDeclare(queue, false, false, false, null);
    return channel;
  }
}