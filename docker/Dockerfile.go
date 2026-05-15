# Vulnerable Dockerfile - Go Application  
# DO NOT USE IN PRODUCTION!

# VULNERABILITY: Using older Go version with known CVEs
FROM golang:1.18-alpine

# VULNERABILITY: Running as root
WORKDIR /app

# VULNERABILITY: Unpinned packages, no version locks
RUN apk add --no-cache \
    git \
    gcc \
    musl-dev \
    curl \
    openssh

# VULNERABILITY: Git config allows arbitrary code execution
RUN git config --global url."https://".insteadOf git://

# Copy vulnerable go.mod and go.sum
COPY go.mod go.sum ./

# VULNERABILITY: Downloading dependencies without verification
RUN go mod download

COPY . .

# VULNERABILITY: Building without security flags
RUN go build -o main .

# VULNERABILITY: No scratch/distroless final stage
# VULNERABILITY: Contains build tools in production image

EXPOSE 8080

CMD ["./main"]
