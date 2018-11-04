package ru.klemtsov.watering.restserver.model;

public class SettingItem {
    private Integer id;
    private String settingType;
    private String key;
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSettingType() {
        return settingType;
    }

    public void setSettingType(String settingType) {
        this.settingType = settingType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SettingItem() {
    }

    public SettingItem(Integer id, String settingType, String key, String value) {
        this.id = id;
        this.settingType = settingType;
        this.key = key;
        this.value = value;
    }
}
