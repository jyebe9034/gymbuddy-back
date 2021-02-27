# Notice API

### 전체 공지사항 갯수조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectNoticeTotalCount
PATH: /api/notice/totalCount
PARAM: 없음
```
* 결과구조
```
{
    "totalCount": 전체 공지사항 갯수
}
```
* 결과 에시
```
{
    "totalCount": 15
}
```

### 전체 공지사항 목록 조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectNoticeList
PATH: /api/notice/all/{page}
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
        "createDate": "2021-02-06T19:31:43.684",
        "createId": test,
        "updateDate": "2021-02-06T19:31:43.684",
        "updateId": test,
        "id": 1,
        "title": "공지사항1",
        "contents": "공지사항 1입니다",
        "imgPath": "/resources/static/img/notice1612607503648_테스트.png",
        "imgName": "테스트.png"
    },
    {
        "createDate": "2021-02-06T19:35:05.857",
        "createId": test,
        "updateDate": "2021-02-06T19:35:05.857",
        "updateId": test,
        "id": 2,
        "title": "공지사항2",
        "contents": "공지사항 2입니다",
        "imgPath": "/resources/static/img/notice1612607705826_테스트.png",
        "imgName": "테스트.png"
    },
    ...
]
```

### 메인 노출 공지사항 조회(최근 5개만)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectMainNoticeList
PATH: /api/notice/mainAll
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
        "createDate": "2021-02-06T19:36:16.748",
        "createId": test,
        "updateDate": "2021-02-06T19:36:16.748",
        "updateId": test,
        "id": 13,
        "title": "공지사항13",
        "contents": "공지사항 13입니다",
        "imgPath": "/resources/static/img/notice1612607776715_테스트.png",
        "imgName": "테스트.png"
    },
    {
        "createDate": "2021-02-06T19:36:10.574",
        "createId": test,
        "updateDate": "2021-02-06T19:36:10.574",
        "updateId": test,
        "id": 12,
        "title": "공지사항12",
        "contents": "공지사항 12입니다",
        "imgPath": "/resources/static/img/notice1612607770536_테스트.png",
        "imgName": "테스트.png"
    },
    {
        "createDate": "2021-02-06T19:36:05.351",
        "createId": test,
        "updateDate": "2021-02-06T19:36:05.351",
        "updateId": test,
        "id": 11,
        "title": "공지사항11",
        "contents": "공지사항 11입니다",
        "imgPath": "/resources/static/img/notice1612607765321_테스트.png",
        "imgName": "테스트.png"
    },
    {
        "createDate": "2021-02-06T19:36:00.382",
        "createId": test,
        "updateDate": "2021-02-06T19:36:00.382",
        "updateId": test,
        "id": 10,
        "title": "공지사항10",
        "contents": "공지사항 10입니다",
        "imgPath": "/resources/static/img/notice1612607760347_테스트.png",
        "imgName": "테스트.png"
    },
    {
        "createDate": "2021-02-06T19:35:52.749",
        "createId": test,
        "updateDate": "2021-02-06T19:35:52.749",
        "updateId": test,
        "id": 9,
        "title": "공지사항9",
        "contents": "공지사항 9입니다",
        "imgPath": "/resources/static/img/notice1612607752714_테스트.png",
        "imgName": "테스트.png"
    }
]
```

### 공지사항 상세
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectNoticeDetail
PATH: /api/notice/detail/{id}
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
{
    "createDate": "2021-02-06T19:35:39.487",
    "createId": test,
    "updateDate": "2021-02-06T19:35:39.487",
    "updateId": test,
    "id": 7,
    "title": "공지사항7",
    "contents": "공지사항 7입니다",
    "imgPath": "/resources/static/img/notice1612607739459_테스트.png",
    "imgName": "테스트.png"
}
```

### 공지사항 등록
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : POST
METHOD NAME: insertNotice
PATH : /api/admin/notice/new
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

### 공지사항 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : PUT
METHOD NAME: updateNotice
PATH : /api/admin/notice/update/{id}
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

### 공지사항 삭제
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : DELETE
METHOD NAME: deleteNotice
PATH : /api/admin/notice/delete
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