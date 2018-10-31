import * as types from '../constants/ActionTypes'
import axios from 'axios'
import {rootApi, getSettingsMethod, saveSettingsMethod} from '../constants/api'
import {getInitialForm} from '../reducers/settings'

export function getSettings() {
    return dispatch => {
        dispatch(getSettingsRequest());

        const url = rootApi + getSettingsMethod;
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

export function getSettingsSuccess(deviceSettings) {
    return {
        type: types.SETTINGS_GET_SUCCESS,
        payload: {
            device: deviceSettings,
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

        const url = rootApi + saveSettingsMethod;
        console.log('url', url);
        axios.post(url).then(response =>
            dispatch(saveSettingsSuccess(settings.device, response.data))).catch(response => {
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

export function saveSettingsSuccess(deviceSettings, response) {
    let form = getInitialForm;
    if ('ok' !== response.status) {
        form = {...form, needSave: true, error: response.error};
    }
    return {
        type: types.SETTINGS_SAVE_SUCCESS,
        payload: {
            device: deviceSettings,
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
