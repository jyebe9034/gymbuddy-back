# Newsletter API

### 전체 뉴스레터 구독자 조회 (관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectSubscriberList
PATH: /api/newsLetter/all
PARAM: 없음
```
* 결과 구조
```
[
    {
        "id": 아이디(JPA 생성),
        "email": 이메일 주소
    },
    {
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
        "id": 1,
        "email": "test@gmail.com"
    },
    {
        "id": 2,
        "email": "test2@gmail.com"
    },
    {
        "id": 3,
        "email": "test3@gmail.com"
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