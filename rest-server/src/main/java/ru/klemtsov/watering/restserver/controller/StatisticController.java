package ru.klemtsov.watering.restserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.klemtsov.watering.restserver.dao.StatisticDAO;
import ru.klemtsov.watering.restserver.dao.ValueKindDAO;
import ru.klemtsov.watering.restserver.model.*;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static ru.klemtsov.watering.restserver.model.ResponseStatus.*;

@RestController
public class StatisticController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticController.class);

    @Autowired
    private ValueKindDAO valueKindDAO;

    @Autowired
    private StatisticDAO statisticDAO;

    @RequestMapping(value = "api/statistic/getStatisticByPeriod",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public ResponseResult<List<StatisticItem>> getStatisticByPeriod(
            @RequestBody StatisticByPeriod statisticByPeriod) {
        try {
            //Random random = new Random();
            //Thread.sleep(random.nextInt(1500) );
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

    @RequestMapping(value = "api/statistic/set", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public ResponseResult<ResponseStatus> setStatistic(@RequestBody List<StatisticItem> statisticItems) {
        try {
            List<String> errors = statisticDAO.setStatistic(statisticItems);
            if (!CollectionUtils.isEmpty(errors)) {
                return ResponseResult.getWarningResult(ResponseStatus.WARNING, errors);
            }
            return ResponseResult.getSuccessfulResult(SUCCESS);
        } catch (Exception e) {
            LOGGER.error("setStatistic", e);
            return ResponseResult.getErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "api/statistic/getGraphDataByPeriod",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public ResponseResult<GraphData> getHumidityDataForGraphByPeriod(
            @RequestBody StatisticByPeriod statisticByPeriod) {

        ResponseResult<List<StatisticItem>> statisticItems = getStatisticByPeriod(statisticByPeriod);
        if (ERROR == statisticItems.getStatus()) {
            return ResponseResult.getErrorResult(statisticItems.getErrors());
        } else {
            List<StatisticItem> items = statisticItems.getResult();
            items.sort(Comparator.comparing(StatisticItem::getValueDate));
            GraphData graphData = new GraphData(items.size());
            GraphDataset dataset = new GraphDataset(items.size());
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            items.forEach(i -> {
                graphData.getLabels().add(format.format(i.getValueDate()));
                dataset.getData().add(i.getValue());
            });
            graphData.getDatasets().add(dataset);
            if (WARNING == statisticItems.getStatus()) {
                return ResponseResult.getWarningResult(graphData, statisticItems.getErrors());
            }
            return ResponseResult.getSuccessfulResult(graphData);
        }

    }
}
