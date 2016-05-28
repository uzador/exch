package zdr.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import zdr.dto.AggregateEntity;
import zdr.dto.VolumeDate;

import java.util.List;

/**
 * Created by yzadorozhnyy on 05.05.2016.
 */
public interface AggregateRepository extends CrudRepository<AggregateEntity, Long> {
    AggregateEntity findById(Long id);

    @Query("SELECT new zdr.dto.VolumeDate(t.volume, t.tradedate) from AggregateEntity t WHERE t.market_name = :market_name")
    List<VolumeDate> getByMarketName(@Param("market_name") String marketName);
}
