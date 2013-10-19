package isim.orion.jms.ptp.single;


/**
 * Reads a single message from the message queue.
 * @author isim
 *
 */
public class Consumer {
  
  private Tunnel tunnel;
  
  public Consumer(String queue, String host) {
    this.tunnel = Tunnel.newInstance(queue, host);
  }
  
  public Consumer(Tunnel tunnel, String queue){
    this.tunnel = tunnel;
  }

  public String receiveSingleMessage() {
    return tunnel.receive();
  }

  public void disconnect() {
    tunnel.disconnect();
  }
  
  public boolean isConnected() {
    return tunnel.isOpen();
  }
}
