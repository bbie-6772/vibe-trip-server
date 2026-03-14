# VibeTrip Server

> 여행의 감성을 음악으로 아카이빙하는 서비스, VibeTrip의 백엔드 서버

## 프로젝트 소개

VibeTrip은 여행의 순간을 시각(사진)과 청각(음악)으로 결합하여 기록하는 **감성 아카이빙 서비스**입니다.

기존 SNS의 전시성 기록에서 벗어나, 유저 본인만을 위한 밀도 높은 개인적 기록 공간을 제공합니다. AI가 사용자의 여행 바이브, 나라, 사진을 분석하여 맞춤형 플레이리스트와 앨범 제목을 추천하고, 미드저니를 활용한 예술적 앨범 아트워크 생성 기능을 제공합니다.

## 기술 스택

| 분류 | 기술 |
|------|------|
| **Language** | Kotlin 2.2.21 |
| **Framework** | Spring Boot 4.0.3 |
| **Build Tool** | Gradle (Kotlin DSL) |
| **Database** | H2 (개발), MySQL (운영) |
| **ORM** | Spring Data JPA, Hibernate |
| **API Docs** | SpringDoc OpenAPI (Swagger UI) |
| **Monitoring** | Spring Actuator, Prometheus, Micrometer |
| **Logging** | Kotlin Logging |

## 실행 가이드

### 사전 요구사항

- JDK 21 이상
- Gradle 8.x (또는 Gradle Wrapper 사용)

### 로컬 실행

```bash
# 프로젝트 클론
git clone https://github.com/your-org/VibeTripServer.git
cd VibeTripServer

# 환경 변수 파일 생성
vim .env
# .env 파일을 열어 실제 값으로 수정

# 애플리케이션 실행 (Gradle Wrapper 사용)
./gradlew bootRun
```

Windows의 경우:
```cmd
gradlew.bat bootRun
```

## 프로젝트 구조

```
src/main/kotlin/com/vibetrip/vibetripserver/
├── common/
│   ├── advice/          # 전역 예외 처리
│   ├── entity/          # 공통 엔티티 (BaseEntity)
│   ├── exception/       # 예외 클래스 및 에러 코드
│   └── log/             # 로깅 유틸리티
├── config/
│   ├── docs/            # Swagger 설정
│   └── jpa/             # JPA 설정
├── support/
│   └── response/        # API 응답 포맷
└── VibeTripServerApplication.kt
```

## API 응답 포맷

모든 API 응답은 `ApiResponse<T>` 형식을 따릅니다.

### 응답 구조

```json
{
  "resultType": "SUCCESS" | "ERROR",
  "data": <T> | null,
  "error": <ErrorMessage> | null
}
```

### 성공 응답

데이터가 있는 경우:
```json
{
  "resultType": "SUCCESS",
  "data": {
    "id": 1,
    "name": "example"
  },
  "error": null
}
```

데이터가 없는 경우:
```json
{
  "resultType": "SUCCESS",
  "data": null,
  "error": null
}
```

### 에러 응답

```json
{
  "resultType": "ERROR",
  "data": null,
  "error": {
    "errorCode": "E400",
    "message": "잘못된 접근 경로입니다.",
    "data": null
  }
}
```

### 에러 코드

| 코드 | HTTP Status | 설명 |
|------|-------------|------|
| `E400` | 400 Bad Request | 잘못된 접근 경로입니다. |
| `E401` | 401 Unauthorized | 리소스에 접근하기 위한 인증이 필요합니다. |
| `E403` | 403 Forbidden | 인증에 실패했습니다. / 해당 리소스에 대한 권한이 없습니다. |
| `E404` | 404 Not Found | 해당 데이터를 찾을 수 없습니다. |
| `E409` | 409 Conflict | 요청이 충돌했습니다. 다시 시도해주세요. |
| `E429` | 429 Too Many Requests | 너무 많은 요청을 보냈습니다. |
| `E500` | 500 Internal Server Error | 알 수 없는 오류가 발생했습니다. |