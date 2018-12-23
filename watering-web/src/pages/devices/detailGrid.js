import * as React from 'react';
import Paper from '@material-ui/core/Paper';
import {withStyles} from '@material-ui/core/styles';
import {EditingState, IntegratedSorting, SortingState} from '@devexpress/dx-react-grid';
import {
    DragDropProvider,
    Grid,
    Table,
    TableColumnReordering,
    TableEditColumn,
    TableEditRow,
    TableHeaderRow
} from '@devexpress/dx-react-grid-material-ui';
import EditIcon from '@material-ui/icons/Edit';
import SaveIcon from '@material-ui/icons/Save';
import CancelIcon from '@material-ui/icons/Cancel';
import IconButton from '@material-ui/core/IconButton';

const styles = theme => ({
    button: {
        margin: theme.spacing.unit
    },
    inputRoot: {
        width: '100%',
    },
});

const Cell = (props) => {
    return <Table.Cell {...props} />;
};


const EditButton = ({onExecute}) => (
    <IconButton onClick={onExecute} title="Изменить">
        <EditIcon/>
    </IconButton>
);


const CommitButton = ({onExecute}) => (
    <IconButton onClick={onExecute} title="Сохранить">
        <SaveIcon/>
    </IconButton>
);

const CancelButton = ({onExecute}) => (
    <IconButton color="secondary" onClick={onExecute} title="Отменить">
        <CancelIcon/>
    </IconButton>
);

const commandComponents = {
    edit: EditButton,
    commit: CommitButton,
    cancel: CancelButton,
};

const Command = ({id, onExecute}) => {
    const CommandButton = commandComponents[id];
    return (
        <CommandButton
            onExecute={onExecute}
        />
    );
};

const EditCell = (props) => {
    return <TableEditRow.Cell {...props} />;
};

const getRowId = row => row.id;

class DeviceSettingsGrid extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            columns: [
                {
                    name: 'settingType', title: 'Параметр',
                    getCellValue: row => (row.settingType ? row.settingType.description : undefined)
                },

                {name: 'value', title: 'Значение'}
            ],
            sorting: [],
            editingRowIds: [],
            rowChanges: {},
            editingStateColumnExtensions: [
                {columnName: 'settingType', editingEnabled: false},
            ],
        };


        this.changeSorting = sorting => this.setState({sorting});
        this.changeEditingRowIds = editingRowIds => this.setState({editingRowIds});


        this.changeRowChanges = rowChanges => this.setState({rowChanges});

        this.commitChanges = ({changed}) => {
            let {data, doSaveData} = this.props;
            console.log("before commitChanges data", data);
            if (changed) {
                let changedRows = data
                    .filter(row => changed[row.id] !== undefined)
                    .map(row => {
                        return {...row, ...changed[row.id]}
                    });
                console.log("changed rows", changedRows);
                data = data.map(row => (changed[row.id] ? {...row, ...changed[row.id]} : row));
                console.log("after commitChanges data", data);
                doSaveData(changedRows, data);
            }
            ;
        };
        this.changeColumnOrder = (order) => {
            this.setState({columnOrder: order});
        };

    }

    componentDidMount() {
        this.props.dataHandler(this.props.deviceId);
    }

    render() {
        const {
            columns,
            tableColumnExtensions,
            sorting,
            editingRowIds,
            rowChanges,
            columnOrder,
            editingStateColumnExtensions
        } = this.state;

        return (
            <Paper>
                <Grid
                    rows={this.props.data}
                    columns={columns}
                    getRowId={getRowId}
                >
                    <SortingState
                        sorting={sorting}
                        onSortingChange={this.changeSorting}
                    />
                    <EditingState
                        editingRowIds={editingRowIds}
                        onEditingRowIdsChange={this.changeEditingRowIds}
                        rowChanges={rowChanges}
                        onRowChangesChange={this.changeRowChanges}
                        onCommitChanges={this.commitChanges}
                        columnExtensions={editingStateColumnExtensions}
                    />
                    <DragDropProvider/>
                    <Table columnExtensions={tableColumnExtensions}
                           cellComponent={Cell}/>

                    <IntegratedSorting/>
                    <TableColumnReordering
                        order={columnOrder}
                        onOrderChange={this.changeColumnOrder}
                    />
                    <TableHeaderRow showSortingControls/>

                    <TableEditRow
                        cellComponent={EditCell}
                    />
                    <TableEditColumn
                        width={170}
                        showEditCommand
                        commandComponent={Command}
                    />

                </Grid>
            </Paper>
        );
    }
}

export default (withStyles(styles)(DeviceSettingsGrid));