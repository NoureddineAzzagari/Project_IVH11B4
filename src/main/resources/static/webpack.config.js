var path = require('path');
var ExtractTextPlugin = require('extract-text-webpack-plugin');


module.exports = {
  entry: './index.js',
  output: { path: __dirname, filename: '/bundle/bundle.js' },

  devServer: {
    inline: true,
    port: 3000
  },
  
  devtool: 'source-map',  

  module: {
    loaders: [
      {        
        loader: 'babel-loader',
        exclude: /node_modules/,
        query: {
          presets: ['es2015', 'react']
        }
      }
    ]
  },
};
