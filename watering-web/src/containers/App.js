import React, {Component} from 'react';
import CssBaseline from '@material-ui/core/CssBaseline';
import AppbarContainer from '../components/appbar';
import RoutesContainer from '../routes';
import Dialog from '@material-ui/core/Dialog';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import CircularProgress from '@material-ui/core/CircularProgress';
import {withStyles} from '@material-ui/core/styles';

const styles = theme => ({
    progressContent: {
        textAlign: 'center'
    }
});

class App extends Component {
    constructor(props) {
        super(props);
        this.timeoutID = null;
        this.state = {
            showIndicator: false,
        };
    }

    componentDidMount() {
        this.ensureTimer(this.props);
    }

    componentWillUnmount() {
        this.destroyTimer();
    }

    componentWillReceiveProps(props) {
        if (props.form.fetching !== this.props.form.fetching) {
            this.ensureTimer(props);
        }
    }

    destroyTimer() {
        clearTimeout(this.timeoutID);
        this.timeoutID = null;
        this.setState({showIndicator: false });
    }

    ensureTimer(props) {
        if (props.form.fetching) {
            if (!this.timeoutID) {
                this.timeoutID = setTimeout(() => {
                    this.timeoutID = null;
                    this.setState({showIndicator: true });
                }, 300);
            }
        } else {
            this.destroyTimer();
        }
    }


    render() {
        const {classes} = this.props;
        return (
            <React.Fragment>
                <CssBaseline/>
                <Dialog
                    open={this.state.showIndicator}
                    aria-describedby="alert-dialog-description">
                    <DialogContent className={classes.progressContent}>
                        <CircularProgress/>
                        <DialogContentText id="alert-dialog-description">
                            Загрузка данных, ожидайте...
                        </DialogContentText>
                    </DialogContent>
                </Dialog>
                <AppbarContainer {...this.props}/>
                <RoutesContainer {...this.props}/>
            </React.Fragment>
        );
    }
}

export default withStyles(styles)(App)
