const http = require("http");
const httpProxy = require("http-proxy");
const { createProxyMiddleware } = require("http-proxy-middleware");

const PORT = 8090; // Port for your proxy server

// Create a proxy instance
const proxy = httpProxy.createProxyServer({});

// Create a basic HTTP server
const server = http.createServer((req, res) => {
  // Proxy all requests with path starting with '/api'
  if (req.url.startsWith("/api")) {
    // Replace with your backend server URL
    req.url = req.url.replace(/^\/api/, "");
    proxy.web(req, res, { target: "http://localhost:8080" }); // Replace with your backend URL
  } else {
    res.writeHead(404, { "Content-Type": "text/plain" });
    res.end("Request path must start with /api");
  }
});

// Listen on the specified port
server.listen(PORT, () => {
  console.log(`Proxy server listening on port ${PORT}`);
});
