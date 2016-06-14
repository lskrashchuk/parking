package by.lskrashchuk.training.parking.dataaccess.impl;

import java.awt.Event;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.lskrashchuk.training.parking.dataaccess.PlaceDao;
import by.lskrashchuk.training.parking.dataaccess.filters.PlaceFilter;
import by.lskrashchuk.training.parking.datamodel.Place;
import by.lskrashchuk.training.parking.datamodel.Place_;
import by.lskrashchuk.training.parking.datamodel.Registry;
import by.lskrashchuk.training.parking.datamodel.Registry_;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.datamodel.User_;

@Repository
public class PlaceDaoImpl extends AbstractDaoImpl<Place, Long> implements PlaceDao{

	protected PlaceDaoImpl() {
		super(Place.class);
	}

	@Override
	public Long count(PlaceFilter filter) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Place> from = cq.from(Place.class);
        cq.select(cb.count(from));
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult();
	}

	@Override
	public List<Place> find(PlaceFilter filter) {
		EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Place> cq = cb.createQuery(Place.class);

        Root<Place> from = cq.from(Place.class);

        // set selection
        cq.select(from);

        if (filter.getPlaceNumber() != null) {
            Predicate fNameEqualCondition = cb.equal(from.get(Place_.number), filter.getPlaceNumber());
            cq.where(fNameEqualCondition);
        }
        // set fetching
 //       if (filter.isFetchCredentials()) {
 //           from.fetch(User_.credentials, JoinType.LEFT);
 //       }

        // set sort params
        if (filter.getSortProperty() != null) {
            cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
        }

        TypedQuery<Place> q = em.createQuery(cq);

        // set paging
        if (filter.getOffset() != null && filter.getLimit() != null) {
            q.setFirstResult(filter.getOffset());
            q.setMaxResults(filter.getLimit());
        }

        // set execute query
        List<Place> allitems = q.getResultList();
        return allitems;
	}

	@Override
	public Boolean isBasy(Place place) {
	
		EntityManager em = getEntityManager();
//		Query query = em.createQuery("SELECT FIRST r.event_type FROM Registry r LEFT JOIN Place p ON r.place_id=p.id WHERE p.id="+place.getId()+" ORDER BY r.event_time DESC");
		List queryResult  = em.createNativeQuery("SELECT r.event_type FROM registry r LEFT JOIN place p ON r.place_id=p.id WHERE p.id="+place.getId()+" ORDER BY r.event_time DESC LIMIT 1").getResultList(); 

		if (!queryResult.isEmpty()) {
			Integer eventType = (Integer)queryResult.get(0); 
	
			return (eventType == 0);
		}
			else return false;
	}

	@Override
	public Integer countNotBuzy() {
		EntityManager em = getEntityManager();
		List queryResult  = em.createNativeQuery("SELECT r.event_type FROM registry r LEFT JOIN place p ON r.place_id=p.id WHERE p.id="+place.getId()+" ORDER BY r.event_time DESC LIMIT 1").getResultList(); 
		if (!queryResult.isEmpty()) {
			Integer eventType = (Integer)queryResult.get(0); 
	
			return (eventType == 0);
		}
			else return false;
	}

}
