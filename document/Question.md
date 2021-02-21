# Question(문의글) API (테스트 진행 중..)

### 전체 문의글 목록 조회 (관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectAdminQuestionList
PATH: /api/admin/question/{page}
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
        "createDate": "2021-02-09T16:41:12.048522",
        "createId": test,
        "updateDate": "2021-02-09T16:41:12.048855",
        "updateId": test,
        "id": 1,
        "categoryId": "AC",
        "title": "제목",
        "contents": "내용"
    },
    {
        "createDate": "2021-02-09T16:41:17.452411",
        "createId": test,
        "updateDate": "2021-02-09T16:41:17.452478",
        "updateId": test,
        "id": 2,
        "categoryId": "ETC",
        "title": "제목",
        "contents": "내용"
    },
    ...
]
```

### 1:1 문의 상세 조회(관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectAdminQuestionDetail
PATH: /api/admin/question/detail/{id}
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

## 1:1 문의 삭제(관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : DELETE
METHOD NAME: deleteQuestionByAdmin
PATH : /api/user/question/delete
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

### 사용자 1:1 문의글 목록 조회 (사용자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectUserQuestionList
PATH: /api/user/question/all/{id}/{page}
PARAM: id, page
== PARAM 설명 ===
id: 사용자 아이디
*** page는 몇번째 페이지인지의 값으로 첫번째 페이지는 0, 두번째 페이지는 1 이런식으로 증가
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
        "createDate": "2021-02-09T16:41:12.048522",
        "createId": test,
        "updateDate": "2021-02-09T16:41:12.048855",
        "updateId": test,
        "id": 1,
        "categoryId": "AC",
        "title": "제목",
        "contents": "내용"
    },
    {
        "createDate": "2021-02-09T16:41:17.452411",
        "createId": test,
        "updateDate": "2021-02-09T16:41:17.452478",
        "updateId": test,
        "id": 2,
        "categoryId": "ETC",
        "title": "제목",
        "contents": "내용"
    },
    ...
]
```

### 1:1 문의 상세 조회(사용자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectUserQuestionDetail
PATH: /api/user/question/detail/{id}
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

### 1:1 문의 등록(사용자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : POST
METHOD NAME: insertQuestion
PATH : /api/user/question/new
PARAM : Map<String, Object>
=== PARAM 설명 ===
title: 제목
contents: 내용
file1: 이미지 파일1
file2: 이미지 파일2
file3: 이미지 파일3
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

### 1:1 문의 수정(사용자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : PUT
METHOD NAME: updateQuestion
PATH : /api/user/question/update/{id}
PARAM : Map<String, Object>
=== PARAM 설명 ===
title: 제목
contents: 내용
file1: 이미지 파일1
file2: 이미지 파일2
file3: 이미지 파일3
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

### 1:1 문의 삭제(사용자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : DELETE
METHOD NAME: deleteQuestion
PATH : /api/user/question/delete/{id}
*** id는 JPA에서 생성된 값으로 목록에서 가지고 있는 값을 넘겨주면 됨.
PARAM : 없음
```
* 결과 구조
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

### 1:1 문의 댓글 등록(관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : POST
METHOD NAME: insertQuestionReply
PATH : /api/user/question/newReply
PARAM : Map<String, Object>
=== PARAM 설명 ===
question_id: 문의글 번호
title: 제목
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

### 1:1 문의 댓글 수정(관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : PUT
METHOD NAME: updateQuestionReply
PATH : /api/user/question/updateReply/{id}
PARAM : Map<String, Object>
=== PARAM 설명 ===
question_id: 문의글 번호
title: 제목
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

### 1:1 문의 댓글 삭제(관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : DELETE
METHOD NAME: deleteQuestionReply
PATH : /api/user/question/deleteReply/{id}
*** id는 JPA에서 생성된 값으로 목록에서 가지고 있는 값을 넘겨주면 됨.
PARAM : 없음
```
* 결과 구조
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