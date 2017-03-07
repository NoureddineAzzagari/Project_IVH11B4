import React from 'react';
import {Link, browserHistory} from 'react-router';
import {getMovies} from '../../actions/movies';

export class MovieOverView extends React.Component{
  constructor(props){
      super(props);
      this.state = {movies: []};
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
      return <div className="row overview-row">
          {this.state.movies.map((movie, index) =>{
            return <div className="col-md-2 col-sm-4 col-xs-6 col-lg-1" key={index}>
              <div className="cardWrap" onClick={() => {browserHistory.push(`movie/detail/${index}`)}}>
                <img src={movie.imgUrl} className="overview-img" />
                <div className="description">
                  <p className="title">
                    <b>{movie.title}</b>
                  </p>
                  <p className="release-year">
                    {movie.releaseDate}
                  </p>
                </div>

              </div>
            </div>
          })}
        </div>
  }
}