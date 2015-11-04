package ch.carauktion.general.couchdb;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CouchDBFileService {

	
	 
	@Inject
	private CouchDBFileDao couchDBFileDao;
	
	public CouchDBFile getCouchDBFileById(String id){
	    return   couchDBFileDao.find(id);
	}
	
	public CouchDBFile saveCouchDBFile(CouchDBFile  couchDBFile){
	    return   couchDBFileDao.saveOrUpdate(couchDBFile);
	}
	
	
	
}
