# Faq API

### 전체 FAQ(자주묻는질문) 목록 조회 (관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectAdminFqList
PATH: /api/admin/frequencyQuestion/all/{page}
*** page는 몇번째 페이지인지의 값으로 첫번째 페이지는 0, 두번째 페이지는 1 이런식으로 증가
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
        "categoryId": 카테고리 아이디,
        "contents": 내용
    },
    {
        "createDate": 생성일
        "createId": 생성자,
        "updateDate": 수정일,
        "updateId": 수정자,
        "id": 아이디(JPA 생성),
        "title": 제목,
        "categoryId": 카테고리 아이디,
        "contents": 내용
    },
    ...
]
```
* 결과 예시
```
[
    {
    "shipment": [
        {
            "createDate": "2021-02-23T23:19:18.13",
            "createId": test,
            "updateDate": "2021-02-23T23:20:30.628",
            "updateId": test,
            "id": 1,
            "categoryId": "SH",
            "title": "제목1",
            "contents": "내용1"
        },
        {
            "createDate": "2021-02-24T21:25:00.165",
            "createId": test,
            "updateDate": "2021-02-24T21:25:00.165",
            "updateId": test,
            "id": 5,
            "categoryId": "SH",
            "title": "dddd",
            "contents": "ddd"
        }
    ],
    "etc": [
        {
            "createDate": "2021-02-23T23:19:48.464",
            "createId": test,
            "updateDate": "2021-02-23T23:19:48.464",
            "updateId": test,
            "id": 2,
            "categoryId": "ETC",
            "title": "제목2",
            "contents": "내용2"
        }
    ],
    "programAndGoods": [
        {
            "createDate": "2021-02-27T19:21:52.645",
            "createId": test,
            "updateDate": "2021-02-27T19:21:52.645",
            "updateId": test,
            "id": 9,
            "categoryId": "PD",
            "title": "제목",
            "contents": "내용"
        }
    ],
    "payment": [
        {
            "createDate": "2021-02-24T21:26:47.357",
            "createId": test,
            "updateDate": "2021-02-24T21:26:47.357",
            "updateId": test,
            "id": 6,
            "categoryId": "PY",
            "title": "ddd",
            "contents": "dddd"
        },
        {
            "createDate": "2021-02-24T21:29:10.852",
            "createId": test,
            "updateDate": "2021-02-24T21:29:10.852",
            "updateId": test,
            "id": 7,
            "categoryId": "PY",
            "title": "dddd",
            "contents": "dddd"
        }
    ],
    "account": [
        {
            "createDate": "2021-02-24T21:18:00.815",
            "createId": test,
            "updateDate": "2021-02-24T21:18:00.815",
            "updateId": test,
            "id": 4,
            "categoryId": "AC",
            "title": "뽀레스트",
            "contents": "ㅇㅇㅇ"
        }
    ],
    "homepage": [
        {
            "createDate": "2021-02-24T21:32:45.335",
            "createId": test,
            "updateDate": "2021-02-24T21:32:45.335",
            "updateId": test,
            "id": 8,
            "categoryId": "HP",
            "title": "fewfewfew",
            "contents": "weffwefwe"
        }
    ]
},
    ...
]
```

### 전체 FAQ(자주묻는질문) 갯수 조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectFaqTotalCount
PATH: /api/frequencyQuestion/totalCount
PARAM: 없음
```
* 결과구조
```
{
    "totalCount": 전체 FAQ(자주묻는질문) 갯수
}럼
```
* 결과 에시
```
{
    "totalCount": 2
}


### 전체 FAQ(자주묻는질문) 목록 조회 (사용자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectUserFqList
PATH: /api/frequencyQuestion/all
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
        "categoryId": 카테고리 아이디,
        "contents": 내용
    },
    {
        "createDate": 생성일
        "createId": 생성자,
        "updateDate": 수정일,
        "updateId": 수정자,
        "id": 아이디(JPA 생성),
        "title": 제목,
        "categoryId": 카테고리 아이디,
        "contents": 내용
    },
    ...
]
```
* 결과 예시
```
[
    {
    "shipment": [
        {
            "createDate": "2021-02-23T23:19:18.13",
            "createId": test,
            "updateDate": "2021-02-23T23:20:30.628",
            "updateId": test,
            "id": 1,
            "categoryId": "SH",
            "title": "제목1",
            "contents": "내용1"
        },
        {
            "createDate": "2021-02-24T21:25:00.165",
            "createId": test,
            "updateDate": "2021-02-24T21:25:00.165",
            "updateId": test,
            "id": 5,
            "categoryId": "SH",
            "title": "dddd",
            "contents": "ddd"
        }
    ],
    "etc": [
        {
            "createDate": "2021-02-23T23:19:48.464",
            "createId": test,
            "updateDate": "2021-02-23T23:19:48.464",
            "updateId": test,
            "id": 2,
            "categoryId": "ETC",
            "title": "제목2",
            "contents": "내용2"
        }
    ],
    "programAndGoods": [
        {
            "createDate": "2021-02-27T19:21:52.645",
            "createId": test,
            "updateDate": "2021-02-27T19:21:52.645",
            "updateId": test,
            "id": 9,
            "categoryId": "PD",
            "title": "제목",
            "contents": "내용"
        }
    ],
    "payment": [
        {
            "createDate": "2021-02-24T21:26:47.357",
            "createId": test,
            "updateDate": "2021-02-24T21:26:47.357",
            "updateId": test,
            "id": 6,
            "categoryId": "PY",
            "title": "ddd",
            "contents": "dddd"
        },
        {
            "createDate": "2021-02-24T21:29:10.852",
            "createId": test,
            "updateDate": "2021-02-24T21:29:10.852",
            "updateId": test,
            "id": 7,
            "categoryId": "PY",
            "title": "dddd",
            "contents": "dddd"
        }
    ],
    "account": [
        {
            "createDate": "2021-02-24T21:18:00.815",
            "createId": test,
            "updateDate": "2021-02-24T21:18:00.815",
            "updateId": test,
            "id": 4,
            "categoryId": "AC",
            "title": "뽀레스트",
            "contents": "ㅇㅇㅇ"
        }
    ],
    "homepage": [
        {
            "createDate": "2021-02-24T21:32:45.335",
            "createId": test,
            "updateDate": "2021-02-24T21:32:45.335",
            "updateId": test,
            "id": 8,
            "categoryId": "HP",
            "title": "fewfewfew",
            "contents": "weffwefwe"
        }
    ]
},
    ...
]
```

### FAQ(자주묻는질문) 상세 조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectFqDetail
PATH: /api/frequencyQuestion/detail/{id}
*** id는 JPA에서 생성된 값으로 목록에서 가지고 있는 값을 넘겨주면 됌.
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
    "categoryId": 카테고리 아이디,
    "contents": 내용
}
```
* 결과 예시
```
{
    "createDate": "2021-02-09T16:41:12.048522",
    "createId": test,
    "updateDate": "2021-02-09T16:41:12.048855",
    "updateId": test,
    "id": 1,
    "categoryId": "AC",
    "title": "제목",
    "contents": "내용"
}
```

### FAQ(자주묻는질문) 등록
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : POST
METHOD NAME: insertFq
PATH : /api/admin/frequencyQuestion/new
PARAM : Map<String, Object>
=== PARAM 설명 ===
title: 제목
categoryId: 카테고리 아이디(AC[계정], PY[결제], SH[배송], PD[프로그램 및 굿즈], HP[홈페이지 이용], ETC[기타])
contents: 내용
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

### FAQ(자주묻는질문) 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : PUT
METHOD NAME: updateFq
PATH : /api/admin/frequencyQuestion/update/{id}
PARAM : Map<String, Object>
=== PARAM 설명 ===
title: 제목
categoryId: 카테고리 아이디(AC[계정], PY[결제], SH[배송], PD[프로그램 및 굿즈], HP[홈페이지 이용], ETC[기타])
contents: 내용
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

### FAQ(자주묻는질문) 삭제
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : DELETE
METHOD NAME: deleteFq
PATH : /api/admin/frequencyQuestion/delete
PARAM : List<Integer>
=== PARAM 설명 ===
아이디를 배열로 넘기면 됨.
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