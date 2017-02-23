import React from 'react';
import ReactDom from 'react-dom';
import {createStore, combineReducers} from 'redux';
import {Router, Route, IndexRoute, browserHistory} from 'react-router';
import {OverView} from './Component/Overview';
import {Detail} from './Component/detail';
import {App} from './Component/App';



ReactDom.render(
        <Router history={browserHistory}>
            <Route path="/" component={App}>
                <IndexRoute component={OverView} />
                <Route path="detail" component={Detail} />
            </Route>
        </Router>,
    document.getElementById("app")
);
