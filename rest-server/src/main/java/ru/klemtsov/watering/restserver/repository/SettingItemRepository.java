package ru.klemtsov.watering.restserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.klemtsov.watering.restserver.model.Device;
import ru.klemtsov.watering.restserver.model.SettingItem;

import java.util.List;

public interface SettingItemRepository extends JpaRepository<SettingItem, Integer> {

    List<SettingItem> findSettingItemsByDevice(Device device);

}
