import React from 'react'
import './O_App.css'
import M_Contact from '../M_Contact/M_Contact';
import A_Property from "../A_Property/A_Property";
import A_LoadButton from "../A_LoadButton/A_LoadButton";

export default class O_App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            list: []
        };
    }

    componentWillUpdate() {}
    componentDidUpdate() {}

    load() {
        let req = new XMLHttpRequest();

        req.open('GET', './controller');
        req.setRequestHeader('command', 'list');

        req.setRequestHeader('args', '');

        let app = (() => this)();

        req.onreadystatechange = function() {
            if(this.readyState !== 4) return;
            let json = this.getResponseHeader('contacts');
            let list = JSON.parse(json);
            app.setList.apply(app, [list]);
        };

        req.send();
    }

    setList(list) {
        let newList = [];
        let c = 0;
        let cc = 0;
        for(let el of list) {
            let pr = [];
            for(let p in el) {
                if(!el.hasOwnProperty(p) || typeof(el[p]) !== 'string') continue;
                pr.push(
                    <A_Property value={el[p]} key={cc++}/>
                );
            }
            newList.push(
                <M_Contact properties={pr} key={c++}/>
            );
        }
        this.setState({
            list: newList
        });
    }

    render() {
        return (
            <div className={'app'}>
                <A_LoadButton o_app={this}/>
                <div className={'contact-list'}>
                    {this.state.list}
                </div>
            </div>
        );
    }
}
