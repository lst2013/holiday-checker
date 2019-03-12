package com.recruitment.task.holidaychecker.config.entity;

import javax.persistence.*;

@Entity
@Table(name = "holiday_check_config")
@NamedQuery(name = "updateHolidayCheckConfig", query = "UPDATE HolidayCheckConfig hcc SET hcc.value = ?1 WHERE hcc.key = ?2")
@NamedQuery(name = "getHolidayCheckConfigValueByKey", query = "SELECT hcc.value FROM HolidayCheckConfig hcc WHERE hcc.key = ?1")
@NamedQuery(name = "getAllHolidayCheckConfigs", query = "SELECT hcc FROM HolidayCheckConfig hcc")
public class HolidayCheckConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "config_key", nullable = false, unique = true)
    private String key;

    @Column(name = "config_value", nullable = false)
    private String value;

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }
}