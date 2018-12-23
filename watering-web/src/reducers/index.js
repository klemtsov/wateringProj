import {combineReducers} from 'redux';

import settingsReducer from './settings';
import formReducer from './forms';
import statReducer from './statistics';
import deviceReducer from './devices';

export default combineReducers({
    settings: settingsReducer,
    form: formReducer,
    statData: statReducer,
    device: deviceReducer
})
