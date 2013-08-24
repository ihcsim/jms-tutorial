package isim.orion.jms.ptp.sync;

import java.io.IOException;

import com.rabbitmq.client.Channel;

/**
 * Sends messages to the message queue.
 * @author isim
 *
 */
public class Producer {
  
  private Channel channel;
  private String queue;
  
  public Producer(Channel channel, String queue) throws IOException{
    this.channel = channel;
    this.queue = queue;
  }
  
  public void sendMessage(String message) throws IOException{
    channel.basicPublish("", queue, null, message.getBytes());
    disconnect();
  }
  
  public boolean isConnected(){
    if(channel == null)
      return false;
    return channel.isOpen();
  }
  
  public void disconnect() throws IOException{
    if(channel != null)
      channel.close();
  }
}
