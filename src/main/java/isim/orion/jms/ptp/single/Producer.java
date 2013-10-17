package isim.orion.jms.ptp.single;

import java.io.IOException;

import com.rabbitmq.client.Channel;

/**
 * Sends a single message to the message queue.
 * @author isim
 *
 */
public class Producer {
  
  private Channel channel;
  private String queue;
  
  public Producer(Channel channel, String queue) {
    this.channel = channel;
    this.queue = queue;
  }
  
  public void sendSingleMessage(String message) {
    try{
      if(message == null)
        throw new IllegalArgumentException();
      channel.basicPublish("", queue, null, message.getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  public boolean isConnected() {
    if(channel == null)
      return false;
    return channel.isOpen();
  }
  
  public void disconnect() {
    try {
      if(channel != null)
        channel.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
