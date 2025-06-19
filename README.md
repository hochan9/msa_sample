# Feign 과 WebFlux 를 연습하기 위한 공간입니다.
 ```shell
  docker-compose up -d
 ``` 
- 위 명령어 호출시 localhost:33308 포트로 mariaDB 가 구동됩니다.
- Mysql 은 R2DBC 에서 공식 지원하지 않기에 MariaDB 로 구성했습니다.
  - [Azure Database for MySQL에서 Spring Data R2DBC 사용](https://learn.microsoft.com/ko-kr/azure/developer/java/spring-framework/configure-spring-data-r2dbc-with-azure-mysql)
  - 위에서 확인은 가능한 것으로 보입니다.
- 각각 스키마는
  - feign_user
  - feign_wallet
  - reactor_user
  - reactor_wallet
- 이고 각각 8080~8083 포트로 접근됩니다.
- user 에서 생성하고 각각 트랜잭션 테스트 방법이 있습니다.
- 날씨와 시간 api 를 통해 외부 api 호출하는 방법도 존재합니다.