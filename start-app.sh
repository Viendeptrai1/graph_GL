#!/bin/bash

echo "🚀 Starting GraphQL Application..."

# Start PostgreSQL using Docker Compose
echo "📦 Starting PostgreSQL database..."
docker-compose up -d

# Wait for PostgreSQL to be ready
echo "⏳ Waiting for PostgreSQL to be ready..."
sleep 5

# Check if PostgreSQL is healthy
echo "🔍 Checking PostgreSQL health..."
docker-compose ps

# Start the Spring Boot application
echo "🎯 Starting Spring Boot application..."
mvn spring-boot:run
