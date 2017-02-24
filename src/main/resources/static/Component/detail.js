import React from 'react';
import {getMovieById} from './../actions/movies';

export class Detail extends React.Component{

    constructor(props){
        super(props);
        this.state = {
          id: this.props.params.id,
          name: "",
          content: "",
          img: ""
        }
    }

    componentDidMount(){
      getMovieById(this.state.id).then((json)=>{
        this.updateDetail(json);
      });
    }

    updateDetail(json){
      this.setState(json);
    }

    render(){
        return <div>
          <img src={this.state.img}/>
        </div>
    }
}
