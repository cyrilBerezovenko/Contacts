import React from 'react'
import './A_SocialNetworksProperty.css'
import A_ListProperty from "../A_ListProperty/A_ListProperty";
import A_Property from "../A_Property/A_Property";

export default class A_SocialNetworksProperty extends A_ListProperty {

    setList(list) {
        let a = [];
        for(let i in list) {
            a.push(<A_Property name={list[i]['socialNetwork'].toLowerCase()} value={list[i]['link']} key={i}/>);
        }
        this.state.list = a;
    }
}