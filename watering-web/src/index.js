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

window.__MUI_USE_NEXT_TYPOGRAPHY_VARIANTS__ = true;

//console.log("store", store.getState());

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

// if (module.hot) {
//     module.hot.accept('./containers/App', () => {
//         require('./containers/App');
//         render(App);
//     });
// }

module.hot.accept();


