package isim.orion.jms.ptp.single;

import com.rabbitmq.client.Channel;

public class Tunnel {
  
  private Channel rabbitMQChannel;
  
  public Tunnel(String queue, String host){
    rabbitMQChannel = ChannelFactory.open(queue, host);
  }
}
