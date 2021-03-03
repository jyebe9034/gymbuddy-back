# ColumnWriter API

### 전체 컬럼 작성자 갯수조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectColumnWriterTotalCount
PATH: /api/columnWriter/totalCount
PARAM: 없음
```
* 결과구조
```
{
    "totalCount": 전체 컬럼 작성자수
}
```
* 결과 에시
```
{
    "totalCount": 3
}
```

### 전체 칼럼 작성자 목록 조회(페이징 미포)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : GET
METHOD NAME: selectColumnWriterList
PATH : /api/columnWriter/all
PARAM : 없음
```
* 결과 구조
```
[
    {
        "createDate": 생성일,
        "createId": 생성자,
        "updateDate": 수정일,
        "updateId": 수정자,
        "id": 아이디(JPA 생성),
        "name": 이름,
        "job": 직업,
        "contents": 내용
    }
]
```
* 결과 예시
```
[
    {
        "createDate": "2021-02-06T21:27:09.629",
        "createId": test,
        "updateDate": "2021-02-06T21:27:09.629",
        "updateId": test,
        "id": 1,
        "name": "김작가",
        "job": "작가",
        "contents": "글을 쓰는 작가입니다."
    },
    {
        "createDate": "2021-02-06T21:27:46.772",
        "createId": test,
        "updateDate": "2021-02-06T21:27:46.772",
        "updateId": test,
        "id": 2,
        "name": "김운동",
        "job": "운동선수",
        "contents": "운동 선수입니다."
    },
    ...
]
```

### 전체 칼럼 작성자 목록 조회함(페이징 포함)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : GET
METHOD NAME: selectColumnWriterList
PATH : /api/columnWriter/all/{page}
*** page는 몇번째 페이지인지의 값으로 첫번째 페이지는 0, 두번째 페이지는 1 이런식으로 증가
PARAM : 없음
```
* 결과 구조
```
[
    {
        "createDate": 생성일,
        "createId": 생성자,
        "updateDate": 수정일,
        "updateId": 수정자,
        "id": 아이디(JPA 생성),
        "name": 이름,
        "job": 직업,
        "contents": 내용
    }
]
```
* 결과 예시
```
[
    {
        "createDate": "2021-02-06T21:27:09.629",
        "createId": test,
        "updateDate": "2021-02-06T21:27:09.629",
        "updateId": test,
        "id": 1,
        "name": "김작가",
        "job": "작가",
        "contents": "글을 쓰는 작가입니다."
    },
    {
        "createDate": "2021-02-06T21:27:46.772",
        "createId": test,
        "updateDate": "2021-02-06T21:27:46.772",
        "updateId": test,
        "id": 2,
        "name": "김운동",
        "job": "운동선수",
        "contents": "운동 선수입니다."
    },
    ...
]
```

### 칼럼 작성자 상세 조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectColumnWriterDetail
PATH: /api/columnWriter/detail/{id}
*** id는 JPA에서 생성된 값으로 목록에서 가지고 있는 값을 넘겨주면 됌.
PARAM: 없음
```
* 결과 구조
```
{
    "createDate": 생성일,
    "createId": 생성자,
    "updateDate": 수정일,
    "updateId": 수정자,
    "id": 아이디(JPA 생성),
    "name": 이름,
    "job": 직업,
    "contents": 내용
}
```
* 결과 예시
```
{
    "createDate": "2021-02-06T21:28:12.045",
    "createId": test,
    "updateDate": "2021-02-06T21:28:12.045",
    "updateId": test,
    "id": 3,
    "name": "김레터",
    "job": "뉴스레터 편집장",
    "contents": "뉴스레터의 편집을 맡고있는 편집장입니다."
}
```

### 칼럼 작성자 등록
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : POST
METHOD NAME: insertColumnWriter
PATH : /api/admin/columnWriter/new
PARAM : Map<String, Object>
=== PARAM 설명 ===
name: 이름
job: 직업
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

### 칼럼 작성자 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : PUT
METHOD NAME: updateColumnWriter
PATH : /api/admin/columnWriter/update/{id}
PARAM : Map<String, Object>
=== PARAM 설명 ===
name: 이름
job: 직업
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

### 칼럼 작성자 삭제
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : DELETE
METHOD NAME: deleteColumnWriter
PATH : /api/admin/columnWriter/delete
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