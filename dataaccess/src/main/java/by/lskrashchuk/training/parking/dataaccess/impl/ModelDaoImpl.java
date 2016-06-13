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

import by.lskrashchuk.training.parking.dataaccess.ModelDao;
import by.lskrashchuk.training.parking.dataaccess.filters.ModelFilter;
import by.lskrashchuk.training.parking.datamodel.Model;
import by.lskrashchuk.training.parking.datamodel.Model_;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.datamodel.User_;

@Repository
public class ModelDaoImpl extends AbstractDaoImpl<Model, Long> implements ModelDao{

	protected ModelDaoImpl(){
		super(Model.class);
	}

	@Override
	public List<Model> find(ModelFilter filter) {
		EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Model> cq = cb.createQuery(Model.class);

        Root<Model> from = cq.from(Model.class);

        // set selection
        cq.select(from);

        if (filter.getModelName() != null) {
            Predicate fRegNumberEqualCondition = cb.equal(from.get(Model_.name), filter.getModelName());
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

        TypedQuery<Model> q = em.createQuery(cq);

        // set paging
        if (filter.getOffset() != null && filter.getLimit() != null) {
            q.setFirstResult(filter.getOffset());
            q.setMaxResults(filter.getLimit());
        }

        // set execute query
        List<Model> allitems = q.getResultList();
        return allitems;
	}

	@Override
	public Model find(String modelName) {
	        EntityManager em = getEntityManager();

	        CriteriaBuilder cb = em.getCriteriaBuilder();

	        CriteriaQuery<Model> cq = cb.createQuery(Model.class);

	        Root<Model> from = cq.from(Model.class);

	        cq.select(from);
	        Predicate modelnameEqualCondition = cb.equal(from.get(Model_.name), modelName);
	        cq.where(modelnameEqualCondition);

	        TypedQuery<Model> q = em.createQuery(cq);

	        List<Model> allitems = q.getResultList();

	        if (allitems.isEmpty()) {
	            return null;
	        } else if (allitems.size() == 1) {
	            return allitems.get(0);
	        } else {
	            throw new IllegalArgumentException("more than 1 model found ");
	        }
	}

}
