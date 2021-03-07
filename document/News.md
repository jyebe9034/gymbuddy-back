# News API

### 전체 대외뉴스 갯수조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectNewsTotalCount
PATH: /api/news/totalCount
PARAM: 없음
```
* 결과구조
```
{
    "totalCount": 전체 대외뉴스 갯수
}
```
* 결과 에시
```
{
    "totalCount": 15
}
```

### 전체 대외뉴스 목록 조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectNewsList
PATH: /api/news/all/{page}
*** page는 몇번째 페이지인지의 값으로 첫번째 페이지는 0, 두번째 페이지는 1 이런식으로 증가
PARAM: 없음
```
* 결과구조
```
[
    {
        "createDate": 생성일
        "createId": 생성한 아이디,
        "updateDate": 수정일,
        "updateId": 수정한 아이디,
        "id": 아이디(JPA 생성),
        "title": 제목,
        "contents": 내용,
        "imgPath": 이미지 경로,
        "imgName": 이미지 명
    },
    {
        "createDate": 생성일
        "createId": 생성한 아이디,
        "updateDate": 수정일,
        "updateId": 수정한 아이디,
        "id": 아이디(JPA 생성),
        "title": 제목,
        "contents": 내용,
        "imgPath": 이미지 경로,
        "imgName": 이미지 명
    },
    ...
]
```
* 결과 예시
```
[
    {
        "createDate": "2021-02-06T20:11:46.226",
        "createId": test,
        "updateDate": "2021-02-06T20:11:46.226",
        "updateId": test,
        "id": 1,
        "title": "대외뉴스1",
        "contents": "대외뉴스 입니다.",
        "imgPath": "/resources/static/img/news/1612609906203_테스트.png",
        "imgName": "테스트.png"
    },
    {
        "createDate": "2021-02-06T20:12:06.899",
        "createId": test,
        "updateDate": "2021-02-06T20:12:06.899",
        "updateId": test,
        "id": 2,
        "title": "대외뉴스2",
        "contents": "대외뉴스2 입니다.",
        "imgPath": "/resources/static/img/news/1612609926868_테스트.png",
        "imgName": "테스트.png"
    },
]
```

### 메인 노출 대외뉴스 조회(최근 5개만)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectMainNewsList
PATH: /api/news/mainAll
PARAM: 없음
```
* 결과구조
```
[
    {
        "createDate": 생성일
        "createId": 생성한 아이디,
        "updateDate": 수정일,
        "updateId": 수정한 아이디,
        "id": 아이디(JPA 생성),
        "title": 제목,
        "contents": 내용,
        "imgPath": 이미지 경로,
        "imgName": 이미지 명
    },
    {
        "createDate": 생성일
        "createId": 생성한 아이디,
        "updateDate": 수정일,
        "updateId": 수정한 아이디,
        "id": 아이디(JPA 생성),
        "title": 제목,
        "contents": 내용,
        "imgPath": 이미지 경로,
        "imgName": 이미지 명
    },
    ...
]
```
* 결과 예시
```
[
    {
        "createDate": "2021-02-06T20:13:16.547",
        "createId": test,
        "updateDate": "2021-02-06T20:13:16.547",
        "updateId": test,
        "id": 13,
        "title": "대외뉴스13",
        "contents": "대외뉴스13 입니다.",
        "imgPath": "/resources/static/img/news/1612609996515_테스트.png",
        "imgName": "테스트.png"
    },
    {
        "createDate": "2021-02-06T20:13:11.248",
        "createId": test,
        "updateDate": "2021-02-06T20:13:11.248",
        "updateId": test,
        "id": 12,
        "title": "대외뉴스12",
        "contents": "대외뉴스12 입니다.",
        "imgPath": "/resources/static/img/news/1612609991217_테스트.png",
        "imgName": "테스트.png"
    },
    {
        "createDate": "2021-02-06T20:13:05.785",
        "createId": test,
        "updateDate": "2021-02-06T20:13:05.785",
        "updateId": test,
        "id": 11,
        "title": "대외뉴스11",
        "contents": "대외뉴스11 입니다.",
        "imgPath": "/resources/static/img/news/1612609985756_테스트.png",
        "imgName": "테스트.png"
    },
    {
        "createDate": "2021-02-06T20:12:59.172",
        "createId": test,
        "updateDate": "2021-02-06T20:12:59.172",
        "updateId": test,
        "id": 10,
        "title": "대외뉴스10",
        "contents": "대외뉴스10 입니다.",
        "imgPath": "/resources/static/img/news/1612609979065_테스트.png",
        "imgName": "테스트.png"
    },
    {
        "createDate": "2021-02-06T20:12:51.698",
        "createId": test,
        "updateDate": "2021-02-06T20:12:51.698",
        "updateId": test,
        "id": 9,
        "title": "대외뉴스9",
        "contents": "대외뉴스9 입니다.",
        "imgPath": "/resources/static/img/news/1612609971667_테스트.png",
        "imgName": "테스트.png"
    }
]
```

### 대외뉴스 상세
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectNewsDetail
PATH: /api/news/detail/{id}
*** id는 JPA에서 생성된 값으로 목록에서 가지고 있는 값을 넘겨주면 됌.
PARAM: 없음
```
* 결과구조
```
{
    "createDate": 생성일
    "createId": 생성한 아이디,
    "updateDate": 수정일,
    "updateId": 수정한 아이디,
    "id": 아이디(JPA 생성),
    "title": 제목,
    "contents": 내용,
    "imgPath": 이미지 경로,
    "imgName": 이미지 명
}
```
* 결과 예시
```

```

### 대외뉴스 등록
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : POST
METHOD NAME: insertNews
PATH : /api/admin/news/new
PARAM : Map<String, Object>
=== PARAM 설명 ===
title: 제목
contents: 내용
file: 이미지 파일 
```
* 결과구조
```
{
    "id": 생성된 아이디 값
}
```
* 결과 예시
```
{
    "id": 2
}
```

### 대외뉴스 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : PUT
METHOD NAME: updateNews
PATH : /api/admin/news/update/{id}
PARAM : Map<String, Object>
=== PARAM 설명 ===
title: 제목
contents: 내용
file: 이미지 파일
*** 수정 시에는 모든 값을 넘길 필요는 없음. 수정할 값만 넘겨도 수정 가능함.
```
* 결과구조
```
{
    "result": 수정 결과(true or false)
}
```
* 결과 예시
```
{
    "result": true
}
```

### 대외뉴스 삭제
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : DELETE
METHOD NAME: deleteNews
PATH : /api/admin/news/delete
PARAM : List<Integer>
=== PARAM 설명 ===
아이디 배열을 넘기면 됌
```
* 파라미터 구조
```
[아이디, 아이디, 아이디]
```
* 결과구조
```
{
    "result": 결과값
}
```
* 결과 예시
```
{
    "result": "success"
    *** 현재 모든 결과값을 성공으로 보내고 있음.
}
```