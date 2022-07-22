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

### 1. 상품목록
<img src="https://user-images.githubusercontent.com/38212743/180378611-109f9450-4e17-422d-8c9f-d13ab6748f09.png"  width="700" height="400"/>
<img src="https://user-images.githubusercontent.com/38212743/180378697-94753540-c722-4f1a-b026-5a5353939ab4.png"  width="700" height="400"/>
<img src="https://user-images.githubusercontent.com/38212743/180378738-0005dc4c-c9af-4d5c-a7f3-41874bd21519.png"  width="700" height="400"/>
<img src="https://user-images.githubusercontent.com/38212743/180378767-1bf84b53-bdc1-4ca6-81c3-2881a59f2379.png"  width="700" height="400"/>
<img src="https://user-images.githubusercontent.com/38212743/180378807-dae4cf28-a313-4437-ba53-c3a1c32bb0b3.png"  width="700" height="400"/>
<img src="https://user-images.githubusercontent.com/38212743/180378789-d53a36fe-526b-408a-8281-5f0092b308f5.png"  width="700" height="400"/>
<img src="https://user-images.githubusercontent.com/38212743/180378827-79c31867-86f0-4dcb-a302-d99a64479188.png"  width="700" height="400"/>


<br>

### 2. 장바구니

<img src="https://user-images.githubusercontent.com/38212743/180378886-f2a2c295-5f38-4dd7-b86f-ab04ee548777.png"  width="700" height="400"/>
<img src="https://user-images.githubusercontent.com/38212743/180380203-21e8218f-969e-4df2-a7a1-ba26dcf8e02c.png"  width="700" height="400"/>
<img src="https://user-images.githubusercontent.com/38212743/180379961-98ec3d17-edf7-497f-a9ec-01385361179d.png"  width="700" height="400"/>
<img src="https://user-images.githubusercontent.com/38212743/180379930-a36fb69e-58a2-4d6f-a2c3-7d0a0b73e9a3.png"  width="700" height="400"/>
<img src="https://user-images.githubusercontent.com/38212743/180379981-a9f5680f-8105-4c62-b315-95465ad868f1.png"  width="700" height="400"/>
<img src="https://user-images.githubusercontent.com/38212743/180378954-7cbdf3c9-e68f-4bc7-92f0-e8e7b0c449e7.png"  width="700" height="400"/>
