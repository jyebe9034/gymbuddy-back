# Newsletter API

### 전체 뉴스레터 구독자 조회 (관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectSubscriberList
PATH: /api/newsLetter/all/{page}
*** page는 몇번째 페이지인지의 값으로 첫번째 페이지는 0, 두번째 페이지는 1 이런식으로 증가
PARAM: 없음
```
* 결과 구조
```
[
    {
        "id": 아이디(JPA 생성),
        "email": 이메일 주소,
        "create_date": 등록일자
    },
    {
        "id": 아이디(JPA 생성),
        "email": 이메일 주소,
        "create_date": 등록일자
    },
    ...
]
```
* 결과 예시
```
[
    {
        "id": 1,
        "email": "test@gmail.com",
        "createDate": "2021-02-24T20:08:45"
    },
    {
        "id": 2,
        "email": "test2@gmail.com",
        "createDate": "2021-02-24T20:08:56"
    },
    {
        "id": 3,
        "email": "test3@gmail.com",
        "createDate": "2021-02-24T20:08:57"
    },
    ...
]
```

### 뉴스레터 등록
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : POST
METHOD NAME: insertNewSubscribe
PATH : /api/newsLetter/newSubscribe
PARAM : Map<String, Object>
=== PARAM 설명 ===
email: 이메일
```
* 결과 구조
```
{
    "id": 생성된 아이디 값
}
```
* 결과 예시
```
{
    "id": 1 
}
```

### 뉴스레터 삭제
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : DELETE
METHOD NAME: deleteNewsletter
PATH : /api/newsLetter/deleteSubscribe/{id}
*** id는 JPA에서 생성된 값으로 목록에서 가지고 있는 값을 넘겨주면 됨.
PARAM : 없음
```
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
```
### 뉴스레터 구독일자 검색 (관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: searchCreateDate
PATH: /api/newsLetter/search/{start}/{end}
=== PARAM 설명 ===
start: 시작 구독일자 
end: 마지막 구독일자
(예시: 2021-02-23)
```
* 결과 구조
```
[
    {
        "id": 아이디(JPA 생성),
        "email": 이메일 주소,
        "create_date": 등록일자
    },
    {
        "id": 아이디(JPA 생성),
        "email": 이메일 주소,
        "create_date": 등록일자
    },
    ...
]
```
* 결과 예시
```
[
    {
        "id": 1,
        "email": "test@gmail.com",
        "createDate": "2020-02-23T00:00:00"
    },
    {
        "id": 2,
        "email": "test2@gmail.com",
        "createDate": "2020-02-23T00:00:00"
    },
    {
        "id": 3,
        "email": "test3@gmail.com",
        "createDate": "2020-02-23T00:00:00"
    }
]
```