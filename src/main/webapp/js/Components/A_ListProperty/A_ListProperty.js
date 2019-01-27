import React from 'react'
import './A_ListProperty.css'

export default class A_ListProperty extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {
            name: props.name
        };
        this.setList(props.list);
    }

    setList(list) {
        let a = [];
        for(let i in list) {
            a.push(<li key={i}>{list[i]}</li>);
        }
        this.state.list = a;
    }
    
    render() {
        return (
            <div className='list-property'>
                <p className='list-property-name'>{this.state.name}: </p>
                <ul className={'list-property-ul'}>{this.state.list}</ul>
                <button className='property_add-button' onClick={this.state.onClick}>+</button>
            </div>
        );
    }
}