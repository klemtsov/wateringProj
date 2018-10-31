import * as types from '../constants/ActionTypes'

export const getInitialForm = {
    needSave: false,
    fetching: false
}

const initialState = {
    device: {
        ip: '192.168.0.1',
        wateringPeriod: 0,
        wateringStartAt: '18:00',
        startWateringBelowHumidity: 0,
        timeWateringInSec: 10,
        serverIp: ''
    },
    form: getInitialForm
}

export default function settingsReducer(state = initialState, action) {
    const {payload} = action;
    const {form} = state.form;
    console.log('in reducer payliad', payload)

    switch (action.type) {
        case types.SETTINGS_GET_REQUEST: {
            return {...state, form: payload}
        }
        case types.SETTINGS_GET_SUCCESS: {
            return {...state, ...payload}
        }
        case types.SETTINGS_GET_ERROR: {
            //TODO реализовать логику обработки ошибки
            console.error('Ошибка при получении настроек с сервера', payload);
            return state;
        }
        case types.SETTINGS_SAVE_REQUEST: {
            const {form} = payload;
            return {...state, form}
        }

        case types.SETTINGS_SAVE_SUCCESS: {
            const {device, form} = payload;
            //const newWateringPrtiod = device.wateringPeriod + 1;

            //let newForm = {...form, needSave: false};
            //let newDevice = {...device, wateringPeriod: newWateringPrtiod};
            return {...payload, device, form: form};
        }
            ;

        case types.SETTINGS_SAVE_ERROR: {
            //TODO реализовать логику обработки ошибки
            console.error('Ошибка при сохранении настроек на сервер', payload);
            return state;
        }
        case types.SETTING_CHANGED: {
            let newState = {...state, form: {...form, needSave: payload}};
            return newState;
        }
        default:
            return state;
    }
}
