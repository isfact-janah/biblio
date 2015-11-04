package ch.carauktion.general.couchdb;

import java.net.URI;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.protocol.BasicHttpContext;


public class CouchDbManagerBase {
	
private static final Log log = LogFactory.getLog(CouchDbManagerBase.class);
	
	private HttpClient httpClient;
	private URI baseURI;
	private URI dbURI;
	protected CouchDbConfig config;
	
	private HttpHost host;
	private BasicHttpContext context;
	private Properties properties = new Properties();
	
	
	
	protected CouchDbManagerBase() {
		this(new CouchDbConfig());
	}
	
	protected CouchDbManagerBase(CouchDbConfig config) {
		this.config = config;
		baseURI = URIBuilder.builder().scheme(config.getProtocol()).host(config.getHost()).port(config.getPort()).path("/").build();
		dbURI   = URIBuilder.builder(baseURI).path(config.getDbName()).path("/").build();
	}
	
	
	
	
	public HttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public URI getBaseURI() {
		return baseURI;
	}

	public void setBaseURI(URI baseURI) {
		this.baseURI = baseURI;
	}

	public URI getDbURI() {
		return dbURI;
	}

	public void setDbURI(URI dbURI) {
		this.dbURI = dbURI;
	}

	public CouchDbConfig getConfig() {
		return config;
	}

	public void setConfig(CouchDbConfig config) {
		this.config = config;
	}

	public HttpHost getHost() {
		return host;
	}

	public void setHost(HttpHost host) {
		this.host = host;
	}

	public BasicHttpContext getContext() {
		return context;
	}

	public void setContext(BasicHttpContext context) {
		this.context = context;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	
	
	
}
