import {combineReducers} from 'redux';

import settingsReducer from './settings';
import formReducer from './forms';

export default combineReducers({
    settings: settingsReducer,
    form: formReducer
})
