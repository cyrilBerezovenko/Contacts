import React from 'react'
import './A_Property.css'

export default function A_Property(props) {
    return (
        <div className='property'>
            <p className='property_value'><span>{props.name}</span>: {props.value}</p>
        </div>
    );
}