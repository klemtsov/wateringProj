import {connect} from 'react-redux'
import SettingValues from './Settings'
import {saveSettings, settingChanged, getSettings} from '../../actions/SettingActions'

const mapStateToProps = function (store) {
    return ({
        ...store.settings
    })
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        onSave: value => {
            console.log('onSave param', value);
            dispatch(saveSettings(value))
        },
        onNeedSave: value => {
            dispatch(settingChanged(value))
        },
        loadData: () => dispatch(getSettings())
    }
};

const SettingForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(SettingValues);

export default SettingForm
