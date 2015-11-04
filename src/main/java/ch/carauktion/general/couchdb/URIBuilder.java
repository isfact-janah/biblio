package ch.carauktion.general.couchdb;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

class URIBuilder {
	private String scheme;
	private String host;
	private int port;
	private String path = "";
	private final StringBuilder query = new StringBuilder();
	private final List<String> queries = new ArrayList<String>();
	
	public static URIBuilder builder() {
		return new URIBuilder();
	}
	
	public static URIBuilder builder(URI uri) {
		URIBuilder builder = URIBuilder.builder()
	  		.scheme(uri.getScheme())
			.host(uri.getHost())
			.port(uri.getPort())
			.path(uri.getPath());
		return builder;
	}
	
	public URI build() {
		try {
			for (int i = 0; i < queries.size(); i++) {
				query.append(queries.get(i));
				if(i != queries.size() - 1) 
					query.append("&");
			}
			String q = (query.length() == 0) ? null : query.toString();
			return new URI(scheme, null, host, port, path, q, null);
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public URIBuilder scheme(String scheme) {
		this.scheme = scheme;
		return this;
	}
	
	public URIBuilder host(String host) {
		this.host = host;
		return this;
	}
	
	public URIBuilder port(int port) {
		this.port = port;
		return this;
	}
	
	public URIBuilder path(String path) {
		this.path += path;
		return this;
	}
	
	public URIBuilder query(String name, Object value) {
		if(name != null && value != null) {
			queries.add(String.format("%s=%s", name, value));
		}
		return this;
	}
	
	public URIBuilder query(String query) {
		this.query.append(query);
		return this;
	}
}
