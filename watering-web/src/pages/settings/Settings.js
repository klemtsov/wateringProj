import React from 'react';
import PropTypes from 'prop-types';
import {withStyles} from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Buttons from './buttons'



const styles = theme => ({
    button: {
        margin: theme.spacing.unit
    },
    input: {
        display: 'none'
    },
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    textField: {
        marginLeft: theme.spacing.unit,
        marginRight: theme.spacing.unit,
        width: 200,
    },
    dense: {
        marginTop: 19,
    },
    menu: {
        width: 200,
    },

});

class SettingValues extends React.Component {

    ipChange = (name, value) => {
        this.setState({name: value});
        this.props.settings.device[name] = value;
        this.props.onNeedSave(true);
    };

    componentDidMount() {
        this.props.loadData(1); // Вызываем загрузку
    }

    render() {
        const {classes, ...other} = this.props;
        return (
            <div>

                <form className={classes.container} noValidate autoComplete="off">
                    <div>
                        <TextField
                            id="ip"
                            label="IP-адрес устройства:"
                            className={classes.textField}
                            value={this.props.settings.device.ip}
                            onChange={(e) => this.ipChange('ip', e.target.value)}
                            margin="normal"
                        />
                    </div>
                    <div>
                        <TextField
                            id="wateringPeriod"
                            label="Период полива:"
                            className={classes.textField}
                            value={this.props.settings.device.wateringPeriod}
                            onChange={(e) => this.ipChange('wateringPeriod', e.target.value)}
                            margin="normal"/>
                    </div>
                    <div>
                        <TextField
                            id="wateringStartAt"
                            label="Время полива:"
                            type="time"
                            value={this.props.settings.device.wateringStartAt}
                            className={classes.textField}
                            onChange={(e) => this.ipChange('wateringStartAt', e.target.value)}
                            InputLabelProps={{
                                shrink: true,
                            }}
                            margin="normal"
                            inputProps={{
                                step: 300, // 5 min
                            }}/>
                    </div>
                    <div>
                        <TextField
                            id="serverIp"
                            label="Сервер:"
                            className={classes.textField}
                            value={this.props.settings.device.serverIp}
                            onChange={(e) => this.ipChange('serverIp', e.target.value)}
                            margin="normal"/>
                    </div>
                </form>
                <div>
                    <Buttons {...this.props}/>
                </div>
            </div>
        );
    };
}

SettingValues.propTypes = {
    classes: PropTypes.object.isRequired
};

export default (withStyles(styles)(SettingValues));
