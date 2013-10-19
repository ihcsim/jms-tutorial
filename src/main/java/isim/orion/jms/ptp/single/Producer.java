package isim.orion.jms.ptp.single;


/**
 * Sends a single message to the message queue.
 * @author isim
 *
 */
public class Producer {
  
  private Tunnel tunnel;
  
  public Producer(Tunnel tunnel, String queue) {
    this.tunnel = tunnel;
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
