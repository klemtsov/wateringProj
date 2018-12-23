import {connect} from 'react-redux'
import DeviceTab from './deviceTabs';
import {getDeviceSettings, saveDeviceSettings} from '../../actions/devicesActions'

const mapStateToProps = function (state) {
    return ({
        ...state
    })
};

const mapDispatchToProps = (dispatch) => {
    return {
        doLoadSettingsData: deviceId => {
            dispatch(getDeviceSettings(deviceId))
        },
        doSaveSettingsData: (changedRows, allRows) =>{
            dispatch(saveDeviceSettings(changedRows, allRows))
        }
    }
};

const DeviceTabContainer = connect(
    mapStateToProps,
    mapDispatchToProps
)(DeviceTab);

export default DeviceTabContainer;