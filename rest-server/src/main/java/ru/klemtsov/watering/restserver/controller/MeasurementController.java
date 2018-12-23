package ru.klemtsov.watering.restserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.klemtsov.watering.restserver.model.*;
import ru.klemtsov.watering.restserver.repository.MeasurementRepository;
import ru.klemtsov.watering.restserver.repository.MeasurementValueKindRepository;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

import static ru.klemtsov.watering.restserver.model.ResponseResult.*;
import static ru.klemtsov.watering.restserver.model.ResponseStatus.*;

@RestController
public class MeasurementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeasurementController.class);

    @Autowired
    private MeasurementValueKindRepository measurementValueKindRepository;

    @Autowired
    private MeasurementRepository measurementRepository;

    @RequestMapping(value = "api/statistic/getStatisticByPeriod",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public ResponseResult<List<Measurement>> getMeasurementByPeriod(
            @RequestBody StatisticByPeriod statisticByPeriod) {
        try {
            //Random random = new Random();
            //Thread.sleep(random.nextInt(1500) );
            MeasurementValueKind measurementValueKind =
                    measurementValueKindRepository.findByCode(statisticByPeriod.getValueTypeCode());
            if (measurementValueKind == null) {
                return getErrorResult("Не задан тип значения!");
            }

            List<Measurement> result = measurementRepository.findMeasurementByValueKindAndValueDateBetween(measurementValueKind,
                    statisticByPeriod.getDateBegin(), statisticByPeriod.getDateEnd());
            return getSuccessfulResult(result);
        } catch (Exception e) {
            LOGGER.error("getMeasurementByPeriod", e);
            return getErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "api/statistic/set", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public ResponseResult<ResponseStatus> setStatistic(@RequestBody List<Measurement> measurements) {
        try {
            measurementRepository.saveAll(measurements);
            return getSuccessfulResult(SUCCESS);
        } catch (Exception e) {
            LOGGER.error("setStatistic", e);
            return getErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "api/statistic/getGraphDataByPeriod",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public ResponseResult<GraphData> getHumidityDataForGraphByPeriod(
            @RequestBody StatisticByPeriod statisticByPeriod) {

        ResponseResult<List<Measurement>> statisticItems = getMeasurementByPeriod(statisticByPeriod);
        if (ERROR == statisticItems.getStatus()) {
            return getErrorResult(statisticItems.getErrors());
        } else {
            List<Measurement> items = statisticItems.getResult();
            items.sort(Comparator.comparing(Measurement::getValueDate));
            GraphData graphData = new GraphData(items.size());
            GraphDataset dataset = new GraphDataset(items.size());
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            items.forEach(i -> {
                graphData.getLabels().add(format.format(i.getValueDate()));
                dataset.getData().add(i.getValue());
            });
            graphData.getDatasets().add(dataset);
            if (WARNING == statisticItems.getStatus()) {
                return getWarningResult(graphData, statisticItems.getErrors());
            }
            return getSuccessfulResult(graphData);
        }

    }
}
