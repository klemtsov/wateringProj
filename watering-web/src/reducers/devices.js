import * as types from '../constants/deviceTypes'

export const getInitialData = {
    masterData: [],
    detailData: []
};


export default function deviceReducer(state = getInitialData, action) {
    const {payload} = action;

    switch (action.type) {
        case types.LOAD_MASTER_SUCCESS: {

            const {device} = payload;
            return {...state, masterData: device}
        }
        case types.LOAD_MASTER_ERROR: {
            //TODO реализовать логику обработки ошибки
            console.error('Ошибка при получении списка устройств с сервера', payload);
            return state;
        }

        case types.LOAD_DETAIL_SUCCESS: {
            const {device} = payload;
            return {...state, detailData: device}
        }
        case types.LOAD_DETAIL_ERROR: {
            //TODO реализовать логику обработки ошибки
            console.error('Ошибка при получении списка устройств с сервера', payload);
            return state;
        }

        case types.SAVE_DETAIL_REQUEST: {
            console.log(types.SAVE_DETAIL_REQUEST, payload);
            const {device} = payload;
            return {...state, detailData: device}
        }

        case types.SAVE_DETAIL_SUCCESS: {
            const {device} = payload;
            return {...state, detailData: device}
        }
        case types.SAVE_DETAIL_ERROR: {
            //TODO реализовать логику обработки ошибки
            console.error('Ошибка при сщхранении списка устройств с сервера', payload);
            return state;
        }

        default:
            return state;
    }
}
