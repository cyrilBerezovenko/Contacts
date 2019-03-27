import React from 'react'
import './M_Contact.css'
import A_SocialNetworksProperty from "../A_SocialNetworksProperty/A_SocialNetworksProperty";
import A_Property from "../A_Property/A_Property";
import A_ListProperty from "../A_ListProperty/A_ListProperty";

export default class M_Contact extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            properties: this.parseContact(props.properties)
        };
    }

    parseContact(cont) {
        let pr = [];
        for(let p in cont) {
            if(!cont.hasOwnProperty(p) || cont[p] == false || cont[p] === 'null') continue;
            if(cont[p][0]['socialNetwork']) {
                pr.push(
                    <A_SocialNetworksProperty name={'socialNetworks'} list={cont[p]} key={'socialNetworks'}/>);
            }
            else if(typeof cont[p] === 'string') {
                pr.push(
                    <A_Property name={p} value={cont[p]} key={p}/>);
            }
            else if(typeof cont[p] === 'object') {
                pr.push(
                    <A_ListProperty name={p} list={cont[p]} key={p}/>);
            }
        }

        return pr;
    }

    render() {
        return (
            <div className='contact'>
                {this.state.properties}
            </div>
        );
    }
}