# Columns API

### 전체 컬럼 갯수조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectColumnTotalCount
PATH: /api/column/totalCount
PARAM: 없음
```
* 결과구조
```
{
    "totalCount": 전체 컬럼 갯수
}
```
* 결과 에시
```
{
    "totalCount": 15
}
```

### 전체 컬럼 목록조회 (관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectColumnList
PATH : /api/column/all/{page}
*** page는 몇번째 페이지인지의 값으로 첫번째 페이지는 0, 두번째 페이지는 1 이런식으로 증가
PARAM: 없음
```
* 결과구조
```
[
    {
        "createDate": 생성일
        "createId": 생성자,
        "updateDate": 수정일,
        "updateId": 수정자,
        "id": 아이디(JPA 생성),
        "title": 제목,
        "contents": 내용,
        "imgPath": 이미지 경로,
        "imgName": 이미지 명,
        "mainYn": 메인 노출 여부
    },
    ...
]
```
* 결과 예시
```
[
    {
        "createDate": "2021-02-22T08:53:40.256",
        "createId": null,
        "updateDate": "2021-02-22T08:53:40.256",
        "updateId": null,
        "id": 15,
        "title": "컬럼15",
        "contents": "컬럼입니다.",
        "imgPath": "/resources/static/img/columns/1613951620222_테스트.png",
        "imgName": "1613951620222_테스트.png"
    },
    {
        "createDate": "2021-02-22T08:53:37.791",
        "createId": null,
        "updateDate": "2021-02-22T08:53:37.791",
        "updateId": null,
        "id": 14,
        "title": "컬럼14",
        "contents": "컬럼입니다.",
        "imgPath": "/resources/static/img/columns/1613951617758_테스트.png",
        "imgName": "1613951617758_테스트.png"
    },
    ...
]
```

### 전체 컬럼 목록조회 (사용자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectColumnListForUser
PATH : /api/column/allForUser/{page}
*** page는 몇번째 페이지인지의 값으로 첫번째 페이지는 0, 두번째 페이지는 1 이런식으로 증가
PARAM: 없음
```
* 결과구조
```
[
    {
        "createDate": 생성일
        "createId": 생성자,
        "updateDate": 수정일,
        "updateId": 수정자,
        "id": 아이디(JPA 생성),
        "title": 제목,
        "contents": 내용,
        "imgPath": 이미지 경로,
        "imgName": 이미지 명,
        "mainYn": 메인 노출 여부
    },
    ...
]
```
* 결과 예시
```
[
    {
        "createDate": "2021-02-22T08:53:40.256",
        "createId": null,
        "updateDate": "2021-02-22T08:53:40.256",
        "updateId": null,
        "id": 15,
        "title": "컬럼15",
        "contents": "컬럼입니다.",
        "imgPath": "/resources/static/img/columns/1613951620222_테스트.png",
        "imgName": "1613951620222_테스트.png"
    },
    {
        "createDate": "2021-02-22T08:53:37.791",
        "createId": null,
        "updateDate": "2021-02-22T08:53:37.791",
        "updateId": null,
        "id": 14,
        "title": "컬럼14",
        "contents": "컬럼입니다.",
        "imgPath": "/resources/static/img/columns/1613951617758_테스트.png",
        "imgName": "1613951617758_테스트.png"
    },
    ...
]
```

### 컬럼 상세 조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectColumnDetail
PATH: /api/column/detail/{id}
*** id는 JPA에서 생성된 값으로 목록에서 가지고 있는 값을 넘겨주면 됌.
PARAM: 없음
```
* 결과 구조
```
{
    "id": 컬럼아이디용(JPA 생성)
    "title": 제목
    "contents": 내용
    "columnWriterId": 컬럼작성자아이디(null로 넘어옴)
    "file": 파일((null로 넘어옴)
    "imgPath": 이미지 경로
    "imgName": 이미지 명
    "columnWriter": {
        "id": 컬럼 작성자 아이디(JPA 생성)
        "name": 작성자 이름
        "job": 작성자 직업
        "contents": 작성자 관련 내
    }
}
```
* 결과 예시
```
{
    "id": 6,
    "title": "컬럼6",
    "contents": "컬럼입니다.",
    "columnWriterId": null,
    "file": null,
    "imgPath": "/resources/static/img/columns/1613951594255_테스트.png",
    "imgName": "1613951594255_테스트.png",
    "columnWriter": {
        "id": 2,
        "name": "김운동",
        "job": "운동선수",
        "contents": "운동친구의 코치입니다."
    }
}
```

### 컬럼 등록
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : POST
METHOD NAME: insertColumn
PATH : /api/admin/column/new
PARAM : Map<String, Object>
=== PARAM 설명 ===
title: 제목
contnets: 내용
columnWriterId: 컬럼 작성자 아이디
file: 이미지 파일
mainYn: 메인 노출 여부(Y or N)
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

### 컬럼 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : PUT
METHOD NAME: updateColumn
PATH : /api/admin/column/update/{id}
PARAM : Map<String, Object>
=== PARAM 설명 ===
title: 제목
contnets: 내용
columnWriterId: 컬럼 작성자 아이디
file: 이미지 파일
mainYn: 메인 노출 여부(Y or N)
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

### 컬럼 삭제
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : DELETE
METHOD NAME: deleteColumn
PATH : /api/admin/column/delete
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