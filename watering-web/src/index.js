import React from 'react';
import ReactDOM from 'react-dom';
import App from './containers/App';
import {Provider} from 'react-redux';
import {applyMiddleware, createStore} from 'redux';
import thunk from 'redux-thunk';
import {composeWithDevTools} from 'redux-devtools-extension';

import reducer from './reducers'
import {AppContainer} from 'react-hot-loader'

export const store = createStore(reducer,
    composeWithDevTools(applyMiddleware(thunk)));

const render = Component => {
    ReactDOM.render(
        <AppContainer>
            <Provider store={store}>
                <Component/>
            </Provider>
        </AppContainer>,
        document.getElementById('root'),
    )
};

render(App);

// webpack Hot Module Replacement API
if (module.hot) {
    module.hot.accept('./containers/App', () => {
        // if you are using harmony modules ({modules:false})
        render(App);
        // in all other cases - re-require App manually
        render(require('./containers/App'))
    })
}
