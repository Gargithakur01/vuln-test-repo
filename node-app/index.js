const express = require('express');
const _ = require('lodash');
const axios = require('axios');
const minimist = require('minimist');
const fetch = require('node-fetch');
const jwt = require('jsonwebtoken');
const moment = require('moment');
const marked = require('marked');

const app = express();
app.use(express.json());

// Vulnerable: lodash < 4.17.21 has prototype pollution
app.post('/merge', (req, res) => {
  const obj = {};
  _.merge(obj, req.body);
  res.json(obj);
});

// Vulnerable: Using old axios with ReDoS vulnerability
app.get('/fetch', async (req, res) => {
  try {
    const response = await axios.get(req.query.url);
    res.json(response.data);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// Vulnerable: minimist prototype pollution
app.get('/parse', (req, res) => {
  const args = minimist(req.query.args?.split(' ') || []);
  res.json(args);
});

// Vulnerable: marked XSS before 4.0.10
app.post('/render', (req, res) => {
  const html = marked(req.body.markdown);
  res.send(html);
});

// Vulnerable: jsonwebtoken algorithm confusion
app.post('/verify', (req, res) => {
  try {
    const decoded = jwt.verify(req.body.token, 'secret');
    res.json(decoded);
  } catch (err) {
    res.status(401).json({ error: 'Invalid token' });
  }
});

// Vulnerable: moment ReDoS
app.get('/date', (req, res) => {
  const date = moment(req.query.date).format('YYYY-MM-DD');
  res.json({ date });
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
