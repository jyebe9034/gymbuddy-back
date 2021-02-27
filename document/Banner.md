# Banner API

### 전체 메인 배너 갯수조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectBannerTotalCount
PATH: /api/banner/totalCount
PARAM: 없음
```
* 결과구조
```
{
    "totalCount": 전체 메인 배너 갯수
}
```
* 결과 에시
```
{
    "totalCount": 6
}
```

### 전체 메인 베너 목록조회(최대 5개)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectMainBannerList
PATH: /api/banner/all
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
        "categoryId": 카테고리 아이디,
        "link": 링크,
        "btnTitle": 버튼명,
        "imgPath": 이미지 경로,
        "imgName": 이미지 명
    },
    {
        "createDate": 생성일
        "createId": 생성자,
        "updateDate": 수정일,
        "updateId": 수정자,
        "id": 아이디(JPA 생성),
        "title": 제목,
        "categoryId": 카테고리 아이디,
        "link": 링크,
        "btnTitle": 버튼명,
        "imgPath": 이미지 경로,
        "imgName": 이미지 명
    },
    ...
]
```
* 결과 예시
```
[
    {
        "createDate": "2021-02-06T21:02:02.529",
        "createId": test,
        "updateDate": "2021-02-06T21:02:02.529",
        "updateId": test,
        "id": 1,
        "title": "배너1",
        "categoryId": "PR",
        "link": "localhost:8080",
        "btnTitle": "자세히보기",
        "imgPath": "/resources/static/img/banner/1612612922504_테스트.png",
        "imgName": "테스트.png"
    },
    {
        "createDate": "2021-02-06T21:06:20.718",
        "createId": test,
        "updateDate": "2021-02-06T21:06:20.718",
        "updateId": test,
        "id": 2,
        "title": "배너2",
        "categoryId": "GD",
        "link": "localhost:8080",
        "btnTitle": "자세히보기",
        "imgPath": "/resources/static/img/banner/1612613180693_테스트.png",
        "imgName": "테스트.png"
    },
    ...
]
```

### 메인 베너 상세 조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectMainBannerDetail
PATH: /api/banner/detail/{id}
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
    "link": 링크,
    "btnTitle": 버튼명,
    "imgPath": 이미지 경로,
    "imgName": 이미지 명
}
```
* 결과 예시
```
{
    "createDate": "2021-02-06T21:07:08.783",
    "createId": test,
    "updateDate": "2021-02-06T21:07:08.783",
    "updateId": test,
    "id": 7,
    "title": "배너5",
    "categoryId": "CL",
    "link": "localhost:8080",
    "btnTitle": "자세히보기",
    "imgPath": "/resources/static/img/banner/1612613228709_테스트.png",
    "imgName": "테스트.png"
}
```

### 메인 베너 등록
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : POST
METHOD NAME: insertMainBanner
PATH : /api/admin/banner/new
PARAM : Map<String, Object>
=== PARAM 설명 ===
title: 제목
categoryId: 카테고리 아이디(PR[프로그램], GD[굿즈], NT[공지사항], NS[대외뉴스], YT[유튜브], CL[칼럼])
link: 링크
btnTitle: 버튼명
file: 이미지 파일
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

### 메인 베너 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : PUT
METHOD NAME: updateMainBanner
PATH : /api/admin/banner/update/{id}
PARAM : Map<String, Object>
=== PARAM 설명 ===
title: 제목
categoryId: 카테고리 아이디(PR[프로그램], GD[굿즈], NT[공지사항], NS[대외뉴스], YT[유튜브], CL[칼럼])
link: 링크
btnTitle: 버튼명
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

### 메인 베너 삭제
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : DELETE
METHOD NAME: deleteMainBanner
PATH : /api/admin/banner/delete
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