"""
Vulnerable Python Application

This application intentionally uses vulnerable dependencies for testing
vulnerability scanning tools.

DO NOT USE IN PRODUCTION!
"""

from flask import Flask, request, render_template_string
import yaml
import pickle
import os
from jinja2 import Template
import requests
from lxml import etree
import paramiko

app = Flask(__name__)

@app.route('/')
def index():
    return "Vulnerable Python Application - For Testing Only!"

# Vulnerable: Jinja2 SSTI (Server-Side Template Injection)
@app.route('/greet')
def greet():
    name = request.args.get('name', 'World')
    # Dangerous: User input directly in template
    template = Template(f"Hello, {name}!")
    return template.render()

# Vulnerable: PyYAML arbitrary code execution (CVE-2020-14343)
@app.route('/parse-yaml', methods=['POST'])
def parse_yaml():
    data = request.data.decode('utf-8')
    # Dangerous: yaml.load without Loader
    result = yaml.load(data)  # Should use yaml.safe_load()
    return str(result)

# Vulnerable: Pickle deserialization RCE
@app.route('/deserialize', methods=['POST'])
def deserialize():
    data = request.data
    # Dangerous: Unpickling untrusted data
    obj = pickle.loads(data)
    return str(obj)

# Vulnerable: Command injection
@app.route('/ping')
def ping():
    host = request.args.get('host', 'localhost')
    # Dangerous: Shell injection
    result = os.popen(f'ping -c 1 {host}').read()
    return result

# Vulnerable: XXE via lxml
@app.route('/parse-xml', methods=['POST'])
def parse_xml():
    data = request.data
    # Dangerous: XXE enabled by default in older lxml
    parser = etree.XMLParser(resolve_entities=True)
    tree = etree.fromstring(data, parser)
    return etree.tostring(tree).decode()

# Vulnerable: SSRF via requests
@app.route('/fetch')
def fetch():
    url = request.args.get('url', 'http://example.com')
    # Dangerous: Fetching arbitrary URLs
    response = requests.get(url)
    return response.text

# Vulnerable: SQL Injection (simulated)
@app.route('/search')
def search():
    query = request.args.get('q', '')
    # Dangerous: String concatenation for SQL
    sql = f"SELECT * FROM users WHERE name = '{query}'"
    return f"Would execute: {sql}"

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5000)
