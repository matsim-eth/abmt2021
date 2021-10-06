package abmt2021.lectures.week3.injection;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;


public class LinkModule extends AbstractModule{	
	
	protected void configure() {
		// in this case Link object needs to have a constructor 
		// annotated with @Inject
		// if the consturctor has dependencies, then those
		// need to be provided to the Guice framework
		//bind(Link.class).asEagerSingleton();
		
		// here we do not need an @Inject annotation in Link
		// as we are directly binding an instance of the object
		
		//bind(Link.class).toInstance(new Link());
		
		// Providers provide us with the flexibility to 
		// provide configurable objects
		
		String name = new String("X");
		bind(String.class).annotatedWith(Names.named("name")).toInstance(name);
		
		// in this case we need an @Inject annotation in the LinkProvider class.
		// Guice will in this case try to create a NetworkGuice (in the RunScript), will realize it needs a Link
		// then it will go to the LinkProvider and realize it needs a String named "name"
		// this will be a dependency tree. String annotated with "name" will be created first.
		bind(Link.class).toProvider(LinkProvider.class);
		
		// in this case @Inject annotation is not used so it is not needed
		// in the LinkProvider. We create the LinkProvider instance directly here
		//bind(Link.class).toProvider(new LinkProvider(name));
	}

}
