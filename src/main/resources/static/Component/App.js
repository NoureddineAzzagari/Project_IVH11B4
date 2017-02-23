import React from 'react';
import { Link, browserHistory } from 'react-router';

export function App({ children }) {
    return (
        <div>
            <div style={{ marginTop: '1.5em' }}>{children}</div>
        </div>
    )
}
