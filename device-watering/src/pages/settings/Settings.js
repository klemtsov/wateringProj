import React from 'react';
import PropTypes from 'prop-types';
import {withStyles} from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Buttons from './buttons'
import Divider from '@material-ui/core/Divider';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Typography from '@material-ui/core/Typography';
import MenuItem from '@material-ui/core/MenuItem';
import Select from '@material-ui/core/Select';
import InputLabel from '@material-ui/core/InputLabel';
import FormControl from '@material-ui/core/FormControl';
import Switch from '@material-ui/core/Switch';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Grid from '@material-ui/core/Grid';


const styles = theme => ({
    root: {
        flexGrow: 1,
        backgroundColor: theme.palette.background.paper,
    },
    tab: {
        flexGrow: 1,
    },
    button: {
        margin: theme.spacing.unit,
        float: 'right'
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
    formControl: {
        marginTop: 15,
        marginLeft: theme.spacing.unit,
        marginRight: theme.spacing.unit,
        minWidth: 200,
    },
    selectEmpty: {
        marginTop: theme.spacing.unit * 2,
    },
    formControlLabel: {
        marginTop: 15
    },
    iOSSwitchBase: {

        '&$iOSChecked': {
            color: theme.palette.common.white,
            '& + $iOSBar': {
                backgroundColor: '#52d869',
            },
        },
        transition: theme.transitions.create('transform', {
            duration: theme.transitions.duration.shortest,
            easing: theme.transitions.easing.sharp,
        }),
    },
    iOSChecked: {
        transform: 'translateX(15px)',
        '& + $iOSBar': {
            opacity: 1,
            border: 'none',
        },
    },
    iOSBar: {
        borderRadius: 13,
        width: 42,
        height: 26,
        marginTop: -13,
        marginLeft: -21,
        border: 'solid 1px',
        borderColor: theme.palette.grey[400],
        backgroundColor: theme.palette.grey[50],
        opacity: 1,
        transition: theme.transitions.create(['background-color', 'border']),
    },
    iOSIcon: {
        width: 24,
        height: 24,
    },
    iOSIconChecked: {
        boxShadow: theme.shadows[1],
    },

});

const timeServers = [
    {
        value: '0.ru.pool.ntp.org'
    },
    {
        value: '0.europe.pool.ntp.org',
    },
    {
        value: '0.north-america.pool.ntp.org',
    },
    {
        value: '0.asia.pool.ntp.org',
    },
];

function TabContainer(props) {
    return (
        <Typography component="div" style={{padding: 8 * 3}}>
            {props.children}
        </Typography>
    );
}

TabContainer.propTypes = {
    children: PropTypes.node.isRequired,
};

class SettingValues extends React.Component {

    state = {
        value: 0,
    };

    tabChange = (event, value) => {
        this.setState({value});
    };

    ipChange = (name, value) => {
        this.setState({name: value});
        this.props.settings.device[name] = value;
        this.props.onNeedSave(true);
    };

    componentDidMount() {
        this.props.loadData(); // Вызываем загрузку
    }

    render() {
        const {value} = this.state;
        const {classes, ...other} = this.props;
        return (
            <div>

                <form className={classes.container} noValidate autoComplete="off">
                    <div>
                        <Tabs fullWidth
                              className={classes.tab}
                              value={value}
                              onChange={this.tabChange}
                              indicatorColor="primary"
                              textColor="primary">
                            <Tab label="Сеть"/>
                            <Tab label="Сервер"/>
                            <Tab label="Настройки времени"/>
                            <Tab label="Полив"/>

                        </Tabs>
                        {value === 0 && <TabContainer>
                            <Grid container spacing={24}>
                                <Grid item xs={12}>
                                    <TextField
                                        id="ssid"
                                        label="Точка доступа:"
                                        className={classes.textField}
                                        value={this.props.settings.device.ssid}
                                        onChange={(e) => this.ipChange('ssid', e.target.value)}
                                        margin="normal"
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        id="password"
                                        label="Пароль:"
                                        className={classes.textField}
                                        value={this.props.settings.device.password}
                                        onChange={(e) => this.ipChange('passsword', e.target.value)}
                                        margin="normal"
                                    />
                                </Grid>
                            </Grid>
                        </TabContainer>}
                        {value === 1 && <TabContainer>
                            <Grid container column spacing={24} direction="column"
                                  justify="flex-start"
                                  alignItems="center">
                                <Grid item xs={12}>
                                    <TextField
                                        id="serverIp"
                                        label="Сервер:"
                                        className={classes.textField}
                                        value={this.props.settings.device.serverIp}
                                        onChange={(e) => this.ipChange('serverIp', e.target.value)}
                                        margin="normal"/>
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        id="serverPort"
                                        label="Порт:"
                                        className={classes.textField}
                                        value={this.props.settings.device.serverPort}
                                        onChange={(e) => this.ipChange('serverPort', e.target.value)}
                                        margin="normal"
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        id="ip"
                                        label="IP-адрес:"
                                        className={classes.textField}
                                        value={this.props.settings.device.ip}
                                        onChange={(e) => this.ipChange('ip', e.target.value)}
                                        margin="normal"
                                    />
                                </Grid>
                            </Grid>
                        </TabContainer>}
                        {value === 2 && <TabContainer>
                            <FormControlLabel className={classes.formControlLabel}
                                              control={
                                                  <Switch
                                                      classes={{
                                                          switchBase: classes.iOSSwitchBase,
                                                          bar: classes.iOSBar,
                                                          icon: classes.iOSIcon,
                                                          iconChecked: classes.iOSIconChecked,
                                                          checked: classes.iOSChecked,
                                                      }}
                                                      disableRipple
                                                      checked={this.props.settings.device.useTimeServer}
                                                      onChange={(e) => this.ipChange('useTimeServer', e.target.checked)}
                                                      value={this.props.settings.device.useTimeServer}
                                                  />
                                              }
                                              label="время с сервера"
                            />
                            <TextField
                                id=""
                                select
                                label="Сервер времени:"
                                className={classes.textField}
                                value={this.props.settings.device.timeServer}
                                onChange={(e) => this.ipChange('timeServer', e.target.value)}
                                disabled={!this.props.settings.device.useTimeServer}
                                margin="normal"

                                SelectProps={{
                                    MenuProps: {
                                        className: classes.menu,
                                    },
                                }}
                            >{timeServers.map(option => (
                                <MenuItem key={option.value} value={option.value}>
                                    {option.value}
                                </MenuItem>
                            ))}</TextField>
                            <TextField
                                id="timeSyncInterval"
                                label="Интервал обновления, c:"
                                className={classes.textField}
                                value={this.props.settings.device.timeSyncInterval}
                                onChange={(e) => this.ipChange('timeSyncInterval', e.target.value)}
                                disabled={!this.props.settings.device.useTimeServer}
                                margin="normal"
                            />

                        </TabContainer>}
                        {value === 3 && <TabContainer>
                            <FormControl className={classes.formControl}>
                                <InputLabel htmlFor="wateringMode-helper">Режим работы</InputLabel>
                                <Select
                                    value={this.props.settings.device.wateringMode}
                                    onChange={(e) => this.ipChange('wateringMode', e.target.value)}
                                    inputProps={{
                                        id: 'wateringMode-helper',
                                        name: 'wateringMode',
                                    }}>
                                    <MenuItem value={0}>Выключен</MenuItem>
                                    <MenuItem value={1}>По времени</MenuItem>
                                    <MenuItem value={2}>По влажности</MenuItem>
                                </Select>
                            </FormControl>
                            <TextField
                                id="timeWatering"
                                label="Продолжительность, с:"
                                disabled={this.props.settings.device.wateringMode == 0}
                                className={classes.textField}
                                value={this.props.settings.device.timeWateringInSec}
                                onChange={(e) => this.ipChange('timeWateringInSec', e.target.value)}
                                margin="normal"/>
                            <TextField
                                id="wateringPeriod"
                                label="Периодичность полива, ч:"
                                className={classes.textField}
                                value={this.props.settings.device.wateringPeriod}
                                disabled={this.props.settings.device.wateringMode == 0}
                                onChange={(e) => this.ipChange('wateringPeriod', e.target.value)}
                                margin="normal"/>
                            <TextField
                                id="startWateringBelowHumidity"
                                label="Влажность:"
                                className={classes.textField}
                                value={this.props.settings.device.startWateringBelowHumidity}
                                disabled={this.props.settings.device.wateringMode == 0
                                || this.props.settings.device.wateringMode == 1}
                                onChange={(e) => this.ipChange('startWateringBelowHumidity', e.target.value)}
                                margin="normal"/>
                            <TextField
                                id="wateringStartAt"
                                label="Поливать в:"
                                type="time"
                                value={this.props.settings.device.wateringStartAt}
                                disabled={this.props.settings.device.wateringMode == 0 ||
                                this.props.settings.device.wateringMode == 2}
                                className={classes.textField}
                                onChange={(e) => this.ipChange('wateringStartAt', e.target.value)}
                                InputLabelProps={{
                                    shrink: true,
                                }}
                                margin="normal"
                                inputProps={{
                                    step: 300, // 5 min
                                }}/>
                        </TabContainer>}
                    </div>
                </form>

                <div><Divider/>
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
