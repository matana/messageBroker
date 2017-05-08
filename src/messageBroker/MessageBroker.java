package messageBroker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MessageBroker {
	
	private Map<Topic, Set<Subscriber>> map;
	
	public enum Topic { SPORT, ECONOMY, POLITICS, GOSSIP }
	
	public MessageBroker() {
		map = new HashMap<>();
		map.put(Topic.SPORT, new HashSet<>());
		map.put(Topic.ECONOMY, new HashSet<>());
		map.put(Topic.POLITICS, new HashSet<>());
		map.put(Topic.GOSSIP, new HashSet<>());
	}
	
	public String topicsAvailable() {
		// TODO: RESTful
		return map.keySet().toString();
	}
	
	public void receive(String message, Topic topic) {
		Publisher.getInstance().publish(message, map.get(topic));
	}
	
	public boolean addSubscriber(Subscriber sub, Topic topic) {
		Set<Subscriber> subscribers = map.get(topic);
		if(!subscribers.contains(sub)) {
			return subscribers.add(sub);
		}
		return false;
	}

	public int subSize() {
		int count = 0;
		for (Topic topic : map.keySet()) {
			count  += map.get(topic).size();
		}
		return count;
	}
	
	public int subSizePerTopic(Topic topic) {
		return map.get(topic).size();
	}

	public boolean removeSubscription(final String name, Topic topic) {
		return map.get(topic).removeIf(x -> x.name.equalsIgnoreCase(name));
	}

}
