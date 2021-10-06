package abmt2021.lectures.week3.live;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class LinkProvider implements Provider<Link> {

	private String name;

	@Inject
	public LinkProvider(@Named("name") String name) {
		this.name = name;
	}

	// this method will be called when the program needs a Link object
	// it allows to return different instances of the Link object
	// depending on your configuration
	public Link get() {
		if (name.equals("Zurich"))
			return new Link(name);
		else
			return new Link("Lugano");
	}
}
