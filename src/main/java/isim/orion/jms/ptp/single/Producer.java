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
  
  public void sendMessage(String message) throws IOException {
    if(message == null)
      throw new IllegalArgumentException();
    channel.basicPublish("", queue, null, message.getBytes());
  }
  
  public boolean isConnected() {
    if(channel == null)
      return false;
    return channel.isOpen();
  }
  
  public void disconnect() throws IOException {
    if(channel != null)
      channel.close();
  }
}
