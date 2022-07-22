# Cart-ProtoType
주문 서비스 간단 프로토타입


<br>

## 🔨 개발환경

- OS : macOS Monterey
- IDE : IntelliJ IDEA Ultimate, WebStorm 
- Backend
    - Spring Boot 2.8.6
    - Java 11
    - JPA
    - queryDSL
    - Gradle
    - MySQL
    - Swagger
- Frontend
    - vue
    - vue/cli
    - bootstrap 5, element-plus, sweetalert2
    - node 16.15.1

<br><br>

## 📌 서비스 소개

1. 개발 목표    
    온라인에서 상품 선택부터 장바구니 구매까지를 경험할 수 있는 주문 서비스의 간단한 프로토타입 웹 서버 구축

<br>

2. 주요 기능
- 상품 테이블에 있는 상품 목록을 조회한다.
- 사용자의 니즈에 따라 상품을 정렬 및 필터링 할 수 있다.
- 장바구니에 상품을 추가, 수정, 삭제할 수 있다.
- 장바구니 내 상품의 금액을 확인하고 구매할 수 있다.

<br><br>

## 🎬 실행방법
1. Database 연결 
	- dumps > sql 파일 적용 
	- Server > src > main > resources > application.yml 
		파일 DB 환경 정보 설정
2. Server – CartApplication 실행
3. localhost:8080 접속


<br><br>

## 🎯 개발 결과