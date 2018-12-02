package ru.klemtsov.watering.restserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.klemtsov.watering.restserver.dao.SettingsDAO;
import ru.klemtsov.watering.restserver.exception.WateringException;
import ru.klemtsov.watering.restserver.model.ResponseResult;
import ru.klemtsov.watering.restserver.model.Settings;

import java.util.Random;

@RestController
public class SettingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SettingController.class);

    private final SettingsDAO settingsDAO;

    @Autowired
    public SettingController(SettingsDAO settingsDAO) {
        this.settingsDAO = settingsDAO;
    }

    @RequestMapping(value = "api/settings/get", method = RequestMethod.GET)
    public ResponseResult<Settings> getSetting(){
      try {
          //Random random = new Random();
          //Thread.sleep(random.nextInt(1500) );
          Settings result = settingsDAO.getSettings();
          LOGGER.info("возвращаем {}", result);
          return ResponseResult.getSuccessfulResult(result);
          //return ResponseResult.getErrorResult("123");
      }catch (Exception e){
          LOGGER.error("getSettings", e);
        return ResponseResult.getErrorResult(e.getMessage());
      }
    }

    @RequestMapping(value = "api/settings/set", method = RequestMethod.POST)
    public ResponseResult<Settings> setSettings(@RequestBody Settings settings){
        try {
            settingsDAO.setSettings(settings);
            return ResponseResult.getSuccessfulResult(settings);
        } catch (WateringException e) {
            e.printStackTrace();
            return ResponseResult.getErrorResult(e.getMessage());
        }
    }

}
