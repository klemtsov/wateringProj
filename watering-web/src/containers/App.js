import React, {Component} from 'react';
import CssBaseline from '@material-ui/core/CssBaseline';
import AppbarContainer from '../components/appbar';
import RoutesContainer from '../routes';


class App extends Component {
    render() {
        return (
            <React.Fragment>
                <CssBaseline/>
                <AppbarContainer {...this.props}/>
                <RoutesContainer {...this.props}/>
            </React.Fragment>
        );
    }
}

export default App;
