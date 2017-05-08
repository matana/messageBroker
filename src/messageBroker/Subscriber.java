package messageBroker;

import java.net.URL;

public class Subscriber {

	String name;
	URL destination;

	public Subscriber(String name, URL destination) {
		this.name = name;
		this.destination = destination;
	}
	
}
