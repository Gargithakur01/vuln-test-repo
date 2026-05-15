package main

import (
	"fmt"
	"net/http"

	"github.com/dgrijalva/jwt-go"
	"github.com/gin-gonic/gin"
	"golang.org/x/crypto/bcrypt"
	"gopkg.in/yaml.v2"
)

type Config struct {
	Secret string `yaml:"secret"`
}

func main() {
	r := gin.Default()

	r.GET("/", func(c *gin.Context) {
		// Vulnerable: Using deprecated jwt-go library
		token := jwt.New(jwt.SigningMethodHS256)
		tokenString, _ := token.SignedString([]byte("secret"))

		// Using potentially vulnerable bcrypt
		hash, _ := bcrypt.GenerateFromPassword([]byte("password"), bcrypt.DefaultCost)

		// Using vulnerable yaml.v2
		config := Config{}
		yaml.Unmarshal([]byte("secret: test"), &config)

		c.JSON(http.StatusOK, gin.H{
			"token": tokenString,
			"hash":  string(hash),
		})
	})

	fmt.Println("Server starting on :8080")
	r.Run(":8080")
}
