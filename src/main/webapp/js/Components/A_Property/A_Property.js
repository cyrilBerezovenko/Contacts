import React from 'react'
import './A_Property.css'

export default function A_Property(props) {
    return (
        <div className='property'>
            <p className='property_value'>{props.value}</p>
            <button className='property_add-button'>{props.onClick}</button>
        </div>
    );
}