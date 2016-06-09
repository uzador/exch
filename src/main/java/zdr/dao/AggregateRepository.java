package zdr.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import zdr.dto.AggregateEntity;
import zdr.dto.VolumeDate;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by yzadorozhnyy on 05.05.2016.
 */
public interface AggregateRepository extends CrudRepository<AggregateEntity, Long> {
    @Query("Select new zdr.dto.VolumeDate(t.volume, t.tradedate) From AggregateEntity t Where t.market_name = :market_name And market_name != 'moexboard' And t.secid = :secid")
    List<VolumeDate> getByMarketName(@Param("market_name") String marketName, @Param("secid") String secid);

    @Query("Select distinct t.secid From AggregateEntity t")
    List<String> getSecids();

    @Query("Select distinct t.market_name From AggregateEntity t Where t.secid = :secid And market_name != 'moexboard'")
    List<String> getMarketNamesBySecid(@Param("secid") String secid);

    @Query("Select max(t.tradedate) From AggregateEntity t Where t.secid = :secid")
    LocalDate getMaxDateBySecid(@Param("secid") String secid);
}
