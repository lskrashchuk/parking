package by.lskrashchuk.training.parking.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;


import by.lskrashchuk.training.parking.dataaccess.UserTypeDao;
import by.lskrashchuk.training.parking.dataaccess.filters.UserTypeFilter;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.datamodel.UserType;
import by.lskrashchuk.training.parking.datamodel.UserType_;
import by.lskrashchuk.training.parking.datamodel.User_;

@Repository
public class UserTypeDaoImpl extends AbstractDaoImpl<UserType, Long> implements UserTypeDao{

	protected UserTypeDaoImpl() {
		super(UserType.class);
	}

	@Override
	public List<UserType> find(UserTypeFilter filter) {
        EntityManager em = getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<UserType> cq = cb.createQuery(UserType.class);

        Root<UserType> from = cq.from(UserType.class);

        // set selection
        cq.select(from);
        handleFilterParameters(filter, cb, cq, from);

        // set sort params
        if (filter.getSortProperty() != null) {
            Path<Object> expression;
            expression = from.get(filter.getSortProperty());
            cq.orderBy(new OrderImpl(expression, filter.isSortOrder()));
        }

        TypedQuery<UserType> q = em.createQuery(cq);

        // set paging
        if (filter.getOffset() != null && filter.getLimit() != null) {
            q.setFirstResult(filter.getOffset());
            q.setMaxResults(filter.getLimit());
        }

        // set execute query
        List<UserType> allitems = q.getResultList();
        return allitems;
 	}

	@Override
	public long count(UserTypeFilter filter) {
        EntityManager em = getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<UserType> from = cq.from(UserType.class);

        // set selection
        cq.select(cb.count(from));

        handleFilterParameters(filter, cb, cq, from);

        TypedQuery<Long> q = em.createQuery(cq);

        // set execute query
        return q.getSingleResult();
	}
	
	   private void handleFilterParameters(UserTypeFilter filter, CriteriaBuilder cb, CriteriaQuery<?> cq, Root<UserType> from) {
	        if (filter.getName() != null) {
	            cq.where(cb.equal(from.get(UserType_.name), filter.getName()));
	        }

	    }

	@Override
	public UserType getWithUser(Long id) {
	       EntityManager em = getEntityManager();

	        CriteriaBuilder cb = em.getCriteriaBuilder();

	        CriteriaQuery<UserType> cq = cb.createQuery(UserType.class);

	        Root<UserType> from = cq.from(UserType.class);

	        // set selection
	        cq.select(from);

	        from.fetch(UserType_.users, JoinType.LEFT);

	        cq.where(cb.equal(from.get(UserType_.id), id));
	        cq.distinct(true);

	        TypedQuery<UserType> q = em.createQuery(cq);

	        // set execute query
	        return q.getSingleResult();
	    }



}
