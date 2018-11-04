package ru.klemtsov.watering.restserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ru.klemtsov.watering.restserver.dao.StatisticDAO;
import ru.klemtsov.watering.restserver.dao.ValueKindDAO;
import ru.klemtsov.watering.restserver.model.ResponseResult;
import ru.klemtsov.watering.restserver.model.ResponseStatus;
import ru.klemtsov.watering.restserver.model.StatisticByPeriod;
import ru.klemtsov.watering.restserver.model.StatisticItem;
import ru.klemtsov.watering.restserver.model.ValueKind;

import java.util.List;

@RestController
public class StatisticController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticController.class);

    @Autowired
    private ValueKindDAO valueKindDAO;

    @Autowired
    private StatisticDAO statisticDAO;

    @RequestMapping(value = "/statistic/getStatisticByPeriod",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public ResponseResult<List<StatisticItem>> getStatisticByPeriod(@RequestBody StatisticByPeriod statisticByPeriod) {
        try {
            ValueKind valueKind = valueKindDAO.getValueKindByCode(statisticByPeriod.getValueTypeCode());
            if (valueKind == null) {
                return ResponseResult.getErrorResult("Не задан тип значения!");
            }

            List<StatisticItem> result = statisticDAO.getStatisticByPeriod(valueKind,
                    statisticByPeriod.getDateBegin(), statisticByPeriod.getDateEnd());
            return ResponseResult.getSuccessfulResult(result);
        } catch (Exception e) {
            LOGGER.error("getStatisticByPeriod", e);
            return ResponseResult.getErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/statistic/set", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public ResponseResult<ResponseStatus> setStatistic(@RequestBody List<StatisticItem> statisticItems){
        try{
            List<String> errors = statisticDAO.setStatistic(statisticItems);
            if (!CollectionUtils.isEmpty(errors)){
                return ResponseResult.getWarningResult(ResponseStatus.WARNING, errors);
            }
            return ResponseResult.getSuccessfulResult(ResponseStatus.SUCCESS);
        } catch (Exception e){
            LOGGER.error("setStatistic", e);
            return ResponseResult.getErrorResult(e.getMessage());
        }
    }
}
