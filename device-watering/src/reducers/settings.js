import * as types from '../constants/ActionTypes'

const initialState = {
    device: {
        ssid: '',
        password: '',
        serverIp: '192.168.0.1',
        serverPort: '8082',
        useTimeServer: true,
        timeServer: '0.asia.pool.ntp.org',
        timeSyncInterval: 600,
        ip: '',
        wateringMode: 0,
        wateringPeriod: 0,
        wateringStartAt: '',
        startWateringBelowHumidity: 0,
        timeWateringInSec: 10,
    }
};

export default function settingsReducer(state = initialState, action) {
    const {payload} = action;
    console.log('settingsReducer payload', payload);

    switch (action.type) {
        case types.SETTINGS_GET_SUCCESS: {
            return {...state, ...payload}
        }
        case types.SETTINGS_GET_ERROR: {
            //TODO реализовать логику обработки ошибки
            console.error('Ошибка при получении настроек с сервера', payload);
            return state;
        }


        case types.SETTINGS_SAVE_SUCCESS: {
            const {device} = payload;
            return {...payload, device};
        }
        case types.SETTINGS_SAVE_ERROR: {
            //TODO реализовать логику обработки ошибки
            console.error('Ошибка при сохранении настроек на сервер', payload);
            return state;
        }
        default:
            return state;
    }
}
