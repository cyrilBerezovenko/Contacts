import React from 'react'
import './O_ContactList.css'

export default function O_ContactList(props) {
    return (
        <div className='contact-list'>
            {props.list}
        </div>
    );
}