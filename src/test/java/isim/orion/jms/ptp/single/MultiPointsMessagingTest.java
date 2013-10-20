package isim.orion.jms.ptp.single;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class MultiPointsMessagingTest {
  
  private static final String DEFAULT_HOST = "localhost";
  private final static String QUEUE_NAME = "test-queue";

  @Test
  public void canSendToMultiPointsTest(){
    try{
      Producer producer = new Producer(QUEUE_NAME, DEFAULT_HOST);
      
      for(String message : generateMultipleFakeMessages())
        producer.send(message);
      
//      int numExpectedMsgs = messages.size();
//      for(int i = 0; i < numExpectedMsgs; i++) {
      Consumer consumer = new Consumer(QUEUE_NAME, DEFAULT_HOST);
      System.out.println("Message Received: " + consumer.receive());
      consumer.disconnect();
//     }
      producer.disconnect();
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail("Fail to send messages to multiple consumers. " + e.getMessage());
    }
  }

  private List<String> generateMultipleFakeMessages() {
    List<String> messages = new ArrayList<String>();
    messages.add("Message One");
    messages.add("Message Two");
    messages.add("Message Three");
    messages.add("Message Four");
    messages.add("Message Five"); 
    messages.add("Message Six");
    messages.add("Message Seven");
    messages.add("Message Eight");
    messages.add("Message Nine");
    messages.add("Message Ten");
    return messages;
  }
}
