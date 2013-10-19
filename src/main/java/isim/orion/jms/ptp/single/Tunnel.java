package isim.orion.jms.ptp.single;

import java.io.IOException;

import com.rabbitmq.client.Channel;

public class Tunnel {
  
  private Channel rabbitMQChannel;
  private String queue;
  
  public static Tunnel newInstance(String queue, String host){
    return new Tunnel(queue, host);
  }
  
  private Tunnel(String queue, String host){
    rabbitMQChannel = ChannelFactory.open(queue, host);
    this.queue = queue;
  }

  public void publish(String message) {
    try {
      if(message == null)
        throw new IllegalArgumentException();
      rabbitMQChannel.basicPublish("", queue, null, message.getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean isOpen() {
    return rabbitMQChannel.isOpen();
  }

  public void close() {
    try {
      rabbitMQChannel.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
