# Vulnerable Dependencies Test Repository

**⚠️ WARNING: This repository intentionally contains vulnerable dependencies for security testing purposes only.**

This repo is designed to test vulnerability scanning agents. It includes known vulnerabilities across multiple ecosystems:

## Languages & Vulnerabilities

### Go (`go-app/`)
- `golang.org/x/crypto` v0.0.0-20200622213623 - Multiple CVEs
- `golang.org/x/text` v0.3.0 - CVE-2020-14040 (Infinite loop)
- `golang.org/x/net` v0.0.0-20190404232315 - Multiple CVEs
- `github.com/dgrijalva/jwt-go` v3.2.0 - CVE-2020-26160

### Node.js (`node-app/`)
- `lodash` 4.17.20 - CVE-2021-23337 (Command Injection)
- `axios` 0.21.1 - CVE-2021-3749 (ReDoS)
- `minimist` 1.2.5 - CVE-2021-44906 (Prototype Pollution)
- `node-fetch` 2.6.1 - CVE-2022-0235 (Information Exposure)
- `express` 4.17.1 - Multiple CVEs

### Java/Maven (`java-app/`)
- `log4j-core` 2.14.1 - CVE-2021-44228 (Log4Shell - CRITICAL)
- `spring-core` 5.3.17 - CVE-2022-22965 (Spring4Shell - CRITICAL)
- `jackson-databind` 2.9.10 - Multiple deserialization CVEs
- `commons-collections` 3.2.1 - CVE-2015-7501 (RCE)

### Python (`python-app/`)
- `django` 2.2.0 - Multiple CVEs including SQL injection
- `requests` 2.25.0 - CVE-2023-32681
- `pyyaml` 5.3 - CVE-2020-14343 (Arbitrary code execution)
- `pillow` 8.0.0 - Multiple CVEs
- `urllib3` 1.25.0 - Multiple CVEs
- `cryptography` 3.3.0 - Multiple CVEs

### Docker (`docker/`)
- Alpine 3.12 (EOL)
- Python 3.8 base (EOL)
- Unpinned packages
- No checksum verification on downloads

## Usage

Use this repository to test vulnerability scanning tools and agents.

```bash
# Clone and test with your scanner
git clone https://github.com/<your-username>/vuln-test-repo
cd vuln-test-repo
# Run your vulnerability scanner
```

## Disclaimer

This repository is for **educational and testing purposes only**. Do not deploy any code from this repository in production environments.
