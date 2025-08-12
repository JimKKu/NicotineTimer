docker run -d \
  -p 15503:8080 \
  -v $(pwd):/app \
  --name iTimer-Server \
  jim/openjdk-17 \
  --spring.config.additional-location=file:/app/config/