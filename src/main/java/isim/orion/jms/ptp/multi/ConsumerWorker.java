package isim.orion.jms.ptp.multi;

import isim.orion.jms.ptp.single.Consumer;

import java.util.concurrent.Callable;

public class ConsumerWorker extends Consumer implements Callable<Integer>{

  public ConsumerWorker(String queue, String host) {
    super(queue, host);
  }

  public Integer call() throws Exception {
    return 2;
  }
}
