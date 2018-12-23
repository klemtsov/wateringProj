package ru.klemtsov.watering.restserver.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ru.klemtsov.watering.restserver.exception.WateringException;
import ru.klemtsov.watering.restserver.model.*;
import ru.klemtsov.watering.restserver.repository.MeasurementRepository;
import ru.klemtsov.watering.restserver.repository.MeasurementValueKindRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static ru.klemtsov.watering.restserver.model.ResponseStatus.*;

@RunWith(MockitoJUnitRunner.class)
public class MeasurementControllerTest {

    @InjectMocks
    private MeasurementController measurementController;

    @Mock
    private MeasurementRepository measurementRepository;

    @Mock
    private MeasurementValueKindRepository measurementValueKindRepository;


    private StatisticByPeriod statisticByPeriod;

    private List<Measurement> measurements;

    private List<String> setStatisticErrors;

    @Spy
    private MeasurementValueKind measurementValueKind;

    @Before
    public void setUp() throws WateringException {
        measurements = new ArrayList<>();
        Measurement item = new Measurement();
        item.setValue(new BigDecimal(123));
        item.setValueDate(new Date());
        measurements.add(item);

        statisticByPeriod = new StatisticByPeriod();
        statisticByPeriod.setValueTypeCode("123");
        statisticByPeriod.setDateBegin(new Date());
        statisticByPeriod.setDateEnd(new Date());
        measurementValueKind = new MeasurementValueKind();
        measurementValueKind.setCode("CODE");
        measurementValueKind.setName("NAME");
        doReturn(measurementValueKind).when(measurementValueKindRepository).findByCode(anyString());

        measurements = new ArrayList<>();
        measurements.add(item);
        doReturn(measurements).when(measurementRepository)
                .findMeasurementByValueKindAndValueDateBetween(any(MeasurementValueKind.class),
                        any(Date.class), any(Date.class));

        setStatisticErrors = new ArrayList<>();


    }

    @Test
    public void getStatisticByPeriod() {
        ResponseResult<List<Measurement>> listResponseResult =
                measurementController.getMeasurementByPeriod(statisticByPeriod);
        assertNotNull(listResponseResult);
        assertEquals(SUCCESS, listResponseResult.getStatus());
    }

    @Test
    public void getStatisticByPeriod_notFindValueKind() {
        doReturn(null).when(measurementValueKindRepository).findByCode(anyString());
        ResponseResult<List<Measurement>> listResponseResult =
                measurementController.getMeasurementByPeriod(statisticByPeriod);
        assertNotNull(listResponseResult);
        assertEquals(ERROR, listResponseResult.getStatus());
    }

    @Test
    public void getStatisticByPeriod_throwException() {
        doThrow(new RuntimeException("123")).when(measurementValueKindRepository).findByCode(anyString());
        ResponseResult<List<Measurement>> listResponseResult =
                measurementController.getMeasurementByPeriod(statisticByPeriod);
        assertNotNull(listResponseResult);
        assertEquals(ERROR, listResponseResult.getStatus());
        assertEquals("123", listResponseResult.getErrors().get(0));
    }

    @Test
    public void setStatisticTest() throws WateringException {
        setStatisticErrors.clear();
        List<Measurement> result = new ArrayList<>();
        doReturn(result).when(measurementRepository).saveAll(anyList());

        ResponseResult responseResult = measurementController.setStatistic(measurements);

        assertNotNull(responseResult);
        assertNull(responseResult.getErrors());
        assertEquals(SUCCESS, responseResult.getStatus());
        assertEquals(SUCCESS, responseResult.getResult());
    }


    @Test
    public void setStatisticTest_SetStaisticThrowException() throws WateringException {
        String exceptionMessage = "123";
        setStatisticErrors.clear();
        setStatisticErrors.add(exceptionMessage);
        doThrow(new RuntimeException(exceptionMessage)).when(measurementRepository).saveAll(anyList());
        ResponseResult responseResult = measurementController.setStatistic(measurements);

        assertNotNull(responseResult);
        assertEquals(1, responseResult.getErrors().size());
        assertEquals(exceptionMessage, responseResult.getErrors().get(0));
        assertEquals(ERROR, responseResult.getStatus());
        assertNull(responseResult.getResult());
    }

    @Test
    public void getHumidityDataForGraphByPeriodTest() {
        ResponseResult<GraphData> listResponseResult =
                measurementController.getHumidityDataForGraphByPeriod(statisticByPeriod);
        assertNotNull(listResponseResult);
        assertEquals(SUCCESS, listResponseResult.getStatus());
    }

    @Test
    public void getHumidityDataForGraphByPeriod_notFindValueKind() {
        doReturn(null).when(measurementValueKindRepository).findByCode(anyString());
        ResponseResult<GraphData> listResponseResult =
                measurementController.getHumidityDataForGraphByPeriod(statisticByPeriod);
        assertNotNull(listResponseResult);
        assertEquals(ERROR, listResponseResult.getStatus());
    }

    @Test
    public void getHumidityDataForGraphByPeriod_throwException() {
        doThrow(new RuntimeException("123")).when(measurementValueKindRepository).findByCode(anyString());
        ResponseResult<GraphData> listResponseResult =
                measurementController.getHumidityDataForGraphByPeriod(statisticByPeriod);
        assertNotNull(listResponseResult);
        assertEquals(ERROR, listResponseResult.getStatus());
        assertEquals("123", listResponseResult.getErrors().get(0));
    }
}