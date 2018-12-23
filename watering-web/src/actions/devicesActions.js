import * as types from '../constants/deviceTypes';

import axios from 'axios'
import {getDeviceSettingsMethod,
    getDevicesMethod,
    saveDeviceSettingsMethod} from '../constants/api';
import {showFetching} from '../actions/formActions';

import {getRequestConfig} from '../utils/commons';

export function getDevices() {
    return dispatch => {
        dispatch(showFetching(true));

        axios.get(getDevicesMethod).then(response => {
            dispatch(showFetching(false));
            dispatch(getDevicesSuccess(response.data));
        }).catch(response => {
            dispatch(showFetching(false));
            dispatch(getDevicesError(response))
        })
    }
}

export function getDevicesSuccess(response) {
    const result = response.result;
    if (response.status !== "SUCCESS") {
        return getDevicesError(response.errors)
    }

    return {
        type: types.LOAD_MASTER_SUCCESS,
        payload: {device: result}
    }
}

export function getDevicesError(errorData) {
    console.log('Ошибка при получении данных об устройствах', errorData);
    return {
        type: types.LOAD_MASTER_ERROR,
        payload: errorData
    }
}

export function getDeviceSettings(id) {
    return dispatch => {
        dispatch(showFetching(true));
        let url = getDeviceSettingsMethod + "?id="+ id;
        axios.post(url, JSON.stringify(id), getRequestConfig()).then(response => {
            dispatch(showFetching(false));
            dispatch(getDeviceSettingsSuccess(response.data));
        }).catch(response => {
            dispatch(showFetching(false));
            dispatch(getDeviceSettingsError(response))
        })
    }
}

export function getDeviceSettingsSuccess(response) {
    const result = response.result;
    console.log("getDeviceSettingsSuccess");
    if (response.status !== "SUCCESS") {
        return getDevicesError(response.errors)
    }

    return {
        type: types.LOAD_DETAIL_SUCCESS,
        payload: {device: result}
    }
}

export function getDeviceSettingsError(errorData) {
    console.log('Ошибка при получении данных о настройках устройства', errorData);
    return {
        type: types.LOAD_DETAIL_ERROR,
        payload: errorData
    }
}

export function saveDeviceSettings(changedRows, allRows) {
    return dispatch => {
        dispatch(showFetching(true));
        dispatch(saveDeviceSettingsRequest(allRows));
        axios.post(saveDeviceSettingsMethod, JSON.stringify(changedRows), getRequestConfig())
            .then(response => {
            dispatch(showFetching(false));
            dispatch(saveDeviceSettingsSuccess(response.data));
        }).catch(response => {
            dispatch(showFetching(false));
            dispatch(saveDeviceSettingsError(response))
        })
    }
}

export function saveDeviceSettingsRequest(changedRows) {
    console.log("saveDeviceSettingsRequest", changedRows);
    return {
        type: types.SAVE_DETAIL_REQUEST,
        payload: {device: changedRows}
    }
}

export function saveDeviceSettingsSuccess(response) {
    const result = response.result;
    console.log("saveDeviceSettingsSuccess", result);
    if (response.status !== "SUCCESS") {
        return getDevicesError(response.errors)
    }

    return {
        type: types.SAVE_DETAIL_SUCCESS,
        payload: {device: result}
    }
}

export function saveDeviceSettingsError(errorData) {
    console.log('Ошибка при сохранении данных о настройках устройства', errorData);
    return {
        type: types.SAVE_DETAIL_ERROR,
        payload: errorData
    }
}