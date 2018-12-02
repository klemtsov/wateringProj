import React from 'react';
import {Link, Route, Router} from 'react-router-dom';
import createBrowserHistory from 'history/createBrowserHistory';

import PropTypes from 'prop-types';
import {withStyles} from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';

import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import InboxIcon from '@material-ui/icons/MoveToInbox';

import SettingsForm from '../pages/settings';
import StatisticForm from '../pages/statistics';

const drawerWidth = 240;

const styles = theme => ({
    root: {
        flexGrow: 1,
        zIndex: 1,
        overflow: 'hidden',
        position: 'relative',
        display: 'flex'
    },
    drawerPaper: {
        position: 'relative',
        width: drawerWidth
    },
    content: {
        flexGrow: 1,
        backgroundColor: theme.palette.background.default,
        padding: theme.spacing.unit * 3,
        minWidth: 0 // So the Typography noWrap works
    },
    toolbar: theme.mixins.toolbar
});

const history = createBrowserHistory();

class Routes extends React.Component {

    render() {
        const {classes} = this.props;
        return (
            <div>
                <Router history={history}>
                    <div className={classes.root}>
                        <Drawer open={this.props.drawerOpened} onClose={() => this.props.closeDrawer()}
                                classes={{
                                    paper: classes.drawerPaper
                                }}>
                            <div
                                tabIndex={0}
                                role="button"
                                onClick={() => this.props.closeDrawer()}
                                onKeyDown={() => this.props.closeDrawer()}
                            />
                            {/* <div className={classes.toolbar} /> */}
                            <List>
                                <ListItem button component={Link} to="/" onClick={() => this.props.closeDrawer()}>
                                    <ListItemIcon>
                                        <InboxIcon/>
                                    </ListItemIcon>
                                    <ListItemText primary="Настройки"/>
                                </ListItem>
                                <ListItem button component={Link} to="/statistic"
                                          onClick={() => this.props.closeDrawer()}>
                                    <ListItemIcon>
                                        <InboxIcon/>
                                    </ListItemIcon>
                                    <ListItemText primary="Статистика"/>
                                </ListItem>

                            </List>
                        </Drawer>
                        <main className={classes.content}>
                            {/* <div className={classes.toolbar} /> */}
                            <Route exact path="/" component={SettingsForm}/>
                            <Route exact path="/statistic" component={StatisticForm}/>
                        </main>
                    </div>
                </Router>
            </div>
        );
    };
};

Routes.propTypes = {
    classes: PropTypes.object.isRequired
};

export default withStyles(styles)(Routes);
