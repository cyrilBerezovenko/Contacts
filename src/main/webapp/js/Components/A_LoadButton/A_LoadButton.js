import React from 'react'
import './A_LoadButton.css'

export default function A_LoadButton(props) {
    debugger;
    return (
        <button className={'load-button'} onClick={props.o_app.load.bind(props.o_app)}>{props.text}</button>
    );
}
