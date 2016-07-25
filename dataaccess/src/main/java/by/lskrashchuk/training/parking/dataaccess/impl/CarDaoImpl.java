package by.lskrashchuk.training.parking.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.lskrashchuk.training.parking.dataaccess.CarDao;
import by.lskrashchuk.training.parking.dataaccess.filters.CarFilter;
import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.Car_;
import by.lskrashchuk.training.parking.datamodel.Place;

@Repository
public class CarDaoImpl extends AbstractDaoImpl<Car, Long> implements CarDao{
	
	protected CarDaoImpl() {
		super(Car.class);
	}

	@Override
	public List<Car> find(CarFilter filter) {
		EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Car> cq = cb.createQuery(Car.class);

        Root<Car> from = cq.from(Car.class);

        // set selection
        cq.select(from);

        if (filter.getCarRegNumber() != null) {
            Predicate fRegNumberEqualCondition = cb.equal(from.get(Car_.regNumber), filter.getCarRegNumber());
            cq.where(cb.or(fRegNumberEqualCondition));
        }
        // set fetching
 //       if (filter.isFetchCredentials()) {
 //           from.fetch(User_.credentials, JoinType.LEFT);
 //       }

        // set sort params
        if (filter.getSortProperty() != null) {
            cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
        }

        TypedQuery<Car> q = em.createQuery(cq);

        // set paging
        if (filter.getOffset() != null && filter.getLimit() != null) {
            q.setFirstResult(filter.getOffset());
            q.setMaxResults(filter.getLimit());
        }

        // set execute query
        List<Car> allitems = q.getResultList();
        return allitems;
	}

	@Override
	public Long count(CarFilter filter) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Car> from = cq.from(Car.class);
        cq.select(cb.count(from));
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult();
	}

	@Override
	public Car getWithAll(Long id) {
	       EntityManager em = getEntityManager();

	        CriteriaBuilder cb = em.getCriteriaBuilder();

	        CriteriaQuery<Car> cq = cb.createQuery(Car.class);

	        Root<Car> from = cq.from(Car.class);

	        // set selection
	        cq.select(from);


	        from.fetch(Car_.carPhotos, JoinType.LEFT);
	        from.fetch(Car_.color, JoinType.LEFT);
	        
	        
	        cq.where(cb.equal(from.get(Car_.id), id));
	        cq.distinct(true);

	        TypedQuery<Car> q = em.createQuery(cq);


	        // set execute query
	        return q.getSingleResult();
	}

	@Override
	public Long getPlaceId(Car car) {
			EntityManager em = getEntityManager();
			List<Object[]> queryResult  = em.createNativeQuery("SELECT r.event_type, r.place_id FROM registry r LEFT JOIN car c ON r.car_id=c.id WHERE c.id="+car.getId()+" ORDER BY r.event_time DESC LIMIT 1").getResultList(); 

			if (!queryResult.isEmpty()) {
				Object[] res = queryResult.get(0); 
				Integer eventType = (Integer)res[0];
				if (eventType == 0){
					Long r = new Long(res[1].toString());
					return r;
				}
				else return null;
			}
				else return null;
		}
	
	
	

}
