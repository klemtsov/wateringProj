import {connect} from 'react-redux'
import HumidityLine from './Statistics'
import {refresh} from '../../actions/StatActions.js'

const mapStateToProps = function (state) {
    return ({
        ...state.statData
    })
};

const mapDispatchToProps = (dispatch) => {
    return {
        onUpdate: (params) => {
            dispatch(refresh(params));
        },
        loadGraphData: () => {
            dispatch(refresh())
        }
    }
};

const HumidityLineForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(HumidityLine);

export default HumidityLineForm