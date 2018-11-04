import * as types from '../constants/ActionTypes'
import axios from 'axios'
import {rootApi, getSettingsMethod, saveSettingsMethod} from '../constants/api'
import {getInitialForm} from '../reducers/settings'

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
        dispatch(getSettingsRequest());

        const url = getSettingsMethod;
        axios.get(url).then(response =>
            dispatch(getSettingsSuccess(response.data))).catch(response => {
            dispatch(getSettingsError(response))
        })
    }
}

export function getSettingsRequest() {
    return {
        type: types.SETTINGS_GET_REQUEST,
        payload: {
            form: {...getInitialForm, fetching: true}
        }
    };
}

export function getSettingsSuccess(response) {
    console.log("getSettingsSuccess", response);
    const result = response.result;
    if (response.status !== "SUCCESS") {
       return getSettingsError(response.errors)
    }
    return {
        type: types.SETTINGS_GET_SUCCESS,
        payload: {
            device: result,
            form: getInitialForm
        }
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
        type: types.SETTING_CHANGED,
        payload: value
    }
}

export function saveSettings(settings) {
    const {form} = settings;
    return dispatch => {
        dispatch(saveSettingsRequest(form));

        const url = saveSettingsMethod;
        console.log('url', url);

        axios.post(url, JSON.stringify(settings.device), config).then(response =>
            dispatch(saveSettingsSuccess(response.data))).catch(response => {
            dispatch(saveSettingsError(settings, response))
        })
    }
}

export function saveSettingsRequest(formSettings) {
    return {
        type: types.SETTINGS_SAVE_REQUEST,
        payload: {
            form: {...formSettings, fetching: true}
        }
    }
}

export function saveSettingsSuccess(response) {
    let form = getInitialForm;
    console.log('saveSettingsSuccess response', response);
    const result = response.result;
    if ('SUCCESS' !== result.status) {
        form = {...form, needSave: true, error: result.errors};
    }
    return {
        type: types.SETTINGS_SAVE_SUCCESS,
        payload: {
            device: result,
            form
        }
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
