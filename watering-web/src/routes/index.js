import React from 'react';
import {Router, Route, Link} from 'react-router-dom';
import createBrowserHistory from 'history/createBrowserHistory';

import PropTypes from 'prop-types';
import {withStyles} from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';

import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import InboxIcon from '@material-ui/icons/MoveToInbox';
import DraftsIcon from '@material-ui/icons/Drafts';
import StarIcon from '@material-ui/icons/Star';
import SendIcon from '@material-ui/icons/Send';
import MailIcon from '@material-ui/icons/Mail';
import DeleteIcon from '@material-ui/icons/Delete';
import ReportIcon from '@material-ui/icons/Report';

import SettingsForm from '../pages/settings';

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

const Routes = props => {
    const {classes} = props;

    return (
        <div>
            <Router history={history}>
                <div className={classes.root}>
                    <Drawer
                        variant="temporary"
                        classes={{
                            paper: classes.drawerPaper
                        }}
                    >
                        {/* <div className={classes.toolbar} /> */}
                        <List>
                            <ListItem button component={Link} to="/">
                                <ListItemIcon>
                                    <InboxIcon/>
                                </ListItemIcon>
                                <ListItemText primary="Home"/>
                            </ListItem>

                        </List>
                    </Drawer>
                    <main className={classes.content}>
                        {/* <div className={classes.toolbar} /> */}
                        <Route exact path="/" component={SettingsForm}/>
                    </main>
                </div>
            </Router>
        </div>
    );
};

Routes.propTypes = {
    classes: PropTypes.object.isRequired
};

export default withStyles(styles)(Routes);
