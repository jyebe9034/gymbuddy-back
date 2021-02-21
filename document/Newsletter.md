# Newsletter API

### 전체 뉴스레터 구독자 조회 (관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectSubscriberList
PATH: /api/newsletter/all
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
        "email": 이메일 주소
    },
    {
        "createDate": 생성일
        "createId": 생성자,
        "updateDate": 수정일,
        "updateId": 수정자,
        "id": 아이디(JPA 생성),
        "email": 이메일 주소
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
        "email": test1@gmail.com
    },
    {
        "createDate": "2021-02-09T13:07:21.871573",
        "createId": test,
        "updateDate": "2021-02-09T13:07:21.871612",
        "updateId": test,
        "id": 2,
        "email": test2@gmail.com
    },
    ...
]
```

### 뉴스레터 등록
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : POST
METHOD NAME: insertNewSubscribe
PATH : /api/newsletter/newSubscribe
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
PATH : /api/newsletter/deleteSubscribe/{id}
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