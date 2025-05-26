# Sử dụng image JDK 17 chính thức
FROM maven:3.9.6-eclipse-temurin-21

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép toàn bộ nội dung dự án vào container
COPY . .

# Build ứng dụng bằng Maven Wrapper
RUN mvn clean package -DskipTests

# Chạy file JAR đã build (cần thay bằng đúng tên file JAR thực tế nếu khác)
CMD ["java", "-jar", "./target/spring-boot-non-jwt-1.0.jar"]
