package zdr.service;

import org.springframework.data.repository.query.Param;
import zdr.dto.AggregateEntity;
import zdr.dto.VolumeDate;

import java.util.List;

/**
 * Created by MuratovaO on 22.05.2016.
 */
public interface Manager {
    List<VolumeDate> getByMarketName(String marketName, String secid);

    List<String> getSecids();

    List<String> getMarketNamesBySecid(String secid);
}
