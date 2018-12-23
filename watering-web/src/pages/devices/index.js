import {connect} from 'react-redux'
import DeviceGrid from './masterGrid';
import {getDevices} from '../../actions/devicesActions'

const mapStateToProps = function (state) {
    return ({
        ...state
    })
};

const mapDispatchToProps = (dispatch) => {
    return {
        doLoadDeviceData: () => {
            dispatch(getDevices())
        }
    }
};

const DeviceForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(DeviceGrid);

export default DeviceForm;