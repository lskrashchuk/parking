package by.lskrashchuk.training.parking.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.lskrashchuk.training.parking.dataaccess.CarPhotoDao;
import by.lskrashchuk.training.parking.dataaccess.filters.CarPhotoFilter;
import by.lskrashchuk.training.parking.datamodel.CarPhoto;
import by.lskrashchuk.training.parking.datamodel.CarPhoto_;

@Repository
public class CarPhotoDaoImpl extends AbstractDaoImpl<CarPhoto, Long> implements CarPhotoDao{

	protected CarPhotoDaoImpl() {
		super(CarPhoto.class);
	}

	@Override
	public List<CarPhoto> find(CarPhotoFilter filter) {
		EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<CarPhoto> cq = cb.createQuery(CarPhoto.class);

        Root<CarPhoto> from = cq.from(CarPhoto.class);

        // set selection
        cq.select(from);

        if (filter.getCar() != null) {
        	if (filter.getCarPhoto() != null) {
            
        		Predicate fCarEqualCondition = cb.equal(from.get(CarPhoto_.car), filter.getCar());
        		Predicate fCarPhotoEqualCondition = cb.equal(from.get(CarPhoto_.photo), filter.getCarPhoto());
;

        		cq.where(cb.or(fCarEqualCondition, fCarPhotoEqualCondition));
        	}
        }
        // set fetching
 //       if (filter.isFetchCredentials()) {
 //           from.fetch(User_.credentials, JoinType.LEFT);
 //       }

            TypedQuery<CarPhoto> q = em.createQuery(cq);

        // set paging
        if (filter.getOffset() != null && filter.getLimit() != null) {
            q.setFirstResult(filter.getOffset());
            q.setMaxResults(filter.getLimit());
        }

        // set execute query
        List<CarPhoto> allitems = q.getResultList();
        return allitems;
	}

}
