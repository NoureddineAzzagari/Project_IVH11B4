import React from 'react';
import {Link, browserHistory} from 'react-router';
import {getMovies} from './../actions/movies';

export class OverView extends React.Component{
    constructor(props){
        super(props);
        this.state = {movies: []}
        this.updateMovies = this.updateMovies.bind(this);       
        
    }

    componentDidMount(){
        getMovies().then((json)=>{
            this.updateMovies(json);
        });
    }

    updateMovies(movies){
        this.setState({movies: movies});
    }

    render(){
        return <div className="row">
            {this.state.movies.map((movie, index) =>{
                return <div className="col-md-2" key={movie.id}><p className="hidden">{movie.title}</p><img src={movie.imgUrl} /></div>
            })}
        </div>
    }
}
