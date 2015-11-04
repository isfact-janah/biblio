package ch.carauktion.general.couchdb;

import java.io.Serializable;

import javax.inject.Inject;

public abstract class CouchDBBaseDao<T extends CouchDbBaseEntity> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CouchDbManager cm;

	protected abstract Class<T> getDomainClass();

	public void create(T entity) {
		cm.getCouchDbConnector().create(entity);
	}

	public T find(String id) {
		return cm.getCouchDbConnector().find(getDomainClass(), id);
	}

	public T update(T entity) {
		cm.getCouchDbConnector().update(entity);
		return entity;
	}

	public void delete(T entity) {
		cm.getCouchDbConnector().delete(entity);
	}

	public T saveOrUpdate(T entity) {
		if (entity == null) {
			return null;
		}

		if (entity.isNewEntity()) {
			create(entity);
		} else {
			entity = update(entity);
		}

		return entity;
	}

	
}
