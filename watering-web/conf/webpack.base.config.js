import Config from 'webpack-config';
import HtmlWebpackPlugin from 'html-webpack-plugin';

const webpack = require('webpack');

export default new Config().merge({
    entry: './src/index.js',
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader",
                    options: {
                        "presets": ["@babel/preset-env","@babel/preset-react"],
                        "plugins": [
                            "@babel/plugin-proposal-class-properties"
                        ]

                    }
                }
            }
        ]
    },
    resolve: {
        extensions: ['*', '.js', '.jsx']
    },
    output: {
        path: __dirname + '/dist',
        publicPath: '/',
        filename: 'bundle.js'
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        new HtmlWebpackPlugin({
            template: './index.html',
            inject: "body"
        })
    ],
    devServer: {
        contentBase: './public',
        hot: true
    }
});