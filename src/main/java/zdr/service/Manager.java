package zdr.service;

import zdr.dto.AggregateEntity;
import zdr.dto.VolumeDate;

import java.util.List;

/**
 * Created by MuratovaO on 22.05.2016.
 */
public interface Manager {
    AggregateEntity getAggregateEntityById(Long id);

    List<VolumeDate> getByMarketName(String marketName);
}
