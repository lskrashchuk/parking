package by.lskrashchuk.training.parking.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.lskrashchuk.training.parking.dataaccess.BrandDao;
import by.lskrashchuk.training.parking.dataaccess.filters.BrandFilter;
import by.lskrashchuk.training.parking.datamodel.Brand;
import by.lskrashchuk.training.parking.datamodel.Brand_;
import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.Car_;

@Repository
public class BrandDaoImpl extends AbstractDaoImpl<Brand, Long> implements BrandDao{
	
	protected BrandDaoImpl(){
		super(Brand.class);
	}

	@Override
	public List<Brand> find(BrandFilter filter) {
		EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Brand> cq = cb.createQuery(Brand.class);

        Root<Brand> from = cq.from(Brand.class);

        // set selection
        cq.select(from);

        if (filter.getBrandName() != null) {
            Predicate fRegNumberEqualCondition = cb.equal(from.get(Brand_.name), filter.getBrandName());
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

        TypedQuery<Brand> q = em.createQuery(cq);

        // set paging
        if (filter.getOffset() != null && filter.getLimit() != null) {
            q.setFirstResult(filter.getOffset());
            q.setMaxResults(filter.getLimit());
        }

        // set execute query
        List<Brand> allitems = q.getResultList();
        return allitems;
	}

}
