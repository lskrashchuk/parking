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

import by.lskrashchuk.training.parking.dataaccess.ColorDao;
import by.lskrashchuk.training.parking.dataaccess.filters.ColorFilter;
import by.lskrashchuk.training.parking.datamodel.Color;
import by.lskrashchuk.training.parking.datamodel.Color_;
import by.lskrashchuk.training.parking.datamodel.Model;
import by.lskrashchuk.training.parking.datamodel.Model_;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.datamodel.User_;

@Repository
public class ColorDaoImpl extends AbstractDaoImpl<Color, Long> implements ColorDao{
	protected ColorDaoImpl() {
		super(Color.class);
		
	}

	@Override
	public List<Color> find(ColorFilter filter) {
		EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Color> cq = cb.createQuery(Color.class);

        Root<Color> from = cq.from(Color.class);

        // set selection
        cq.select(from);

        if (filter.getColorName() != null) {
            Predicate colornameEqualCondition = cb.equal(from.get(Color_.name), filter.getColorName());
            cq.where(colornameEqualCondition);
        }
        // set fetching
 //       if (filter.isFetchCredentials()) {
 //           from.fetch(User_.credentials, JoinType.LEFT);
 //       }

        // set sort params
        if (filter.getSortProperty() != null) {
            cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
        }

        TypedQuery<Color> q = em.createQuery(cq);

        // set paging
        if (filter.getOffset() != null && filter.getLimit() != null) {
            q.setFirstResult(filter.getOffset());
            q.setMaxResults(filter.getLimit());
        }

        // set execute query
        List<Color> allitems = q.getResultList();
        return allitems;
	}

	@Override
	public Color find(String colorName) {
		EntityManager em = getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Color> cq = cb.createQuery(Color.class);

		Root<Color> from = cq.from(Color.class);

		cq.select(from);
		Predicate colornameEqualCondition = cb.equal(cb.upper(from.get(Color_.name)), colorName.toUpperCase().toUpperCase());
		cq.where(colornameEqualCondition);

		TypedQuery<Color> q = em.createQuery(cq);

		List<Color> allitems = q.getResultList();

		if (allitems.isEmpty()) {
			return null;
		} else if (allitems.size() == 1) {
			return allitems.get(0);
		} else {
			throw new IllegalArgumentException("more than 1 color found ");
		}
	}

}
