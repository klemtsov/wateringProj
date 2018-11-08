
import Config from 'webpack-config';
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');

export default new Config().extend('webpack.base.config.js').merge({
    mode: "production",
    output: {
        filename: 'bundle.min.js'
    },
    optimization: {
        minimizer: [
            // we specify a custom UglifyJsPlugin here to get source maps in production
            new UglifyJsPlugin({
                cache: true,
                parallel: true,
                uglifyOptions: {
                    compress: false,
                    ecma: 6,
                    mangle: true
                },
                sourceMap: true
            })
        ]
    }
});