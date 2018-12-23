import * as React from 'react';
import Paper from '@material-ui/core/Paper';
import {withStyles} from '@material-ui/core/styles';
import {DataTypeProvider, IntegratedSorting, RowDetailState, SortingState} from '@devexpress/dx-react-grid';
import {Grid, Table, TableHeaderRow, TableRowDetail,} from '@devexpress/dx-react-grid-material-ui';
import DeviceTabContainer from './devicelTabCnt';


const styles = theme => ({
    button: {
        margin: theme.spacing.unit
    },
});

const RowDetail = ({row}) => (
    <div>
        <DeviceTabContainer parentRow={row}/>
    </div>
);

const DateFormatter = ({value}) => value.replace(/(\d{4})-(\d{2})-(\d{2})/, '$3.$2.$1');

const DateTypeProvider = props => (
    <DataTypeProvider
        formatterComponent={DateFormatter}
        {...props}
    />
);

class DeviceGrid extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            columns: [
                {name: 'id', title: 'id', width: 30},
                {
                    name: 'deviceType', title: 'Тип устройства',
                    getCellValue: row => (row.deviceType ? row.deviceType.name : undefined)
                },
                {
                    name: 'deviceStatus.name', title: 'Статус',
                    getCellValue: row => (row.deviceStatus ? row.deviceStatus.name : undefined)
                },
                {name: 'ipAddress', title: 'IP-адрес'},
                {name: 'lastUpdateAt', title: 'Дата изменения'},
                {name: 'deviceId', title: 'Серийный номер'},
            ],
            dateColumns: ['lastUpdateAt'],
        };
    }

    componentDidMount() {
        this.props.doLoadDeviceData(); // Вызываем загрузку

        console.log("grid props", this.props);
    }

    render() {
        const {columns, dateColumns} = this.state;

        return (
            <Paper>
                <Grid
                    rows={this.props.device.masterData}
                    columns={columns}
                >
                    <DateTypeProvider
                        for={dateColumns}
                    />
                    <SortingState
                        defaultSorting={[
                            {columnName: 'id', direction: 'asc'},
                        ]}
                    />
                    <RowDetailState

                    />
                    <SortingState/>
                    <Table/>
                    <IntegratedSorting/>
                    <TableHeaderRow showSortingControls/>
                    <TableRowDetail
                        contentComponent={RowDetail}
                    />
                </Grid>
            </Paper>
        );
    }
}

export default (withStyles(styles)(DeviceGrid));