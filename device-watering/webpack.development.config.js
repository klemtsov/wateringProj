import webpack from 'webpack';
import Config from 'webpack-config';

export default new Config().extend('webpack.base.config.js').merge({
    mode: "development",
    entry: [
        'webpack-hot-middleware/client?reload=true',
        'react-hot-loader/patch',
        __dirname + '/src/index.js'
    ],
    devtool: 'inline-source-map',
    output: {
        filename: 'bundle.js'
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin()
    ]
});