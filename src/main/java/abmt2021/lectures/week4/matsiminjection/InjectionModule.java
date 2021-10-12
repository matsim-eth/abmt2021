package abmt2021.lectures.week4.matsiminjection;

import org.matsim.core.controler.AbstractModule;

import com.google.inject.name.Names;

import abmt2021.lectures.week3.injection.Link;
import abmt2021.lectures.week3.injection.LinkProvider;
import abmt2021.lectures.week3.injection.NetworkGuice;

public class InjectionModule extends AbstractModule{

	@Override
	public void install() {
		// we want to create a String object annotated with "name" that should be passed
		// to the injection framework
		String name = new String("X");
		bind(String.class).annotatedWith(Names.named("name")).toInstance(name);
		// we want to provide a Link object using a provider LinkProvider
		bind(Link.class).toProvider(LinkProvider.class);
		// we want to bind a NetworkGuice object and ensure that only one instance is created
		bind(NetworkGuice.class).asEagerSingleton();
		
	}

}
