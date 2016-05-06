package zdr.dao;

import org.springframework.data.repository.CrudRepository;
import zdr.domain.AggregateEntity;

/**
 * Created by yzadorozhnyy on 05.05.2016.
 */
public interface AggregateRepository extends CrudRepository<AggregateEntity, Long> {
}
