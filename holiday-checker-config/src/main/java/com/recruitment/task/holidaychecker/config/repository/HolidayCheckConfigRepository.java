package com.recruitment.task.holidaychecker.config.repository;

import com.recruitment.task.holidaychecker.config.entity.HolidayCheckConfig;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class HolidayCheckConfigRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void updateHolidayCheckConfig(HolidayCheckConfig holidayCheckConfig) {
        Query query = entityManager.createNamedQuery("updateHolidayCheckConfig");
        query.setParameter(1, holidayCheckConfig.getValue());
        query.setParameter(2, holidayCheckConfig.getKey());
        query.executeUpdate();
    }

    public String getHolidayCheckConfigValueByKey(String key) {
        TypedQuery<String> query = entityManager.createNamedQuery("getHolidayCheckConfigValueByKey", String.class);
        query.setParameter(1, key);
        return query.getSingleResult();
    }

    public List<HolidayCheckConfig> getAllHolidayCheckConfigs() {
        TypedQuery<HolidayCheckConfig> query = entityManager.createNamedQuery("getAllHolidayCheckConfigs", HolidayCheckConfig.class);
        return query.getResultList();
    }
}