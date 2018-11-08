import * as types from '../constants/ActionTypes'
import axios from 'axios'
import {getSettingsMethod, saveSettingsMethod} from '../constants/api'
import {showFetching,} from '../actions/formActions';
import {SETTING_CHANGED} from "../constants/formTypes";

const config = {
    headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        'Access-Control-Allow-Origin': "*",
        'Access-Control-Allow-Methods': 'GET, PUT, POST, DELETE',
        //'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept, Authorization'
    }
};

export function getSettings() {
    return dispatch => {
        dispatch(showFetching(true));

        axios.get(getSettingsMethod).then(response => {
            dispatch(showFetching(false));
            dispatch(getSettingsSuccess(response.data));
        }).catch(response => {
            dispatch(showFetching(false));
            dispatch(getSettingsError(response))
        })
    }
}


export function getSettingsSuccess(response) {
    const result = response.result;
    if (response.status !== "SUCCESS") {
        return getSettingsError(response.errors)
    }

    return {
        type: types.SETTINGS_GET_SUCCESS,
        payload: {device: result}
    }
}

export function getSettingsError(errorData) {
    console.log('Ошибка при получении данных настроек', errorData);
    return {
        type: types.SETTINGS_GET_ERROR,
        payload: errorData
    }
}

export function settingChanged(value) {
    return {
        type: SETTING_CHANGED,
        payload: value
    }
}

export function saveSettings(settings) {
    return dispatch => {
        dispatch(showFetching(true));

        const url = saveSettingsMethod;
        console.log('url', url);
        let isSuccess = false;
        axios.post(url, JSON.stringify(settings.device), config).then(response => {
            const result = response.data;
            const errors = 'SUCCESS' !== result.status ? result.errors : [];
            isSuccess = 'SUCCESS' === result.status;
            if (isSuccess) {
                dispatch(showFetching(false))
            } else {
                dispatch(showFetching(false, errors));

            }
            dispatch(saveSettingsSuccess(response.data));
            if (isSuccess) {
                dispatch(settingChanged(false))
            }
        }).catch(response => {
            dispatch(showFetching(false));
            dispatch(saveSettingsError(settings, response))
        })
    }
}

export function saveSettingsSuccess(response) {
    return {
        type: types.SETTINGS_SAVE_SUCCESS,
        payload: {device: response.result}
    }
}

export function saveSettingsError(settings, errorData) {
    const {device, form} = settings;
    console.log('Ошибка при сохранении настроек', errorData);
    const result = {device, form: {...form, fetching: false, errorMessage: errorData}};
    return {
        type: types.SETTINGS_SAVE_ERROR,
        payload: result
    }
}
