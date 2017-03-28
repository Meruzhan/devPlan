package filter;

import org.hibernate.jpa.criteria.expression.EntityTypeExpression;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.Map;

/**
 * Created by meruzhan.gasparyan on 08-Dec-16.
 */

@Service
public class EventFilter<T> implements Specification<T> {

    private Map<String, String> filters;

    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.disjunction();

//        cb.like(root.get(filters.get("")),"%"+filters.get(""));

        predicate.getExpressions().add(cb.and(getPredicates(filters, root, cb)));
//        predicate.getExpressions().add(cb.and(getPredicates(filters, root, cb)));

        return predicate;
    }

    private Predicate[] getPredicates(Map<String, String> filters, Root<T> root, CriteriaBuilder cb) {
        int index = filters.size();
        Predicate[] predicate = new Predicate[index];

        for (Map.Entry filter : filters.entrySet()) {
            index--;
            predicate[index] =  cb.like(root.get(filter.getKey().toString()),"%"+filter.getValue().toString()+"%");
//            predicate[index] = cb.equal(root.get(filter.getKey().toString()),filter.getValue());

        }

        return predicate;
    }

    public void setFilters(Map<String, String> filters) {
        this.filters = filters;
    }
}
