import React, {Component} from 'react';
import CssBaseline from '@material-ui/core/CssBaseline';
import Appbar from '../components/appBar';
import Routes from '../routes';


class App extends Component {
    render() {
        return (
            <React.Fragment>
                <CssBaseline/>
                <Appbar/>
                <Routes/>
            </React.Fragment>
        );
    }
}

export default App;
