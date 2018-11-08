import {connect} from 'react-redux'

import Routes from "./routes"

import {showDrawer} from '../actions/formActions'


const mapStateToProps = function (state) {
    return ({
        ...state.form
    })
};

const mapDispatchToProps = (dispatch) => {
    return {
        closeDrawer: () => {
            dispatch(showDrawer(false));
        },
        openDrawer: () => {
            dispatch(showDrawer(true));
        },
    }
};

const RoutesContainer = connect(
    mapStateToProps,
    mapDispatchToProps
)(Routes);

export default RoutesContainer;