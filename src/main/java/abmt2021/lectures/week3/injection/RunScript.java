package abmt2021.lectures.week3.injection;

import com.google.inject.Guice;
import com.google.inject.Injector;


public class RunScript {
	
	
	public static void main(String[] args) {
		
		
		Injector injector = Guice.createInjector(new LinkModule());
		
		NetworkGuice network = injector.getInstance(NetworkGuice.class);
		System.out.println(network.getLink().hashCode());	
		
	}

}
