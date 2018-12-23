import React from 'react';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Typography from '@material-ui/core/Typography';
import {withStyles} from '@material-ui/core/styles';
import DeviceSettingsGrid from './detailGrid';

const styles = theme => ({
    root: {
        flexGrow: 1,
        backgroundColor: theme.palette.background.paper,
    },
    tab: {
        flexGrow: 1,
        backgroundColor: theme.palette.background.paper,
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

function TabContainer(props) {
    return (
        <Typography component="div" style={{padding: 8}}>
            {props.children}
        </Typography>
    );
}

class DeviceTabs extends React.Component {
    state = {
        tab: 0
    };

    constructor(props) {
        super(props);
    }

    tabChange = (event, tab) => {
        this.setState({tab});
    };

    render() {
        const {tab} = this.state;
        const {classes, device, parentRow, doLoadSettingsData, doSaveSettingsData} = this.props;

        return (
            <div className={classes.root}>
                <Tabs className={classes.tab}
                      value={tab}
                      onChange={this.tabChange}
                      indicatorColor="primary"
                      textColor="primary">
                    <Tab label="Настройки"/>
                    <Tab label="Операции"/>
                </Tabs>

                {tab === 0 && <TabContainer>
                    <DeviceSettingsGrid deviceId = {parentRow.id}
                                        dataHandler = {doLoadSettingsData}
                                        doSaveData = {doSaveSettingsData}
                                        data = {device.detailData}/>
                </TabContainer>}

                {tab === 1 && <TabContainer>
                    Операции для типа {parentRow.deviceType.name}
                </TabContainer>}
            </div>)

    };
}

export default (withStyles(styles)(DeviceTabs));