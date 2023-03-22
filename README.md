# Search Blog Service

---


## API

### 블로그 검색 API

이 API를 사용하면 사용자는 주어진 쿼리 매개변수를 기반으로 블로그 게시물을 검색할 수 있습니다.

##### Endpoint

|          항목 | 내용           |
|------------:|:-------------|
|         URL | /blog/search |
| HTTP Method | GET          |

#####  [Request](https://github.com/letsbe-x/search-blog-service/blob/main/requests/rest/BlogSearch.http)

- query(필수): 관련 블로그 게시물을 찾기 위한 검색어 문자열입니다.
- 정렬(선택, 기본값: "정확도"): 검색 결과를 정렬하는 정렬 방법입니다. 사용 가능한 옵션: "accuracy", "recently" 등
- page (선택, 기본값: 1): 검색 결과의 페이지 번호입니다.
- size (선택, 기본값: 10): 페이지당 표시할 블로그 게시물 수입니다.
- provider (선택, 기본값: "kakao"): 블로그 게시물 제공자. 사용 가능한 옵션: "kakao", "naver", "total(통합 검색)" 등

###### Response

해당 API는 블로그 검색 결과를 가지고 있는 `Flux<BlogPostDto>`를 반환합니다. 

```
http://localhost:8080/blog/search?query=윤하

HTTP/1.1 200 OK
transfer-encoding: chunked
Content-Type: application/json

Response code: 200 (OK); Time: 58ms (58 ms); Content length: 3833 bytes (3.83 kB)
```
```json
[
  ...
  {
    "title": "&lt; <b>윤하</b> 앵콜 콘서트 c/2023YH 후기! &gt;",
    "contents": "&lt; <b>윤하</b> 앵콜 콘서트 c/2023YH 후기. &gt; Index. Prologue. 실패한 티켓팅, 양일 스탠딩이라니... 내가? Chapter 1. 즐기다 보니 금세 끝나버린 첫콘. Chapter 2. 막콘, 오늘을 잊지 않을게... Chapter 3. 콘서트를 빛나게 만드는 우리들, &lt;Y.HOLICS&gt; Chapter 4. 체조? 고척? 잠실? 그건 절대 꿈이 아니야... Epilogue. 끝...",
    "url": "http://youniverse-for-younha.tistory.com/10",
    "datetime": "2023-03-19T09:41:31Z",
    "searchProvider": "KAKAO"
  }
  ...
]
```

### 블로그 실시간 순위 API

이 API는 BlogRankService에 정의된 기준에 따라 블로그 순위를 검색합니다.

##### Endpoint

|          항목 | 내용         |
|------------:|:-----------|
|         URL | /blog/rank |
| HTTP Method | POST       |


##### [Request](https://github.com/letsbe-x/search-blog-service/blob/main/requests/rest/BlogRank.http)

- 이 API 호출에는 요청 Parameter나 본문이 필요하지 않습니다.

###### Response

해당 API는 사용자의 요청키워드에 대해서 상위 10개에 대해서  `Mono<BlogRankResponse>`를 반환합니다.

```
http://localhost:8080/blog/rank

HTTP/1.1 200 OK
transfer-encoding: chunked
Content-Type: text/event-stream;charset=UTF-8

Response code: 200 (OK); Time: 24ms (24 ms)
```
```json
{
  "ranks": [
    {
      "rank": 1,
      "keyword": "테스트1",
      "count": 32
    },
    {
      "rank": 2,
      "keyword": "테스트2",
      "count": 16
    },
    {
      "rank": 3,
      "keyword": "테스트3",
      "count": 8
    },
    {
      "rank": 4,
      "keyword": "테스트4",
      "count": 4
    },
    {
      "rank": 5,
      "keyword": "윤하",
      "count": 3
    },
    {
      "rank": 6,
      "keyword": "테스트5",
      "count": 2
    },
    {
      "rank": 7,
      "keyword": "테스트6",
      "count": 1
    }
  ]
}
```


### 프로젝트 관련 구조
```
com.letsbe.blog
├── infrastructure
│   ├── search
│   └── rank
├── domain
│   ├── search
│   └── rank
├── applications
│   ├── search
│   └── rank
└── interfaces
    ├── search
    └── rank
```

### [Dependency](https://github.com/letsbe-x/search-blog-service/blob/main/build.gradle.kts)
- Spring Boot 3.0.4
- JDK 17

#### [Infrastructure](https://github.com/letsbe-x/search-blog-service/blob/main/subprojects/infrastructure/build.gradle.kts)

#### [DOMAIN](https://github.com/letsbe-x/search-blog-service/blob/main/subprojects/domain/build.gradle.kts)

#### [Application](https://github.com/letsbe-x/search-blog-service/blob/main/subprojects/applications/build.gradle.kts)

#### [Interface](https://github.com/letsbe-x/search-blog-service/blob/main/subprojects/interfaces/build.gradle.kts)



## [Release Note](https://github.com/letsbe-x/search-blog-service/releases)

- [x] 0.1.0 멀티모듈로 프로젝트를 구성한다.
- [x] 0.2.x 키워드를 통해 카카오 블로그 검색을 할 수 있어야한다.
- [x] 0.3.x 검색결과에서 Sort 기능을 제공해야합니다.
- [x] 0.4.x 검색결과는 최신순으로 정렬되어야합니다.
- [x] 0.5.x 검색 결과는 Pagenation 형태로 제공해야합니다.
- [x] 0.6.x 패키지명, API 수정 - search보다 blog가 먼저있어야합니다.
- [x] 0.7.x 인기 검색어 목록 API, 키워드 블로그 검색 API와 연동
- [x] 0.8.x 인메모리 DB 추가 (Redis)
- [x] 0.9.x 네이버 블로그 검색 API를 이용하여 검색결과를 가져온다.
- [x] 0.10.x 카카오 블로그 검색 API가 문제가 생길경우, 블로그 검색 API를 통해 데이터 제공
- [x] 0.11.x DB컨트롤은 JPA를 이용하여 구현한다. (중요!! 0.8.x 다시 확인)
- [x] 0.12.x Exception Handling 추가



## 사전 실행환경

### release

[execute-jar](https://github.com/letsbe-x/search-blog-service/blob/main/execute/search-blog-service-0.11-SNAPSHOT.jar)


### 환경변수

- kakao : https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-blog
- naver : https://developers.naver.com/docs/serviceapi/search/blog/blog.md
  를 확인하고 API키를 발급받아 환경변수에 넣어주어야합니다.
- request-sample : [http-client.env.json](https://github.com/letsbe-x/search-blog-service/blob/main/requests/http-client.env.json)을 참고하여, 환경변수를 설정해주세요.

### commit-lint를 위한 husky가 설치되어있습니다.

```bash
$ npm install
```
