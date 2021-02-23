# Term API

### 전체 약관 목록 조회 (관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectTermList
PATH: /api/admin/term/all
PARAM: 없음
```
* 결과 구조
```
[
    {
        "createDate": 생성일
        "createId": 생성자,
        "updateDate": 수정일,
        "updateId": 수정자,
        "id": 아이디(JPA 생성),
        "title": 제목,
        "imgPath": 이미지 경로,
        "imgName": 이미지 명,
        "webMobile": 웹/모바일 여부
    },
    {
        "createDate": 생성일
        "createId": 생성자,
        "updateDate": 수정일,
        "updateId": 수정자,
        "id": 아이디(JPA 생성),
        "title": 제목,
        "imgPath": 이미지 경로,
        "imgName": 이미지 명,
        "webMobile": 웹/모바일 여부
    },
    ...
]
```
* 결과 예시
```
[
    {
        "createDate": "2021-02-09T13:07:13.285257",
        "createId": test,
        "updateDate": "2021-02-09T13:07:13.28572",
        "updateId": test,
        "id": 1,
        "title": "이용약관",
        "imgPath": "/resources/static/img/term1612843633229_cat.jpg",
        "imgName": "cat.jpg",
        "webMobile": "WEB"
    },
    {
        "createDate": "2021-02-09T13:07:21.871573",
        "createId": test,
        "updateDate": "2021-02-09T13:07:21.871612",
        "updateId": test,
        "id": 2,
        "title": "이용약관",
        "imgPath": "/resources/static/img/term1612843641821_cat2.jpg",
        "imgName": "cat2.jpg",
        "webMobile": "MOBILE"
    },
    ...
]
```

### 약관 상세 조회 (관리자)
* 약관 상세 조회는 웹/모바일 두 가지 타입으로 "web"과 "mobile"로 묶어서 데이터를 보내므로 web.title 등으로 사용 가능*
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectTermDetail
PATH: /api/admin/term/detail/{title}
PARAM: title
```
* 결과 구조
```
{
    "createDate": 생성일
    "createId": 생성자,
    "updateDate": 수정일,
    "updateId": 수정자,
    "id": 아이디(JPA 생성),
    "title": 제목,
    "imgPath": 이미지 경로,
    "imgName": 이미지 명,
    "webMobile": 웹/모바일 여부
}
```
* 결과 예시
```
{
    "web": {
        "createDate": "2021-02-09T13:12:06.976974",
        "createId": test,
        "updateDate": "2021-02-09T13:12:06.977028",
        "updateId": test,
        "id": 1,
        "title": "이용약관",
        "imgPath": "/resources/static/img/term1612843926958_cat.jpg",
        "imgName": "cat.jpg",
        "webMobile": "WEB"
    },
    "mobile": {
        "createDate": "2021-02-09T13:12:16.473324",
        "createId": test,
        "updateDate": "2021-02-09T13:12:16.473343",
        "updateId": test,
        "id": 2,
        "title": "이용약관",
        "imgPath": "/resources/static/img/term1612843936464_cat2.jpg",
        "imgName": "cat2.jpg",
        "webMobile": "MOBILE"
    }
}
```

### 푸터 - 개인정보 처리방침 (사용자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectPrivatePolicy
PATH: /api/term/footer/private_policy
PARAM: 없음
```
* 결과 구조
```
{
    "createDate": 생성일
    "createId": 생성자,
    "updateDate": 수정일,
    "updateId": 수정자,
    "id": 아이디(JPA 생성),
    "title": 제목,
    "imgPath": 이미지 경로,
    "imgName": 이미지 명,
    "webMobile": 웹/모바일 여부
}
```
* 결과 예시
```
[
    {
        "createDate": "2021-02-09T13:29:44.391275",
        "createId": test,
        "updateDate": "2021-02-09T13:29:44.391741",
        "updateId": test,
        "id": 3,
        "title": "개인정보 처리방침",
        "imgPath": "/resources/static/img/term1612844984323_cat.jpg",
        "imgName": "cat.jpg",
        "webMobile": "WEB"
    },
    {
        "createDate": "2021-02-09T13:29:53.699475",
        "createId": test,
        "updateDate": "2021-02-09T13:29:53.6995",
        "updateId": test,
        "id": 4,
        "title": "개인정보 처리방침",
        "imgPath": "/resources/static/img/term1612844993666_cat2.jpg",
        "imgName": "cat2.jpg",
        "webMobile": "MOBILE"
    }
]
```

### 푸터 - 이용약관 (사용자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectTermOfUser
PATH: /api/term/footer/term_of_use
PARAM: 없음
```
* 결과 구조
```
{
    "createDate": 생성일
    "createId": 생성자,
    "updateDate": 수정일,
    "updateId": 수정자,
    "id": 아이디(JPA 생성),
    "title": 제목,
    "imgPath": 이미지 경로,
    "imgName": 이미지 명,
    "webMobile": 웹/모바일 여부
}
```
* 결과 예시
```
[
    {
        "createDate": "2021-02-09T13:12:06.976974",
        "createId": null,
        "updateDate": "2021-02-09T13:12:06.977028",
        "updateId": null,
        "id": 1,
        "title": "이용약관",
        "imgPath": "/resources/static/img/term1612843926958_cat.jpg",
        "imgName": "cat.jpg",
        "webMobile": "WEB"
    },
    {
        "createDate": "2021-02-09T13:12:16.473324",
        "createId": null,
        "updateDate": "2021-02-09T13:12:16.473343",
        "updateId": null,
        "id": 2,
        "title": "이용약관",
        "imgPath": "/resources/static/img/term1612843936464_cat2.jpg",
        "imgName": "cat2.jpg",
        "webMobile": "MOBILE"
    }
]
```

### 약관 등록
* 등록은 백엔드에서 등록해줌. (수정만 가능)

### 약관 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : PUT
METHOD NAME: updateTerm
PATH : /api/admin/term/update/{id}
PARAM : Map<String, Object>
=== PARAM 설명 ===
title: 제목
file: 이미지 파일
webOrMobile: 웹/모바일 여부 (이 파라미터는 수정하지 않는 것을 권장)
*** 수정 시에는 모든 값을 넘길 필요는 없음. 수정할 값만 넘겨도 수정 가능함.
```
* 결과 구조
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

### 약관 삭제
* 약관은 삭제할 수 없고 수정만 가능함. (사진은 업데이트 시 삭제됨.)