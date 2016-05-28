package zdr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zdr.dao.AggregateRepository;
import zdr.dto.AggregateEntity;
import zdr.dto.VolumeDate;

import java.util.List;

/**
 * Created by MuratovaO on 22.05.2016.
 */
@Service
public class ManagerImpl implements Manager {

    @Autowired
    AggregateRepository aggregateRepository;

    @Override
    public AggregateEntity getAggregateEntityById(Long id) {
        return aggregateRepository.findById(id);
    }

    public List<VolumeDate> getByMarketName(String marketName) {
        return aggregateRepository.getByMarketName(marketName);
    }
}
