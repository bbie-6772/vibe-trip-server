# ① JDK가 포함된 환경에서
FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /app

# ② gradlew로 의존성 먼저 다운로드 (캐시 최적화)
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle.kts settings.gradle.kts ./
RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon || true

# ③ 소스 복사 후 JAR 파일로 빌드
COPY src ./src
RUN ./gradlew bootJar --no-daemon -x test
#                                  ↑ 테스트는 건너뜀 (빌드 속도 향상)

# ④ 실행용 가벼운 이미지로 갈아탐 (JRE만 포함)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# ⑤ 빌드된 JAR만 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# ⑥ 컨테이너 시작 시 실행할 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]