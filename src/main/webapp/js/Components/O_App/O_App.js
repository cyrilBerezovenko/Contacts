import React from 'react'
import './O_App.css'
import M_Contact from '../M_Contact/M_Contact';
import A_AddButton from "../A_AddButton/A_AddButton";


export default class O_App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            list: []
        };
        this.load();
    }

    componentWillUpdate() {}
    componentDidUpdate() {}

    load() {
        let req = new XMLHttpRequest();

        req.open('GET', './controller?command=list');

        let app = this;

        req.onreadystatechange = function() {
            if(this.readyState !== 4) return;
            let json = this.responseText;
            let list = JSON.parse(json);
            console.log(json);
            console.log(list);
            app.setContacts.apply(app, [list]);
        };

        req.send('');
    }

    onAdd() {
        let close = (function(){ this.setState({popup: undefined}) }).bind(this);
        let addFunc = this.add.bind(this);
        this.setState({
            popup: (
                <div className={'popup'}>
                    <div className={'popup-content'}>
                        <button className='close-button' onClick={close}>X</button>
                        <form id='popup-form'>
                            <p>Name : </p>
                            <input type='text' name='name'/>
                            <p>Surname : </p>
                            <input type='text' name='surname'/>
                            <p>Skype : </p>
                            <input type='text' name='skype'/>
                            <p>Phone : </p>
                            <textarea name='phone'/>
                            <p>Email : </p>
                            <textarea name='email'/>
                            <p>Social networks : </p>
                            <textarea name='socialNetworks'/>
                        </form>
                        <button className='send-button' onClick={() => {addFunc();close();}}>Add</button>
                    </div>
                </div>
            )
        });
    }

    add() {
        let form = document.querySelector('#popup-form');
        let res = {};

        for(let inp of form.elements) {
            if(inp.tagName === 'INPUT') {
                res[inp.name] = inp.value.trim() || null;
            } else if(inp.name === 'socialNetworks') {
                res[inp.name] = [];
                let lines = inp.value.split('\n');
                if(lines.length === 1 && lines[0] === '')
                    continue;
                for(let line of lines) {
                    let obj = {};
                    let s = line.trim().split(' ');
                    obj.socialNetwork = s[0].toUpperCase();
                    obj.link = s[1] || null;
                    res[inp.name].push(obj);
                }
            } else {
                res[inp.name] = [];
                let lines = inp.value.split('\n');
                if(lines.length === 1 && lines[0] === '')
                    continue;
                for(let line of lines) {
                    res[inp.name].push(line.trim() || null);
                }
            }
        }

        let json = JSON.stringify(res);
        let req = new XMLHttpRequest();
        req.open('POST', './controller?command=add');
        let app = this;

        req.onreadystatechange = function() {
            if(this.readyState !== 4) return;
            if(this.responseText === 'true') {
                let matchingContact = {
                    name: res.name
                };
                app.find(matchingContact,(obj) => (function() {
                    if(this.readyState !== 4) return;
                let list = JSON.parse(this.responseText);
                if(list.length !== 0) {
                    let ind = undefined;
                    for(let i in app.state.list) {
                        if(app.state.list[i].key === res.name) {
                            let lst = app.state.list;
                            delete lst[i];
                            ind = i;
                            app.setState({
                                list: lst
                            });
                            break;
                        }
                    }
                    app.addContact(list[0], ind);
                }
                }).bind(obj));
            }
        };
        console.log(json);
        req.send(json);
    }

    find(matchingContact, callbackfn) {
        let json = JSON.stringify(matchingContact);
        let req = new XMLHttpRequest();
        // should not be POST
        req.open('POST', './controller?command=find');
        req.onreadystatechange = callbackfn(req);
        req.send(json);
    }

    addContact(prop, ind) {
        let newList = this.state.list;
        newList[ind || newList.length] = <M_Contact properties={prop} key={prop.name}/>;
        this.setState({
            list: newList
        });
    }

    setContacts(list) {
        let newList = [];
        for(let el of list) {
            newList.push(
                <M_Contact properties={el} key={el.name}/>
            );
        }
        this.setState({
            list: newList
        });
    }

    render() {
        return (
            <div className={'app'}>
                <A_AddButton o_app={this} text={'Add'}/>
                <div className={'contact-list'}>
                    {this.state.list}
                </div>
                {this.state.popup}
            </div>
        );
    }
}
