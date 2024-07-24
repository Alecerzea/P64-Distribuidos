const net = require('net');

const client = net.createConnection(3000, 'localhost', () => {
  console.log('Connected to server');
  client.write('Hello from client!');
});

client.on('data', (data) => {
  console.log(`Received data from server: ${data}`);
});

client.on('close', () => {
  console.log('Disconnected from server');
});