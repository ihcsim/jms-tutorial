package isim.orion.jms.ptp.single;

import java.util.List;


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
  
  public String receive() {
    return tunnel.receive();
  }

  public List<String> receive(int timeout) {
    return tunnel.receive(timeout);
  }

  public void disconnect() {
    tunnel.disconnect();
  }
  
  public boolean isConnected() {
    return tunnel.isOpen();
  }

  public void purgeQueue() {
    tunnel.purgeQueue();
  }
}
