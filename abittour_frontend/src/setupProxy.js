const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    "/applications/offer",
    createProxyMiddleware({
      target: "http://localhost:8080",
      changeOrigin: true,
      headers: {
        "Access-Control-Allow-Origin": "*", // Example of setting CORS header
        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE", // Example of setting allowed methods
        "Access-Control-Allow-Headers": "Content-Type, Authorization", // Example of setting allowed headers
      },
    })
  );
};
