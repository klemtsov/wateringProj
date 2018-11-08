import {connect} from 'react-redux'

import AppBar from "./appBar"
import {showDrawer} from '../../actions/formActions'


const mapStateToProps = function (state) {
    return ({
        form: state.form
    })
};

const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        openDrawer: () => dispatch(showDrawer(true))
    }
};

const AppBarContainer = connect(
    mapStateToProps,
    mapDispatchToProps
)(AppBar);

export default AppBarContainer;