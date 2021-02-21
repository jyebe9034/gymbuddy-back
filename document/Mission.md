# Mission API

### 운동친구 소개 조회
* 기본 정보 및 파라미터 설명
* 미션, 활동기록, BI가 리스트 형태로 전달되므로 biList.imgName 등으로 사용 가능
```
HTTP METHOD: GET
METHOD NAME: selectMission
PATH: /api/mission/allByMap
PARAM: 없음
```
* 결과 구조
```
{
    "biList": [
        {
            "createDate": 생성일,
            "createId": 생성자,
            "updateDate": 수정일,
            "updateId": 수정자,
            "id": 아이디(JPA 생성),
            "imgPath": 이미지 경로,
            "imgName": 이미지 명,
            "webMobile": 웹/모바일 여부
        },
        {
            "createDate": 생성일,
            "createId": 생성자,
            "updateDate": 수정일,
            "updateId": 수정자,
            "id": 아이디(JPA 생성),
            "imgPath": 이미지 경로,
            "imgName": 이미지 명,
            "webMobile": 웹/모바일 여부
        }
    ],
    "historyList": [
        {
            "createDate": 생성일
            "createId": 생성자,
            "updateDate": 수정일,
            "updateId": 수정자,
            "id": 아이디(JPA 생성),
            "historyDate": 활동기록 날짜,
            "title": 활동기록 제목
        },
        {
            "createDate": 생성일
            "createId": 생성자,
            "updateDate": 수정일,
            "updateId": 수정자,
            "id": 아이디(JPA 생성),
            "historyDate": 활동기록 날짜,
            "title": 활동기록 제목
        },
        ...
    ],
    "missionList": [
        {
            "createDate": 생성일
            "createId": 생성자,
            "updateDate": 수정일,
            "updateId": 수정자, 
            "id": 아이디(JPA 생성),
            "contents": "미션 소개",
            "imgPath1": 이미지 경로1,
            "imgName1": 이미지 명1,
            "imgPath2": 이미지 경로2,
            "imgName2": 이미지 명2,
            "imgPath3": 이미지 경로3,
            "imgName3": 이미지 명3
        }
    ]
}
```
* 결과 예시
```
{
    "biList": [
        {
            "createDate": "2021-02-09T16:27:21.334006",
            "createId": test,
            "updateDate": "2021-02-09T16:27:21.334475",
            "updateId": test,
            "id": 1,
            "imgPath": "/resources/static/img/bi1612855641265_cat.jpg",
            "imgName": "cat.jpg",
            "webMobile": "WEB"
        },
        {
            "createDate": "2021-02-09T16:27:35.568154",
            "createId": test,
            "updateDate": "2021-02-09T16:27:35.568179",
            "updateId": test,
            "id": 2,
            "imgPath": "/resources/static/img/bi1612855655513_cat2.jpg",
            "imgName": "cat2.jpg",
            "webMobile": "MOBILE"
        }
    ],
    "historyList": [
        {
            "createDate": "2021-02-09T16:27:49.231381",
            "createId": test,
            "updateDate": "2021-02-09T16:27:49.231405",
            "updateId": test,
            "id": 1,
            "historyDate": "2021-02-07",
            "title": "활동기록1"
        },
        {
            "createDate": "2021-02-09T16:27:56.072714",
            "createId": test,
            "updateDate": "2021-02-09T16:27:56.072744",
            "updateId": test,
            "id": 2,
            "historyDate": "2021-02-08",
            "title": "활동기록2"
        },
        {
            "createDate": "2021-02-09T16:28:00.317638",
            "createId": test,
            "updateDate": "2021-02-09T16:28:00.317691",
            "updateId": test,
            "id": 3,
            "historyDate": "2021-02-09",
            "title": "활동기록3"
        },
        ...
    ],
    "missionList": [
        {
            "createDate": "2021-02-09T16:31:42.983591",
            "createId": test,
            "updateDate": "2021-02-09T16:31:42.98362",
            "updateId": test,
            "id": 1,
            "contents": "미션 소개",
            "imgPath1": "/resources/static/img/mission/1612855902966_cat.jpg",
            "imgName1": "cat.jpg",
            "imgPath2": "/resources/static/img/mission/1612855902968_cat2.jpg",
            "imgName2": "cat2.jpg",
            "imgPath3": "/resources/static/img/mission/1612855902970_cat3.jpg",
            "imgName3": "cat3.jpg"
        }
    ]
}
```

### 미션(소개) 등록
* 등록은 백엔드에서 등록해줌. (수정만 가능)

### 미션(소개) 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : PUT
METHOD NAME: updateMission
PATH : /api/mission/update/{id}
PARAM : Map<String, Object>
=== PARAM 설명 ===
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

### 미션(소개) 삭제
* 미션은 삭제할 수 없고 수정만 가능함. (사진은 업데이트 시 삭제됨.)