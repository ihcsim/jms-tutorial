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
  private String queue;
  
  public Consumer(Channel channel, String queue) throws IOException{
    this.channel = channel;
    this.queue = queue;
  }

  public String receiveMessage() throws IOException,InterruptedException{
    // callback to buffer the messages
    QueueingConsumer queueConsumer = new QueueingConsumer(channel);
    channel.basicConsume(queue, true, queueConsumer);
    
    // consumer remains in suspend until message arrives
    QueueingConsumer.Delivery delivery = queueConsumer.nextDelivery();
    return new String(delivery.getBody());
  }

  public void disconnect() throws IOException{
    channel.close();
  }
  
  public boolean isConnected() {
    return channel.isOpen();
  }
}
