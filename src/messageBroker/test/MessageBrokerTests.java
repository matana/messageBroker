package messageBroker.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import messageBroker.MessageBroker;
import messageBroker.Subscriber;
import messageBroker.MessageBroker.Topic;

public class MessageBrokerTests {

	private MessageBroker messageBroker;

	@Before
	public void init() throws MalformedURLException {
		messageBroker = new MessageBroker();
	}
	
	@Test
	public void subscribe() throws MalformedURLException {
		messageBroker.addSubscriber(new Subscriber("google", new URL("https://google.com")), Topic.GOSSIP);
		messageBroker.addSubscriber(new Subscriber("bing", new URL("https://www.bing.com")), Topic.GOSSIP);
		messageBroker.addSubscriber(new Subscriber("yahoo", new URL("https://www.yahoo.com/")), Topic.GOSSIP);
		messageBroker.addSubscriber(new Subscriber("myBet", new URL("https://mybet.com")), Topic.SPORT);
		messageBroker.addSubscriber(new Subscriber("nyt", new URL("https://www.nytimes.com/")), Topic.POLITICS);
		messageBroker.addSubscriber(new Subscriber("nyt", new URL("https://www.nytimes.com/")), Topic.GOSSIP);
		messageBroker.addSubscriber(new Subscriber("nyt", new URL("https://www.nytimes.com/")), Topic.ECONOMY);
		Assert.assertEquals(7, messageBroker.subSize());
		Assert.assertEquals(4, messageBroker.subSizePerTopic(Topic.GOSSIP));
		Assert.assertEquals(1, messageBroker.subSizePerTopic(Topic.ECONOMY));
		Assert.assertEquals(1, messageBroker.subSizePerTopic(Topic.SPORT));
		Assert.assertEquals(1, messageBroker.subSizePerTopic(Topic.POLITICS));
	}
	
	@Test
	public void receive() throws MalformedURLException {
		messageBroker.addSubscriber(new Subscriber("google", new URL("https://google.com")), Topic.GOSSIP);
		messageBroker.addSubscriber(new Subscriber("bing", new URL("https://www.bing.com")), Topic.GOSSIP);
		messageBroker.addSubscriber(new Subscriber("yahoo", new URL("https://www.yahoo.com/")), Topic.GOSSIP);
		messageBroker.addSubscriber(new Subscriber("myBet", new URL("https://mybet.com")), Topic.SPORT);
		messageBroker.addSubscriber(new Subscriber("nyt", new URL("https://www.nytimes.com/")), Topic.POLITICS);
		messageBroker.addSubscriber(new Subscriber("nyt", new URL("https://www.nytimes.com/")), Topic.GOSSIP);
		messageBroker.addSubscriber(new Subscriber("nyt", new URL("https://www.nytimes.com/")), Topic.ECONOMY);
		messageBroker.receive("Test test test...", Topic.GOSSIP);
	}
	
	@Test
	public void removeSubcription() throws MalformedURLException {
		messageBroker.addSubscriber(new Subscriber("google", new URL("https://google.com")), Topic.GOSSIP);
		messageBroker.addSubscriber(new Subscriber("bing", new URL("https://www.bing.com")), Topic.GOSSIP);
		messageBroker.addSubscriber(new Subscriber("yahoo", new URL("https://www.yahoo.com/")), Topic.GOSSIP);
		messageBroker.addSubscriber(new Subscriber("myBet", new URL("https://mybet.com")), Topic.SPORT);
		messageBroker.addSubscriber(new Subscriber("nyt", new URL("https://www.nytimes.com/")), Topic.POLITICS);
		messageBroker.addSubscriber(new Subscriber("nyt", new URL("https://www.nytimes.com/")), Topic.GOSSIP);
		messageBroker.addSubscriber(new Subscriber("nyt", new URL("https://www.nytimes.com/")), Topic.ECONOMY);
		messageBroker.addSubscriber(new Subscriber("nyt", new URL("https://www.nytimes.com/")), Topic.SPORT);
		Assert.assertEquals(8, messageBroker.subSize());
		Assert.assertEquals(4, messageBroker.subSizePerTopic(Topic.GOSSIP));
		Assert.assertEquals(1, messageBroker.subSizePerTopic(Topic.ECONOMY));
		Assert.assertEquals(2, messageBroker.subSizePerTopic(Topic.SPORT));
		Assert.assertEquals(1, messageBroker.subSizePerTopic(Topic.POLITICS));
		messageBroker.removeSubscription("nyt", Topic.SPORT);
		Assert.assertEquals(7, messageBroker.subSize());
		Assert.assertEquals(1, messageBroker.subSizePerTopic(Topic.SPORT));
	}
}
