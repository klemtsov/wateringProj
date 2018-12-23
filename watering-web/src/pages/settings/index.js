import {connect} from 'react-redux'
import SettingValues from './Settings'
import {getSettings, saveSettings, settingChanged} from '../../actions/settingActions'

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
        loadData: (id) => dispatch(getSettings(id))
    }
};

const SettingForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(SettingValues);

export default SettingForm
