package messageBroker;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Publisher {
	
	private ExecutorService executor = Executors.newFixedThreadPool(3);
	
	private static Publisher instance;
	
	public static Publisher getInstance() {
		if(instance == null)
			instance = new Publisher();
		return instance;
	}

	public void publish(final String message, Set<Subscriber> subscribers) {
		List<Future<String>> actions = new ArrayList<>();
		for (Subscriber subscriber : subscribers) {
			actions.add(executor.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					// TODO: send message via POST
					HttpURLConnection openConnection = (HttpURLConnection) subscriber.destination.openConnection();
					openConnection.setRequestMethod("GET");
					openConnection.connect();
					return subscriber.destination.toString() + " [responseCode:"  + openConnection.getResponseCode() + "]";
				}
			}));
		}
		
		for (Future<String> future : actions) {
			try {
				String repsonse = future.get();
				System.out.println(repsonse);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
