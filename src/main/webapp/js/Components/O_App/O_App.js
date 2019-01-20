import React from 'react'
import './O_App.css'
import M_Contact from '../M_Contact/M_Contact';
import A_Property from "../A_Property/A_Property";
import A_LoadButton from "../A_LoadButton/A_LoadButton";
import O_ContactList from "../O_ContactList/O_ContactList";

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
        debugger;

        req.onreadystatechange = function() {
            debugger;
            if(this.readyState !== 4) return;
            let json = this.getResponseHeader('contacts');
            let list = JSON.parse(json);
            app.setList.apply(app, [list]);
        };

        req.send();
    }

    setList(list) {
        let newList = [];
        for(let el of list) {
            let pr = [];
            for(let p in el) {
                if(!el.hasOwnProperty(p) || typeof(el[p]) !== 'string') continue;
                pr.push(
                    <A_Property value={el[p]} key={p}/>
                );
            }
            newList.push(
                <M_Contact properties={pr} key={el}/>
            );
        }
        debugger;
        this.setState({
            list: newList
        });
    }

    render() {
        debugger;
        return (
            <div className={'app'}>
                <A_LoadButton o_app={this}/>
                <O_ContactList list={this.state.list}/>
            </div>
        );
    }
}
