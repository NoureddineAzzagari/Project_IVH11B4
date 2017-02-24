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

    hoverTitle(e){
      $(".js-title" + e.target.classList[0]).removeClass("hidden");
    }

    stopHoverTitle(e){
      $(".js-title" + e.target.classList[0]).addClass("hidden");
    }

    goToDetail(e){
      browserHistory.push("detail/"+e.target.classList[0])
    }

    render(){
        return <div className="row">
            {this.state.movies.map((movie, index) =>{
              return <div className="col-md-2" key={index}>
                <h3 className={"hidden hidden-title js-title" + index}>{movie.title}</h3>
                <img onClick={(e) => this.goToDetail(e)} className={index} onMouseOut={(e) => this.stopHoverTitle(e)} onMouseOver={(e) => this.hoverTitle(e)} src={movie.imgUrl} />
              </div>
            })}
        </div>
    }
}
