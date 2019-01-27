import React from 'react'
import './A_AddButton.css'

export default function A_AddButton(props) {
    return (
        <button className={'add-button'} onClick={props.o_app.onAdd.bind(props.o_app)}>{props.text}</button>
    );
}
