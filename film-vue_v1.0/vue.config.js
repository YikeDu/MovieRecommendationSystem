module.exports = {
    devServer: {
        host: 'localhost',
        port: 38388,
        proxy: {
            '/api': {
                target: 'http://localhost:8080'
            }
        }
    }
};