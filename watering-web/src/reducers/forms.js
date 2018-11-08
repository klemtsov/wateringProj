import * as types from '../constants/formTypes'

export const getInitialForm = {
    needSave: false,
    fetching: false,
    drawerOpened: false,
    errors: []
};


export default function formReducer(state = getInitialForm, action) {

    const {payload} = action;
    switch (action.type) {
        case(types.DRAWER_VISIBLE): {
            return  {...state, drawerOpened: payload};
        }
        case(types.FETCH_CHANGE): {
            return {...state, ...payload}
        }
        case (types.SETTING_CHANGED):{
            return {...state, needSave: action.payload}
        }
        default:
            return state;
    }
}