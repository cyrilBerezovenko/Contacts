import React from 'react'
import './M_Contact.css'

export default class M_Contact extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            properties: props.properties
        };
    }

    render() {
        return (
            <div className='contact'>
                {this.state.properties}
            </div>
        );
    }
}