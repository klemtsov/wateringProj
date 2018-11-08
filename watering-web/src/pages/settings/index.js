import {connect} from 'react-redux'
import SettingValues from './Settings'
import {getSettings, saveSettings, settingChanged} from '../../actions/SettingActions'

const mapStateToProps = function (state) {
    return ({
        ...state
    })
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        onSave: value => {
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
