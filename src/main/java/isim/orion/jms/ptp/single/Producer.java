package isim.orion.jms.ptp.single;

import java.util.List;


/**
 * Sends a single message to the message queue.
 * @author isim
 *
 */
public class Producer {
  
  private Tunnel tunnel;
  
  public Producer(String queue, String host) {
    this.tunnel = Tunnel.newInstance(queue, host);
  }
  
  public void send(String message) {
    tunnel.publish(message);
  }
  
  public void send(List<String> messages) {
    for(String message : messages)
      send(message);
  }
  
  public boolean isConnected() {
    if(tunnel == null)
      return false;
    return tunnel.isOpen();
  }
  
  public void disconnect() {
    if(tunnel != null)
      tunnel.close();
  }

  public void purgeQueue() {
    tunnel.purgeQueue();
  }
}
