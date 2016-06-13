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

import by.lskrashchuk.training.parking.dataaccess.RegistryDao;
import by.lskrashchuk.training.parking.dataaccess.filters.RegistryFilter;
import by.lskrashchuk.training.parking.datamodel.Registry;
import by.lskrashchuk.training.parking.datamodel.Registry_;
import by.lskrashchuk.training.parking.datamodel.User;


@Repository
public class RegistryDaoImpl extends AbstractDaoImpl<Registry, Long> implements RegistryDao{

	protected RegistryDaoImpl() {
		super(Registry.class);
	}

	@Override
	public Long count(RegistryFilter filter) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Registry> from = cq.from(Registry.class);
        cq.select(cb.count(from));
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult();
	}

	@Override
	public List<Registry> find(RegistryFilter filter) {
		EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Registry> cq = cb.createQuery(Registry.class);

        Root<Registry> from = cq.from(Registry.class);

        // set selection
        cq.select(from);

        if (filter.getRegistryPlaceNumber() != null) {
            Predicate fNameEqualCondition = cb.equal(from.get(Registry_.place), filter.getRegistryPlaceNumber());
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

        TypedQuery<Registry> q = em.createQuery(cq);

        // set paging
        if (filter.getOffset() != null && filter.getLimit() != null) {
            q.setFirstResult(filter.getOffset());
            q.setMaxResults(filter.getLimit());
        }

        // set execute query
        List<Registry> allitems = q.getResultList();
        return allitems;
	}

}
