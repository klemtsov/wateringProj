package ru.klemtsov.watering.restserver.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ru.klemtsov.watering.restserver.model.Device;
import ru.klemtsov.watering.restserver.model.ResponseResult;
import ru.klemtsov.watering.restserver.model.ResponseStatus;
import ru.klemtsov.watering.restserver.model.SettingItem;
import ru.klemtsov.watering.restserver.repository.DeviceRepository;
import ru.klemtsov.watering.restserver.repository.SettingItemRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.klemtsov.watering.restserver.model.ResponseResult.getErrorResult;
import static ru.klemtsov.watering.restserver.model.ResponseResult.getSuccessfulResult;

@RestController
public class DeviceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private SettingItemRepository settingItemRepository;

    @RequestMapping(value = "api/device/getAll")
    @ResponseBody
    public ResponseResult<List<Device>> getAllDevices() {
        try {
            List<Device> res = deviceRepository.findAll();
            return getSuccessfulResult(res);
        } catch (Exception e) {
            LOGGER.error("getAllDevices", e);
            return getErrorResult("Ошибка при загрузке списка устройств, см. логи");
        }
    }

    @RequestMapping(value = "api/device/getDetail")
    @ResponseBody
    public ResponseResult<List<SettingItem>> getDeviceSettings(@RequestParam(name = "id") Integer id) {
        try {
            Device device = deviceRepository.findById(id).get();
            List<SettingItem> result = settingItemRepository.findSettingItemsByDevice(device);

            return getSuccessfulResult(result);
        } catch (Exception e) {
            return getErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "api/device/saveDetails",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<List<SettingItem>> saveDeviceSettings(@RequestBody List<SettingItem> settings) {
        try {

            if (!CollectionUtils.isEmpty(settings)) {
                settingItemRepository.saveAll(settings);
                Device device = settings.get(0).getDevice();
               return getDeviceSettings(device.getId());
            }
            return getSuccessfulResult(new ArrayList<>());
        } catch (Exception e) {
            return getErrorResult(e.getMessage());
        }
    }


}
