module.exports = {
    devServer: {
        host: 'localhost',
        port: 38388,
        proxy: {
            '/': {
                target: 'http://localhost:40015'
            }
        }
    }
};