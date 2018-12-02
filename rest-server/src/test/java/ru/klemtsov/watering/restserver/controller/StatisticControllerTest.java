package ru.klemtsov.watering.restserver.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ru.klemtsov.watering.restserver.dao.StatisticDAO;
import ru.klemtsov.watering.restserver.dao.ValueKindDAO;
import ru.klemtsov.watering.restserver.exception.WateringException;
import ru.klemtsov.watering.restserver.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static ru.klemtsov.watering.restserver.model.ResponseStatus.ERROR;
import static ru.klemtsov.watering.restserver.model.ResponseStatus.SUCCESS;
import static ru.klemtsov.watering.restserver.model.ResponseStatus.WARNING;

@RunWith(MockitoJUnitRunner.class)
public class StatisticControllerTest {

    @InjectMocks
    private StatisticController statisticController;

    @Mock
    private StatisticDAO statisticDAO;


    @Mock
    private ValueKindDAO valueKindDAO;


    private StatisticByPeriod statisticByPeriod;

    private List<StatisticItem> statisticItems;

    private List<String> setStatisticErrors;

    @Spy
    private ValueKind valueKind;

    @Before
    public void setUp() throws WateringException {
        statisticItems = new ArrayList<>();
        StatisticItem item = new StatisticItem();
        item.setValue(new BigDecimal(123));
        item.setValueDate(new Date());
        statisticItems.add(item);

        statisticByPeriod = new StatisticByPeriod();
        statisticByPeriod.setValueTypeCode("123");
        statisticByPeriod.setDateBegin(new Date());
        statisticByPeriod.setDateEnd(new Date());
        valueKind = new ValueKind();
        valueKind.setCode("CODE");
        valueKind.setName("NAME");
        doReturn(valueKind).when(valueKindDAO).getValueKindByCode(anyString());

        statisticItems = new ArrayList<>();
        statisticItems.add(item);
        doReturn(statisticItems).when(statisticDAO).getStatisticByPeriod(any(ValueKind.class),
                any(Date.class), any(Date.class));

        setStatisticErrors = new ArrayList<>();


    }

    @Test
    public void getStatisticByPeriod() {
        ResponseResult<List<StatisticItem>> listResponseResult =
                statisticController.getStatisticByPeriod(statisticByPeriod);
        assertNotNull(listResponseResult);
        assertEquals(SUCCESS, listResponseResult.getStatus());
    }

    @Test
    public void getStatisticByPeriod_notFindValueKind() {
        doReturn(null).when(valueKindDAO).getValueKindByCode(anyString());
        ResponseResult<List<StatisticItem>> listResponseResult =
                statisticController.getStatisticByPeriod(statisticByPeriod);
        assertNotNull(listResponseResult);
        assertEquals(ERROR, listResponseResult.getStatus());
    }

    @Test
    public void getStatisticByPeriod_throwException() {
        doThrow(new RuntimeException("123")).when(valueKindDAO).getValueKindByCode(anyString());
        ResponseResult<List<StatisticItem>> listResponseResult =
                statisticController.getStatisticByPeriod(statisticByPeriod);
        assertNotNull(listResponseResult);
        assertEquals(ERROR, listResponseResult.getStatus());
        assertEquals("123", listResponseResult.getErrors().get(0));
    }

    @Test
    public void setStatisticTest() throws WateringException {
        setStatisticErrors.clear();
        doReturn(setStatisticErrors).when(statisticDAO).setStatistic(anyList());

        ResponseResult responseResult = statisticController.setStatistic(statisticItems);

        assertNotNull(responseResult);
        assertNull(responseResult.getErrors());
        assertEquals(SUCCESS, responseResult.getStatus());
        assertEquals(SUCCESS, responseResult.getResult());
    }


    @Test
    public void setStatisticTest_SetStaisticWithErrors() throws WateringException {
        setStatisticErrors.clear();
        setStatisticErrors.add("123");
        doReturn(setStatisticErrors).when(statisticDAO).setStatistic(anyList());
        ResponseResult responseResult = statisticController.setStatistic(statisticItems);

        assertNotNull(responseResult);
        assertEquals(1, responseResult.getErrors().size());
        assertEquals(WARNING, responseResult.getStatus());
        assertEquals(WARNING, responseResult.getResult());
    }

    @Test
    public void setStatisticTest_SetStaisticThrowException() throws WateringException {
        String exceptionMessage = "123";
        setStatisticErrors.clear();
        setStatisticErrors.add(exceptionMessage);
        doThrow(new RuntimeException(exceptionMessage)).when(statisticDAO).setStatistic(anyList());
        ResponseResult responseResult = statisticController.setStatistic(statisticItems);

        assertNotNull(responseResult);
        assertEquals(1, responseResult.getErrors().size());
        assertEquals(exceptionMessage, responseResult.getErrors().get(0));
        assertEquals(ERROR, responseResult.getStatus());
        assertNull(responseResult.getResult());
    }

    @Test
    public void getHumidityDataForGraphByPeriodTest(){
        ResponseResult<GraphData> listResponseResult =
                statisticController.getHumidityDataForGraphByPeriod(statisticByPeriod);
        assertNotNull(listResponseResult);
        assertEquals(SUCCESS, listResponseResult.getStatus());
    }

    @Test
    public void getHumidityDataForGraphByPeriod_notFindValueKind() {
        doReturn(null).when(valueKindDAO).getValueKindByCode(anyString());
        ResponseResult<GraphData> listResponseResult =
                statisticController.getHumidityDataForGraphByPeriod(statisticByPeriod);
        assertNotNull(listResponseResult);
        assertEquals(ERROR, listResponseResult.getStatus());
    }

    @Test
    public void ggetHumidityDataForGraphByPeriod_throwException() {
        doThrow(new RuntimeException("123")).when(valueKindDAO).getValueKindByCode(anyString());
        ResponseResult<GraphData> listResponseResult =
                statisticController.getHumidityDataForGraphByPeriod(statisticByPeriod);
        assertNotNull(listResponseResult);
        assertEquals(ERROR, listResponseResult.getStatus());
        assertEquals("123", listResponseResult.getErrors().get(0));
    }
}