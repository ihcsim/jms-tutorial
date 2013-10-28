package isim.orion.jms.ptp.multi;

import isim.orion.jms.ptp.single.Producer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Test;

public class MultiPointsMessagingTest {
  
  private static final String DEFAULT_HOST = "localhost";
  private final static String QUEUE_NAME = "test-queue";
  private static final int CONSUMERS_COUNT = 5;

  @Test
  public void canSendToMultiPointsTest(){
    try{
      Producer producer = new Producer(QUEUE_NAME, DEFAULT_HOST);
      List<String> messages = generateMultipleFakeMessages();
      for(String message : messages)
        producer.send(message);

      ExecutorService pool = Executors.newFixedThreadPool(CONSUMERS_COUNT);
      List<Future<Integer>> totalNumMessages = new ArrayList<Future<Integer>>();
      for(int i = 0; i < CONSUMERS_COUNT; i++){
        Future<Integer> numMessages = pool.submit(new ConsumerWorker(QUEUE_NAME, DEFAULT_HOST));
        totalNumMessages.add(numMessages);
      }
      
      int actualNumMessages = 0;
      for(Future<Integer> numMessages : totalNumMessages)
        actualNumMessages += numMessages.get();
      int expectedNumMessages = messages.size();
      
      Assert.assertEquals(expectedNumMessages, actualNumMessages);
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
