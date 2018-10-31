import React from 'react'
import SaveIcon from '@material-ui/icons/Save'
import classNames from 'classnames'
import Button from '@material-ui/core/Button'
import {withStyles} from '@material-ui/core/styles'
import {connect} from 'react-redux'

function Buttons(props) {
    const {classes} = props;
    console.log('button props', props);
    return (
        <div>
            <Button variant='contained' size='small' className={classes.button}
                    onClick={() => props.onSave(props)}
                    disabled={!props.form.needSave}
                    color='primary'>
                <SaveIcon className={classNames(classes.leftIcon, classes.iconSmall)}/>
                Сохранить
            </Button>
        </div>
    )
}

export default (Buttons)
