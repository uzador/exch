package zdr.service;

import zdr.dto.AggregateEntity;

/**
 * Created by MuratovaO on 22.05.2016.
 */
public interface Manager {
    AggregateEntity getAggregateEntityById(Long id);
}
