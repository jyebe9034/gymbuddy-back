# Youtube API

### 전체 유튜브 목록조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectYoutubeList
PATH: /api/youtube/all/{page}
*** page는 몇번째 페이지인지의 값으로 첫번째 페이지는 0, 두번째 페이지는 1 이런식으로 증가
PARAM: 없음
```
* 결과구조
```
[
    {
        "id": 유튜브 아이디(JPA 생성),
        "uploadDate": 업로드 날짜,
        "title": 제목,
        "contents": 내용,
        "link": 유튜브 링크,
        "imgPath": 이미지 경로,
        "imgName": 이미지 명,
        "mainYn": 메인 노출 여부
    }
]
```
* 결과 예시
```
[
    {
        "id": 1,
        "uploadDate": "2021-02-03",
        "title": "유튜브",
        "contents": "첫번째 유튜브 입니다.",
        "link": "localhost:8080/youtube",
        "imgPath": "/resources/static/img/youtube/1612437700781_테스트.png",
        "imgName": "테스트.png",
        "mainYn": "Y"
    },
    {
        "id": 2,
        "uploadDate": "2021-02-03",
        "title": "유튜브2",
        "contents": "두번째 유튜브 입니다.",
        "link": "localhost:8080/youtube",
        "imgPath": "/resources/static/img/youtube/1612437682220_테스트.png",
        "imgName": "테스트.png",
        "mainYn": "Y"
    }
]
```

### 유튜브 상세 조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectYoutubeDetail
PATH: /api/youtube/detail/{id}
*** id는 JPA에서 생성된 값으로 목록에서 가지고 있는 값을 넘겨주면 됌.
PARAM: 없음
```
* 결과 구조
```
{
    "id": 유튜브 아이디(JPA 생성),
    "uploadDate": 업로드 날짜,
    "title": 제목,
    "contents": 내용,
    "link": 유튜브 링크,
    "imgPath": 이미지 경로,
    "imgName": 이미지 명,
    "mainYn": 메인 노출 여부
}
```
* 결과 예시
```
{
    "id": 6,
    "uploadDate": "2021-02-04",
    "title": "유튜브6",
    "contents": "여섯번째 유튜브 입니다",
    "link": "localhost:8080/youtube",
    "imgPath": "/resources/static/img/youtube/1612437647277_테스트.png",
    "imgName": "테스트.png",
    "mainYn": "Y"
}
```

### 유튜브 등록
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : POST
METHOD NAME: insertYoutube
PATH : /api/youtube/new
PARAM : Map<String, Object>
=== PARAM 설명 ===
uploadDate: 업로드 날짜(YYYY-MM-DD)
title: 제목
contents: 내용
link:  유튜브 링크
file: 이미지 파일
mainYn: 메인 노출 여부 (Y or N)
```
* 결과 구조
```
{
    "id": 생성된 아이디 값을 리턴함
}
```
* 결과 예시
```
{
    "id": 13     
}
```