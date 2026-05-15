module github.com/example/vuln-go-app

go 1.19

require (
	// Vulnerable dependencies (for testing)
	github.com/dgrijalva/jwt-go v3.2.0+incompatible
	github.com/gin-gonic/gin v1.6.3
	golang.org/x/crypto v0.0.0-20200622213623-75b288015ac9
	golang.org/x/net v0.0.0-20190404232315-eb5bcb51f2a3
	golang.org/x/text v0.3.0
	gopkg.in/yaml.v2 v2.2.2

	// Safe dependencies (latest versions, no known CVEs)
	github.com/google/uuid v1.6.0
	github.com/stretchr/testify v1.9.0
	go.uber.org/zap v1.27.0
)
