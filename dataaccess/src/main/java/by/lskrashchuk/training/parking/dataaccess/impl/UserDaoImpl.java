package by.lskrashchuk.training.parking.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;


import by.lskrashchuk.training.parking.dataaccess.UserDao;
import by.lskrashchuk.training.parking.dataaccess.filters.UserFilter;
import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.datamodel.User_;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, Long> implements UserDao{

	protected UserDaoImpl() {
		super(User.class);
	}
	
	@Override
	public List<User> find(UserFilter filter) {
		EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> from = cq.from(User.class);

        // set selection
        cq.select(from);

        if (filter.getUserName() != null) {
            Predicate fNameEqualCondition = cb.equal(from.get(User_.firstName), filter.getUserName());
            Predicate lNameEqualCondition = cb.equal(from.get(User_.lastName), filter.getUserName());
            cq.where(cb.or(fNameEqualCondition, lNameEqualCondition));
        }
        // set fetching
 //       if (filter.isFetchCredentials()) {
 //           from.fetch(User_.credentials, JoinType.LEFT);
 //       }

        // set sort params
        if (filter.getSortProperty() != null) {
            cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
        }

        TypedQuery<User> q = em.createQuery(cq);

        // set paging
        if (filter.getOffset() != null && filter.getLimit() != null) {
            q.setFirstResult(filter.getOffset());
            q.setMaxResults(filter.getLimit());
        }

        // set execute query
        List<User> allitems = q.getResultList();
        return allitems;
    }

	@Override
	public Long count(UserFilter filter) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<User> from = cq.from(User.class);
        cq.select(cb.count(from));
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult();
	}

	@Override
	public User find(String userName, String password) {
        EntityManager em = getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> from = cq.from(User.class);

        cq.select(from);
        Predicate usernameEqualCondition = cb.equal(from.get(User_.email), userName);
        Predicate passwEqualCondition = cb.equal(from.get(User_.password), password);
        cq.where(cb.and(usernameEqualCondition, passwEqualCondition));

        TypedQuery<User> q = em.createQuery(cq);

        List<User> allitems = q.getResultList();

        if (allitems.isEmpty()) {
            return null;
        } else if (allitems.size() == 1) {
            return allitems.get(0);
        } else {
            throw new IllegalArgumentException("more than 1 user found ");
        }
	}



	@Override
	public User getWithAll(Long id) {
	       EntityManager em = getEntityManager();

	        CriteriaBuilder cb = em.getCriteriaBuilder();

	        CriteriaQuery<User> cq = cb.createQuery(User.class);

	        Root<User> from = cq.from(User.class);

	        // set selection
	        cq.select(from);


	        from.fetch(User_.userType, JoinType.LEFT);
	        from.fetch(User_.cars, JoinType.LEFT);

//	        Predicate userType = cb.equal(from.get(User_.email), userName);
//	        Predicate passwEqualCondition = cb.equal(from.get(User_.password), password);
	        
	        
	        cq.where(cb.equal(from.get(User_.id), id));
	        cq.distinct(true);

	        TypedQuery<User> q = em.createQuery(cq);


	        // set execute query
	        return q.getSingleResult();
	}
}
		


