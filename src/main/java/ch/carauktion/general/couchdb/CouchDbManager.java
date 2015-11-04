package ch.carauktion.general.couchdb;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;


@Named
@ApplicationScoped
public class CouchDbManager  extends CouchDbManagerBase{
	
	CouchDbConnector couchDbConnector ;
	
	// -------------------------------------------------------------------------- Constructors
	/**
	 * Constructs a new instance of this class, expects a configuration file named 
	 * <code>couchdb.properties</code> to be available in your application classpath.
	 */
	public CouchDbManager() {
		super();
	}
	
	
	@PostConstruct
	public void init(){
		System.out.println("****************************************init Couch db *********************************");
		 HttpClient httpClient = new StdHttpClient.Builder().host(config.getHost())
					.port(config.getPort()).username(config.getUsername()).password(config.getPassword()).build();

			CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
			couchDbConnector = new StdCouchDbConnector(config.getDbName(), dbInstance);
			if(config.isCreateDbIfNotExist()){
			couchDbConnector.createDatabaseIfNotExists();
			}
	}

	
	public CouchDbConnector getCouchDbConnector() {
		return couchDbConnector;
	}

	public void setCouchDbConnector(CouchDbConnector couchDbConnector) {
		this.couchDbConnector = couchDbConnector;
	}


}
