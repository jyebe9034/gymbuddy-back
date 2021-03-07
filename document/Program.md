# Program API

### 전체 프로그램 갯수조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectProgramTotalCount
PATH: /api/program/totalCount
PARAM: 없음
```
* 결과구조
```
{
    "totalCount": 전체 프로그램 갯수
}
```
* 결과 에시
```
{
    "totalCount": 1
}
```

### 전체 프로그램 조회(관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectProgramList
PATH : /api/program/all/{page}
```
* 결과 구조
```
[
    {
        "goodsList": [
            "id": 아이디(JPA생성),
            "title": 제목,
            "coach": 강사이름,
            "classAddress": 클래스 장소,
            "classDate": 클래스 기간,
            "classTime": 클래스 시간,
            "price": 금액,
            "mainYn": 메인 노출 여부,
            "thumbnailFile": 썸네일 파일(null로 넘어옴),
            "thumbnailImgPath": 썸네일 이미지 경로,
            "thumbnailImgName": 썸네일 이미지 이름,
            "detailFile": 상세 파일(null로 넘어옴),
            "detailImgPath": 상세 이미지 경로,
            "detailImgName": 상세 이미지 이름,
            "optionList": [
                {
                    "id": 옵션 아이디(JPA생성),
                    "classDateTime": 구체적인 일자와 시간,
                    "userCount": 참여 가능 인원,
                    "addPrice": 추가 금액
                },
                ...
            ],
        ],
        "mainCounts": 메인 갯수
]
```
* 결과 예시
```
[
    {
        "goodsList": [
                "id": 3,
                "title": "원데이 클래스: 크로스핏3",
                "coach": "김코치",
                "classAddress": "강남역 10번출구 00빌딩 5층",
                "classDate": "2021.03.13 - 2021.04.10",
                "classTime": "토요일 오후 2시, 일요일 오후 3시",
                "price": 55000.00,
                "mainYn": "N",
                "thumbnailFile": null,
                "thumbnailImgPath": null,
                "thumbnailImgName": null,
                "detailFile": null,
                "detailImgPath": null,
                "detailImgName": null,
                "optionList": [
                    {
                        "id": 6,
                        "classDateTime": "2021년3월13일 오후 4시",
                        "userCount": 14,
                        "addPrice": 4000.00
                    },
                    {
                        "id": 7,
                        "classDateTime": "2021년3월13일 오후 3시",
                        "userCount": 15,
                        "addPrice": 5000.00
                    },
                    {
                        "id": 8,
                        "classDateTime": "2021년3월13일 오후 2시",
                        "userCount": 10,
                        "addPrice": 10000.00
                    }
                ]
        ],
        "mainCounts": 4
]
```

### 프로그램 상세
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectProgramDetail
PATH : /api/program/detail/{id}
```
* 결과 구조
```
{
    "id": 아이디(JPA생성),
    "title": 제목,
    "coach": 강사이름,
    "classAddress": 클래스 장소,
    "classDate": 클래스 기간,
    "classTime": 클래스 시간,
    "price": 금액,
    "mainYn": 메인 노출 여부,
    "thumbnailFile": 썸네일 파일(null로 넘어옴),
    "thumbnailImgPath": 썸네일 이미지 경로,
    "thumbnailImgName": 썸네일 이미지 이름,
    "detailFile": 상세 파일(null로 넘어옴),
    "detailImgPath": 상세 이미지 경로,
    "detailImgName": 상세 이미지 이름,
    "optionList": [
        {
            "id": 옵션 아이디(JPA생성),
            "classDateTime": 구체적인 일자와 시간,
            "userCount": 참여 가능 인원,
            "addPrice": 추가 금액
        },
        ...
    ]
}
```
* 결과 예시
```
{
    "id": 3,
    "title": "원데이 클래스: 크로스핏3",
    "coach": "김코치",
    "classAddress": "강남역 10번출구 00빌딩 5층",
    "classDate": "2021.03.13 - 2021.04.10",
    "classTime": "토요일 오후 2시, 일요일 오후 3시",
    "price": 55000.00,
    "mainYn": "N",
    "thumbnailFile": null,
    "thumbnailImgPath": null,
    "thumbnailImgName": null,
    "detailFile": null,
    "detailImgPath": null,
    "detailImgName": null,
    "optionList": [
        {
            "id": 6,
            "classDateTime": "2021년3월13일 오후 4시",
            "userCount": 14,
            "addPrice": 4000.00
        },
        {
            "id": 7,
            "classDateTime": "2021년3월13일 오후 3시",
            "userCount": 15,
            "addPrice": 5000.00
        },
        {
            "id": 8,
            "classDateTime": "2021년3월13일 오후 2시",
            "userCount": 10,
            "addPrice": 10000.00
        }
    ]
}
```

### 프로그램 등록
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST
METHOD NAME: insertProgram
PATH : /api/admin/program/new
=== PARAM 설명 ===
{
    "thumbnailFile" : 썸네일 파일
    "detailFile" : 상세 파
    "title" : 제목
    "coach" : 강사이름
    "classAddress" : 클래스 장소
    "classDate" : 클래스가 열리는 기간(일자)
    "classTime" : 클래스가 열리는 시간
    "price" : 금액(가격)
    "mainYn" : 메인 노출 여부(Y or N)
    "optionList" : [
        {
            "classDateTime" : 구체적인 일자와 시간
            "userCount" : 참여 가능 인원
            "addPrice" : 추가 금액
        },
        ...
    ]
}
```
* 파라미터 예시
```
{
    "thumbnailFile" : "thumbnail.jpg",
    "detailFile" : "detail.jpg",
    "title" : "원데이 클래스: 주짓수",
    "coach" : "김짓수",
    "classAddress" : "홍대역 9번출구 00빌딩 5층",
    "classDate" : "2021.03.06 - 2021.04.10",
    "classTime" : "토요일 오후 2시, 일요일 오후 3시",
    "price" : "50000",
    "mainYn" : "N",
    "optionList" : [
        {
            "classDateTime" : "2021년3월6일 오후 2시",
            "userCount" : "10",
            "addPrice" : "10000"
        },
        {
            "classDateTime" : "2021년3월7일 오후 3시",
            "userCount" : "12",
            "addPrice" : "10000"
        },
        {
            "classDateTime" : "2021년3월13일 오후 2시",
            "userCount" : "10",
            "addPrice" : "10000"
        }
    ]
}
```
* 결과 구조
```
{
    "id": 3
}
```
* 결과 예시
```
{
    "id": 3
}
```
### 프로그램 상태 변경
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: PUT
METHOD NAME: updateProgramStatus
PATH : /api/admin/program/updateStatus/{status}
*** 변경하는 상태값(INPROGRESS, COMPLETE, TUMBLBUG)을 Path에 입력
PARAM : List<Integer>
=== PARAM 설명 ===
* 아이디 배열을 넘긴다.
```
* 파라미터 구조
```
[아이디, 아이디, 아이디] 
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

### 프로그램 메인 설정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: PUT
METHOD NAME: setProgramMainYn
PATH : /api/admin/program/setMainYn/{id}/{mainYn}
*** 변경하는 id와 메인 여부를 Path에 입력
=== PARAM 설명 ===
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

### 프로그램 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: PUT
METHOD NAME: updateProgram
PATH : /api/admin/program/update/{id}
=== PARAM 설명 ===
{
    "title" : 제목
    "coach" : 강사이름
    "classAddress" : 클래스 장소
    "classDate" : 클래스가 열리는 기간
    "classTime" : 클래스가 열리는 시간
    "price" : 금액
    "mainYn" : 메인 노출 여부
    "optionList" : [
        {
            "id" : 아이디(JPA 생성)
            "classDateTime" : 구체적인 일자와 시간
            "userCount" : 참여 가능 인원
            "addPrice" : 추가 금액
        },
        ...
    ]
}
```
* 파라미터 예시
```
{
    "title" : "원데이 클래스: 크로스핏",
    "coach" : "김코치",
    "classAddress" : "강남역 10번출구 00빌딩 5층",
    "classDate" : "2021.03.13 - 2021.04.10",
    "classTime" : "토요일 오후 2시, 일요일 오후 3시",
    "price" : "55000",
    "mainYn" : "N",
    "optionList" : [
        {
            "id" : "6",
            "classDateTime" : "2021년3월13일 오후 4시",
            "userCount" : "14",
            "addPrice" : "4000"
        },
        {
            "id" : "7",
            "classDateTime" : "2021년3월13일 오후 3시",
            "userCount" : "15",
            "addPrice" : "5000"
        }
    ]
}
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

### 프로그램 삭제
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: DELETE
METHOD NAME: deleteProgram
PATH : /api/admin/program/delete
PARAM : List<nteger>
=== PARAM 설명 ===
아이디 배열을 넘기면 됌
```
* 파라미터 구조
```
[아이디, 아이디, 아이디] 
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
