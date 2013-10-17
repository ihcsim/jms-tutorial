package isim.orion.jms.ptp.single;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InitTunnelTest {

  private static final String queue = "test-queue";
  private static final String host = "localhost";
  private Tunnel tunnel;
  
  @Before
  public void setUp(){
    tunnel = new Tunnel(queue, host);
  }
  
  @Test
  public void canCreateTunnelTest() {
    Assert.assertNotNull(tunnel);
  }
}
