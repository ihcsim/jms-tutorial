package isim.orion.jms.ptp.single;


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
  
  public boolean isConnected() {
    if(tunnel == null)
      return false;
    return tunnel.isOpen();
  }
  
  public void disconnect() {
    if(tunnel != null)
      tunnel.close();
  }
}
