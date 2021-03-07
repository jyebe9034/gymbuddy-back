# Member API

### 멤버 조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectMemberList
PATH: /api/member/all
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
        "imgPath": 이미지 경로,
        "imgName": 이미지 명,
        "webMobile": 웹/모바일 여부
    }
]
```
* 결과 예시
```
[
    {
        "createDate": "2021-02-09T13:43:00.277449",
        "createId": test,
        "updateDate": "2021-02-09T13:43:00.277575",
        "updateId": test,
        "id": 1,
        "imgPath": "/resources/static/img/member1612845780205_cat.jpg",
        "imgName": "cat.jpg",
        "webOrMobile": "WEB"
    },
    {
        "createDate": "2021-02-09T13:43:12.539385",
        "createId": test,
        "updateDate": "2021-02-09T13:43:12.539411",
        "updateId": test,
        "id": 2,
        "imgPath": "/resources/static/img/member1612845792517_cat2.jpg",
        "imgName": "cat2.jpg",
        "webOrMobile": "MOBILE"
    }
]
```

### 멤버 웹 등록
* 등록은 백엔드에서 등록해줌. (수정만 가능)

### 멤버 모바일 등록
* 등록은 백엔드에서 등록해줌. (수정만 가능)

### 멤버 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : PUT
METHOD NAME: updateMember
PATH : /api/admin/member/update/{id}
PARAM : Map<String, Object>
=== PARAM 설명 ===
id: 1 - 웹, 2 - 모바일
file: 이미지 파일
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

### 멤버 삭제
* 멤버는 삭제할 수 없고 수정만 가능합니다. (*사진은 수정 시에만 삭제됩니다.)