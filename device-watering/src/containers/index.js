import {connect} from 'react-redux'
import App from './App'

const mapStateToProps = function (state) {
    return ({
        ...state
    })
};

const AppCnt = connect(
    mapStateToProps
)(App);

export default AppCnt
