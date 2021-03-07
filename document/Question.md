# Question(문의글) API 

### 전체 문의글 목록 조회 (관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectAdminQuestionList
PATH: /api/admin/question/all/{page}
*** page는 몇번째 페이지인지의 값으로 첫번째 페이지는 0, 두번째 페이지는 1 이런식으로 증가
*** categoryList는 한글순으로 배치함
PARAM: 없음
```
* 결과 구조
```
[
    {
        "categoryList": [
        "PY",
        "AC",
        "ER",
        "GD",
        "ETC",
        "SH",
        "PR"
    ],
    "questionList": [
        "createDate": 생성일
        "createId": 생성자,
        "updateDate": 수정일,
        "updateId": 수정자,
        "id": 아이디(JPA 생성),
        "categoryId": 카테고리 아이디,
        "title": 제목,
        "contents": 내용,
        "imgPath1": 이미지 경로1,
        "imgName1": 이미지 명1,
        "imgPath2": 이미지 경로2,
        "imgName2": 이미지 명2,
        "imgPath3": 이미지 경로3,
        "imgName3": 이미지 명3
    },
    {
        "createDate": 생성일
        "createId": 생성자,
        "updateDate": 수정일,
        "updateId": 수정자,
        "id": 아이디(JPA 생성),
        "categoryId": 카테고리 아이디,
        "title": 제목,
        "contents": 내용,
        "imgPath1": 이미지 경로1,
        "imgName1": 이미지 명1,
        "imgPath2": 이미지 경로2,
        "imgName2": 이미지 명2,
        "imgPath3": 이미지 경로3,
        "imgName3": 이미지 명3
    },
    ...
    ]
]
```
* 결과 예시
```
[
    {
        "createDate": "2021-02-21T17:52:36.09132",
        "createId": test,
        "updateDate": "2021-02-21T17:52:36.09132",
        "updateId": test,
        "id": 1,
        "categoryId": "AC",
        "title": "제목",
        "contents": "내용",
        "imgPath1": "/resources/static/img/question/1613897556016_cat3.jpg",
        "imgName1": "cat3.jpg",
        "imgPath2": "/resources/static/img/question/1613897556064_cat2.jpg",
        "imgName2": "cat2.jpg",
        "imgPath3": "/resources/static/img/question/1613897556066_cat.jpg",
        "imgName3": "cat.jpg"
    },
    {
        "createDate": "2021-02-21T17:52:37.750297",
        "createId": test,
        "updateDate": "2021-02-21T17:53:29.140459",
        "updateId": test,
        "id": 2,
        "categoryId": "AC",
        "title": "제목2",
        "contents": "내용2",
        "imgPath1": "/resources/static/img/question/1613897557721_cat3.jpg",
        "imgName1": "cat3.jpg",
        "imgPath2": "/resources/static/img/question/1613897557729_cat2.jpg",
        "imgName2": "cat2.jpg",
        "imgPath3": "/resources/static/img/question/1613897557740_cat.jpg",
        "imgName3": "cat.jpg"
    }
]
```

### 전체 문의글 갯수 조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectQuestionTotalCount
PATH: /api/user/question/totalCount
PARAM: 없음
```
* 결과구조
```
{
    "totalCount": 전체 문의글 갯수
}럼
```
* 결과 에시
```
{
    "totalCount": 2
}
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
        "id": 아이디(JPA 생성),
        "title": 제목,
        "contents": 내용,
        "categoryId": 카테고리 아이디,
        "imgPath1": 이미지 경로1,
        "imgName1": 이미지 명1,
        "imgPath2": 이미지 경로2,
        "imgName2": 이미지 명2,
        "imgPath3": 이미지 경로3,
        "imgName3": 이미지 명3,
        "commentList": [
        {
            "id": 댓글 아이디(JPA 생성),
            "contents": 댓글 내용
        },
        {
            "id": 댓글 아이디(JPA 생성),
            "contents": 댓글 내용
        }
    ]
}
```
* 결과 예시
```
{
    "id": 1,
    "title": "제목",
    "contents": "내용",
    "categoryId": "AC",
    "file1": null,
    "file2": null,
    "file3": null,
    "imgPath1": "/resources/static/img/question/1613897556016_cat3.jpg",
    "imgName1": "cat3.jpg",
    "imgPath2": "/resources/static/img/question/1613897556064_cat2.jpg",
    "imgName2": "cat2.jpg",
    "imgPath3": "/resources/static/img/question/1613897556066_cat.jpg",
    "imgName3": "cat.jpg",
    "commentList": [
        {
            "id": 2,
            "contents": "asdfsddf"
        },
        {
            "id": 5,
            "contents": "test"
        }
    ]
}
```

## 1:1 문의 삭제(관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : DELETE
METHOD NAME: deleteQuestionByAdmin
PATH : /api/admin/question/delete
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
PATH: /api/user/question/all/{createId}/{page}
PARAM: createId, page
== PARAM 설명 ===
createId: 사용자 아이디(String 타입)
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
        "categoryId": 카테고리 아이디,
        "title": 제목,
        "contents": 내용,
        "imgPath1": 이미지 경로1,
        "imgName1": 이미지 명1,
        "imgPath2": 이미지 경로2,
        "imgName2": 이미지 명2,
        "imgPath3": 이미지 경로3,
        "imgName3": 이미지 명3
    },
    {
        "createDate": 생성일
        "createId": 생성자,
        "updateDate": 수정일,
        "updateId": 수정자,
        "id": 아이디(JPA 생성),
        "categoryId": 카테고리 아이디,
        "title": 제목,
        "contents": 내용,
        "imgPath1": 이미지 경로1,
        "imgName1": 이미지 명1,
        "imgPath2": 이미지 경로2,
        "imgName2": 이미지 명2,
        "imgPath3": 이미지 경로3,
        "imgName3": 이미지 명3
    },
    ...
]
```
* 결과 예시
```
[
    {
        "createDate": "2021-02-21T17:52:36.09132",
        "createId": test,
        "updateDate": "2021-02-21T17:52:36.09132",
        "updateId": test,
        "id": 1,
        "categoryId": "AC",
        "title": "제목",
        "contents": "내용",
        "imgPath1": "/resources/static/img/question/1613897556016_cat3.jpg",
        "imgName1": "cat3.jpg",
        "imgPath2": "/resources/static/img/question/1613897556064_cat2.jpg",
        "imgName2": "cat2.jpg",
        "imgPath3": "/resources/static/img/question/1613897556066_cat.jpg",
        "imgName3": "cat.jpg"
    },
    {
        "createDate": "2021-02-21T17:52:37.750297",
        "createId": test,
        "updateDate": "2021-02-21T17:53:29.140459",
        "updateId": test,
        "id": 2,
        "categoryId": "AC",
        "title": "제목2",
        "contents": "내용2",
        "imgPath1": "/resources/static/img/question/1613897557721_cat3.jpg",
        "imgName1": "cat3.jpg",
        "imgPath2": "/resources/static/img/question/1613897557729_cat2.jpg",
        "imgName2": "cat2.jpg",
        "imgPath3": "/resources/static/img/question/1613897557740_cat.jpg",
        "imgName3": "cat.jpg"
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
        "id": 아이디(JPA 생성),
        "title": 제목,
        "contents": 내용,
        "categoryId": 카테고리 아이디,
        "imgPath1": 이미지 경로1,
        "imgName1": 이미지 명1,
        "imgPath2": 이미지 경로2,
        "imgName2": 이미지 명2,
        "imgPath3": 이미지 경로3,
        "imgName3": 이미지 명3,
        "commentList": [
        {
            "id": 댓글 아이디(JPA 생성),
            "contents": 댓글 내용
        },
        ...
    ]
}
```
* 결과 예시
```
{
    "id": 1,
    "title": "제목",
    "contents": "내용",
    "categoryId": "AC",
    "file1": null,
    "file2": null,
    "file3": null,
    "imgPath1": "/resources/static/img/question/1613897556016_cat3.jpg",
    "imgName1": "cat3.jpg",
    "imgPath2": "/resources/static/img/question/1613897556064_cat2.jpg",
    "imgName2": "cat2.jpg",
    "imgPath3": "/resources/static/img/question/1613897556066_cat.jpg",
    "imgName3": "cat.jpg",
    "commentList": [
        {
            "id": 2,
            "contents": "asdfsddf"
        },
        {
            "id": 5,
            "contents": "test"
        }
    ]
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
categoryId: 카테고리 아이디(AC[계정], PY[결제], SH[배송], PR[프로그램], GD[굿즈], ER[교환 및 환불], ETC[기타])
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
categoryId: 카테고리 아이디(AC[계정], PY[결제], SH[배송], PR[프로그램], GD[굿즈], ER[교환 및 환불], ETC[기타])
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
*** id는 JPA에서 생성된 값으로 목록에서 가지고 있는 값을 넘겨주면 됩니다.
* 사용자는 상세 페이지에서만 삭제가 가능하므로 PATH에 id를 넘겨줘야 합니다.
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
PATH : /api/admin/question/newReply/{id}
PARAM : Map<String, Object>, id
*** id는 JPA에서 생성된 값으로 목록에서 가지고 있는 값을 넘겨주면 됩니다.
=== PARAM 설명 ===
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
PATH : /api/admin/question/updateReply/{id}
*** id는 JPA에서 생성된 값으로 목록에서 가지고 있는 값을 넘겨주면 됩니다.
PARAM : Map<String, Object>
=== PARAM 설명 ===
contents: 내용
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
PATH : /api/admin/question/deleteReply/{id}
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

### 문의글 검색 (관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: searchQuestion
PATH: api/admin/question/search/{page}
categoryId: 카테고리 아이디(AC[계정], PY[결제], SH[배송], PR[프로그램], GD[굿즈], ER[교환 및 환불], ETC[기타])(필수),
keyword: 검색어
type: "T" 제목, "I" 아이디
*** page는 몇번째 페이지인지의 값으로 첫번째 페이지는 0, 두번째 페이지는 1 이런 식으로 증가
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
        "categoryId": 카테고리 아이디,
        "title": 제목,
        "contents": 내용,
        "imgPath1": 이미지 경로1,
        "imgName1": 이미지 명1,
        "imgPath2": 이미지 경로2,
        "imgName2": 이미지 명2,
        "imgPath3": 이미지 경로3,
        "imgName3": 이미지 명3
    },
    {
        "createDate": 생성일
        "createId": 생성자,
        "updateDate": 수정일,
        "updateId": 수정자,
        "id": 아이디(JPA 생성),
        "categoryId": 카테고리 아이디,
        "title": 제목,
        "contents": 내용,
        "imgPath1": 이미지 경로1,
        "imgName1": 이미지 명1,
        "imgPath2": 이미지 경로2,
        "imgName2": 이미지 명2,
        "imgPath3": 이미지 경로3,
        "imgName3": 이미지 명3
    },
    ...
]
```
* 결과 예시
```
[
    {
        "createDate": "2021-02-21T17:52:36.09132",
        "createId": test,
        "updateDate": "2021-02-21T17:52:36.09132",
        "updateId": test,
        "id": 1,
        "categoryId": "AC",
        "title": "제목",
        "contents": "내용",
        "imgPath1": "/resources/static/img/question/1613897556016_cat3.jpg",
        "imgName1": "cat3.jpg",
        "imgPath2": "/resources/static/img/question/1613897556064_cat2.jpg",
        "imgName2": "cat2.jpg",
        "imgPath3": "/resources/static/img/question/1613897556066_cat.jpg",
        "imgName3": "cat.jpg"
    },
    {
        "createDate": "2021-02-21T17:52:37.750297",
        "createId": test,
        "updateDate": "2021-02-21T17:53:29.140459",
        "updateId": test,
        "id": 2,
        "categoryId": "AC",
        "title": "제목2",
        "contents": "내용2",
        "imgPath1": "/resources/static/img/question/1613897557721_cat3.jpg",
        "imgName1": "cat3.jpg",
        "imgPath2": "/resources/static/img/question/1613897557729_cat2.jpg",
        "imgName2": "cat2.jpg",
        "imgPath3": "/resources/static/img/question/1613897557740_cat.jpg",
        "imgName3": "cat.jpg"
    },
    ...
]
```