package abmt2021.lectures.week3.live;

import com.google.inject.Inject;

public class NetworkGuice {
	
	private Link link;
	
	@Inject
	public NetworkGuice(Link link) {
		this.link = link;
	}
	
	public Link getLink() {
		return this.link;
	}
}


