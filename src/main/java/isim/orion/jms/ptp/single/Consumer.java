package isim.orion.jms.ptp.single;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Reads a single message from the message queue.
 * @author isim
 *
 */
public class Consumer {
  
  private Channel channel;
  private String queue;
  
  public Consumer(Channel channel, String queue) {
    this.channel = channel;
    this.queue = queue;
  }

  public String receiveSingleMessage() {
    try{
      // callback to buffer the messages
      QueueingConsumer queueConsumer = new QueueingConsumer(channel);
      channel.basicConsume(queue, true, queueConsumer);
      
      // consumer remains in suspend until message arrives
      QueueingConsumer.Delivery delivery = queueConsumer.nextDelivery();
      return new String(delivery.getBody());
    } catch(Exception e){
      throw new RuntimeException(e);
    }
  }

  public void disconnect() {
    try{
      channel.queuePurge(queue);
      channel.close();
    } catch(IOException e){
      throw new RuntimeException(e);
    }
  }
  
  public boolean isConnected() {
    return channel.isOpen();
  }
}
