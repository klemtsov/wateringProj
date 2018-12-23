package ru.klemtsov.watering.restserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.klemtsov.watering.restserver.model.Device;
import ru.klemtsov.watering.restserver.model.ResponseResult;
import ru.klemtsov.watering.restserver.model.SettingItem;
import ru.klemtsov.watering.restserver.repository.DeviceRepository;
import ru.klemtsov.watering.restserver.repository.SettingItemRepository;

import java.util.List;

@RestController
public class SettingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SettingController.class);

    @Autowired
    private SettingItemRepository settingItemRepository;

   @Autowired
   private DeviceRepository deviceRepository;

    @RequestMapping(value = "api/settings/get", method = RequestMethod.GET)
    public ResponseResult<List<SettingItem>> getSetting(@RequestParam(name = "id") Integer id) {
        try {
            //Random random = new Random();
            //Thread.sleep(random.nextInt(1500) );
            Device device = deviceRepository.getOne(id);
            List<SettingItem> result = settingItemRepository.findSettingItemsByDevice(device);
            LOGGER.info("возвращаем {}", result);
            return ResponseResult.getSuccessfulResult(result);
        } catch (Exception e) {
            LOGGER.error("getSettings", e);
            return ResponseResult.getErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "api/settings/set", method = RequestMethod.POST)
    public ResponseResult<List<SettingItem>> setSettings(@RequestBody List<SettingItem> settings) {
        try {
            settingItemRepository.saveAll(settings);
            return ResponseResult.getSuccessfulResult(settings);
        } catch (Exception e) {
            return ResponseResult.getErrorResult(e.getMessage());
        }
    }

}
