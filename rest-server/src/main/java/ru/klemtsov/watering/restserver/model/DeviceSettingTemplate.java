package ru.klemtsov.watering.restserver.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class DeviceSettingTemplate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_type_id")
    private DeviceType deviceType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "setting_item_type_id")
    private SettingItemType settingItemType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public SettingItemType getSettingItemType() {
        return settingItemType;
    }

    public void setSettingItemType(SettingItemType settingItemType) {
        this.settingItemType = settingItemType;
    }
}
