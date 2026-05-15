module github.com/example/vuln-go-app

go 1.19

require (
	// Security fix: replaced deprecated dgrijalva/jwt-go with golang-jwt/jwt
	github.com/golang-jwt/jwt/v5 v5.2.1
	github.com/gin-gonic/gin v1.9.1
	golang.org/x/crypto v0.45.0
	golang.org/x/net v0.7.0
	golang.org/x/text v0.3.8
	gopkg.in/yaml.v2 v2.2.8

	// Safe dependencies (latest versions, no known CVEs)
	github.com/google/uuid v1.6.0
	github.com/stretchr/testify v1.9.0
	go.uber.org/zap v1.27.0
)
