## 사전 실행환경
- kakao : https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-blog
- naver : https://developers.naver.com/docs/serviceapi/search/blog/blog.md
를 확인하고 API키를 발급받아 환경변수에 넣어주어야합니다.

## TODO

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
- [ ] 0.11.x DB컨트롤은 JPA를 이용하여 구현한다. (중요!! 0.8.x 다시 확인)
- [ ] 0.12.x 예외 처리에 대해서 좀 더 고도화 합니다.

