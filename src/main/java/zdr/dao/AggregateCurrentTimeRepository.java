package zdr.dao;

import org.springframework.data.repository.CrudRepository;
import zdr.dto.AggregateEntityCurrentTime;

/**
 * Created by MuratovaO on 22.05.2016.
 */
public interface AggregateCurrentTimeRepository extends CrudRepository<AggregateEntityCurrentTime, Long> {
}
