import * as types from '../constants/formTypes';

export function showDrawer(value) {
        return {
            type: types.DRAWER_VISIBLE,
            payload: value
        }
}

export function showFetching(value, errors) {
    return {
        type: types.FETCH_CHANGE,
        payload: {fetching: value, errors}
    }
}