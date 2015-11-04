package ch.carauktion.general.couchdb;

import javax.ejb.Stateless;


@Stateless
public class CouchDBFileDao  extends CouchDBBaseDao<CouchDBFile>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<CouchDBFile> getDomainClass() {
		return CouchDBFile.class;
	}

}
