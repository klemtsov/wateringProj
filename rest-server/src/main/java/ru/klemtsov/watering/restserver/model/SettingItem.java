package ru.klemtsov.watering.restserver.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name = "setting_item")
public class SettingItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "setting_type")
    private SettingItemType settingType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id")
    private Device device;

    private String value;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SettingItemType getSettingType() {
        return settingType;
    }

    public void setSettingType(SettingItemType settingType) {
        this.settingType = settingType;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
