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

import by.lskrashchuk.training.parking.dataaccess.BrandDao;
import by.lskrashchuk.training.parking.dataaccess.filters.BrandFilter;
import by.lskrashchuk.training.parking.datamodel.Brand;
import by.lskrashchuk.training.parking.datamodel.Brand_;


@Repository
public class BrandDaoImpl extends AbstractDaoImpl<Brand, Long> implements BrandDao {

	protected BrandDaoImpl() {
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
		// if (filter.isFetchCredentials()) {
		// from.fetch(User_.credentials, JoinType.LEFT);
		// }

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

	@Override
	public Brand getWithModels(Long id) {
		EntityManager em = getEntityManager();
		
		if (em.find(getEntityClass(), id)==null) {
			return null;
		};

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Brand> cq = cb.createQuery(Brand.class);

		Root<Brand> from = cq.from(Brand.class);

		// set selection
		cq.select(from);

		from.fetch(Brand_.models, JoinType.LEFT);

		cq.where(cb.equal(from.get(Brand_.id), id));
		cq.distinct(true);

		TypedQuery<Brand> q = em.createQuery(cq);

		// set execute query
		return q.getSingleResult();
	}

}
