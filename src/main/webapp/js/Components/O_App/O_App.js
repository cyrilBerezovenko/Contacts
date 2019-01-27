import React from 'react'
import './O_App.css'
import M_Contact from '../M_Contact/M_Contact';
import A_Property from "../A_Property/A_Property";
import A_ListProperty from "../A_ListProperty/A_ListProperty";
import A_SocialNetworksProperty from "../A_SocialNetworksProperty/A_SocialNetworksProperty";
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
                        <button className='send-button' onClick={addFunc}>Add</button>
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
                res[inp.name] = inp.value.trim();
            } else if(inp.name === 'socialNetworks') {
                res[inp.name] = [];
                for(let line of inp.value.split('\n')) {
                    let obj = {};
                    let s = line.trim().split(' ');
                    obj.socialNetwork = s[0].toUpperCase();
                    obj.link = s[1] || '';
                    res[inp.name].push(obj);
                }
            } else {
                res[inp.name] = [];
                for(let line of inp.value.split('\n')) {
                    res[inp.name].push(line.trim());
                }
            }
        }

        let json = JSON.stringify(res);
        let req = new XMLHttpRequest();
        req.open('GET', './controller?command=add');
        let app = this;

        req.onreadystatechange = function() {
            if(this.readyState !== 4) return;
            app.load();
        };
        console.log(json);
        req.send(json);
    }

    setContacts(list) {
        let newList = [];
        let c = 0;
        let cc = 0;
        for(let el of list) {
            let pr = [];
            for(let p in el) {
                if(!el.hasOwnProperty(p)) continue;
                if(el[p][0]['socialNetwork']) {
                    console.log(el[p]);
                    pr.push(
                        <A_SocialNetworksProperty name={'socialNetworks'} list={el[p]} key={cc++}/>);
                }
                else if(typeof el[p] === 'string') {
                    pr.push(
                        <A_Property name={p} value={el[p]} key={cc++}/>);
                }
                else if(typeof el[p] === 'object') {
                    console.log(el[p]);
                    pr.push(
                        <A_ListProperty name={p} list={el[p]} key={cc++}/>);
                }
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
                <A_AddButton o_app={this} text={'Add'}/>
                <div className={'contact-list'}>
                    {this.state.list}
                </div>
                {this.state.popup}
            </div>
        );
    }
}
