import * as types from '../constants/StatTypes';
import axios from 'axios'
import {showFetching} from '../actions/formActions';
import {getGraphDataByPeriodMethod} from "../constants/api";
import {getRequestConfig} from '../utils/commons';
import * as moment from 'moment';

function defParams() {
    let d2 = moment();
    let d1 = moment(d2).subtract(1, 'months');
    return {
        valueTypeCode: "W1",
        dateBegin: d1.toISOString(true),
        dateEnd: d2.toISOString(true)
    };
}


export function refresh(params) {
    const p = params === undefined ? defParams() : params;
    console.log("p", p);
    return dispatch => {
        dispatch(showFetching(true));

        axios.post(getGraphDataByPeriodMethod, JSON.stringify(p), getRequestConfig())
            .then(response => {
                dispatch(showFetching(false));
                dispatch(getStatSuccess(response.data));
            }).catch(response => {
            dispatch(showFetching(false));
            dispatch(getStatError(response))
        })
    }
}

export function getStatSuccess(response) {
    if (response.status !== "SUCCESS") {
        return getStatError(response.errors)
    }
    console.log("statistic response", response.result);
    return {
        type: types.STAT_GET_SUCCESS,
        payload: response.result
    }
}

export function getStatError(errorData) {
    console.log('Ошибка при получении данных для графика', errorData);
    return {
        type: types.STAT_GET_ERROR,
        payload: errorData
    }
}
