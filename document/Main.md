# Main API

### 사용자쪽 메인(홈)화면에서 필요한 정보 조회
* 기본 정보 및 파라미터 설명시
```
HTTP METHOD: GET
METHOD NAME: selectAllMainInfo
PATH: /api/main/all
```
* 결과구조
```
{
    "newsList": [
        {
            "createDate": 생성일
            "createId": 생성자,
            "updateDate": 수정일,
            "updateId": 수정자,
            "id": 아이디(JPA 생성),
            "title": 제목,
            "contents": 내용,
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
            "contents": 내용,
            "imgPath": 이미지 경로,
            "imgName": 이미지 명
        },
        ...
    ],
    "noticeList": [
        {
            "createDate": 생성일
            "createId": 생성자,
            "updateDate": 수정일,
            "updateId": 수정자,
            "id": 아이디(JPA 생성),
            "title": 제목,
            "contents": 내용,
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
            "contents": 내용,
            "imgPath": 이미지 경로,
            "imgName": 이미지 명
        },
        ...
    ],
    ...
}
```
* 결과 예시
```
{
    "newsList": [
        {
            "createDate": "2021-02-20T16:31:56.5",
            "createId": null,
            "updateDate": "2021-02-20T16:31:56.5",
            "updateId": null,
            "id": 15,
            "title": "대외뉴스15",
            "contents": "대외뉴스15 입니다.",
            "imgPath": "/resources/static/img/news/1613806316470_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:31:50.474",
            "createId": null,
            "updateDate": "2021-02-20T16:31:50.474",
            "updateId": null,
            "id": 14,
            "title": "대외뉴스14",
            "contents": "대외뉴스14 입니다.",
            "imgPath": "/resources/static/img/news/1613806310443_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:31:44.088",
            "createId": null,
            "updateDate": "2021-02-20T16:31:44.088",
            "updateId": null,
            "id": 13,
            "title": "대외뉴스13",
            "contents": "대외뉴스13 입니다.",
            "imgPath": "/resources/static/img/news/1613806304058_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:31:38.016",
            "createId": null,
            "updateDate": "2021-02-20T16:31:38.016",
            "updateId": null,
            "id": 12,
            "title": "대외뉴스12",
            "contents": "대외뉴스12 입니다.",
            "imgPath": "/resources/static/img/news/1613806297989_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:31:30.176",
            "createId": null,
            "updateDate": "2021-02-20T16:31:30.176",
            "updateId": null,
            "id": 11,
            "title": "대외뉴스11",
            "contents": "대외뉴스11 입니다.",
            "imgPath": "/resources/static/img/news/1613806290145_테스트.png",
            "imgName": "테스트.png"
        }
    ],
    "noticeList": [
        {
            "createDate": "2021-02-20T16:30:09.247",
            "createId": null,
            "updateDate": "2021-02-20T16:30:09.248",
            "updateId": null,
            "id": 15,
            "title": "공지사항15",
            "contents": "공지사항 15입니다",
            "imgPath": "/resources/static/img/notice/1613806209218_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:30:03.805",
            "createId": null,
            "updateDate": "2021-02-20T16:30:03.805",
            "updateId": null,
            "id": 14,
            "title": "공지사항14",
            "contents": "공지사항 14입니다",
            "imgPath": "/resources/static/img/notice/1613806203778_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:29:57.306",
            "createId": null,
            "updateDate": "2021-02-20T16:29:57.306",
            "updateId": null,
            "id": 13,
            "title": "공지사항13",
            "contents": "공지사항 13입니다",
            "imgPath": "/resources/static/img/notice/1613806197277_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:29:49.234",
            "createId": null,
            "updateDate": "2021-02-20T16:29:49.234",
            "updateId": null,
            "id": 12,
            "title": "공지사항12",
            "contents": "공지사항 12입니다",
            "imgPath": "/resources/static/img/notice/1613806189205_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:29:43.082",
            "createId": null,
            "updateDate": "2021-02-20T16:29:43.082",
            "updateId": null,
            "id": 11,
            "title": "공지사항11",
            "contents": "공지사항 11입니다",
            "imgPath": "/resources/static/img/notice/1613806183049_테스트.png",
            "imgName": "테스트.png"
        }
    ],
    "bannerList": [
        {
            "createDate": "2021-02-20T16:27:03.184",
            "createId": null,
            "updateDate": "2021-02-20T16:27:03.184",
            "updateId": null,
            "id": 1,
            "title": "배너1",
            "categoryId": "GD",
            "link": "localhost:8080",
            "btnTitle": "보러가기",
            "imgPath": "/resources/static/img/banner/1613806023149_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:27:06.471",
            "createId": null,
            "updateDate": "2021-02-20T16:27:06.471",
            "updateId": null,
            "id": 2,
            "title": "배너2",
            "categoryId": "GD",
            "link": "localhost:8080",
            "btnTitle": "보러가기",
            "imgPath": "/resources/static/img/banner/1613806026442_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:27:09.063",
            "createId": null,
            "updateDate": "2021-02-20T16:27:09.063",
            "updateId": null,
            "id": 3,
            "title": "배너3",
            "categoryId": "GD",
            "link": "localhost:8080",
            "btnTitle": "보러가기",
            "imgPath": "/resources/static/img/banner/1613806029037_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:27:11.644",
            "createId": null,
            "updateDate": "2021-02-20T16:27:11.644",
            "updateId": null,
            "id": 4,
            "title": "배너4",
            "categoryId": "GD",
            "link": "localhost:8080",
            "btnTitle": "보러가기",
            "imgPath": "/resources/static/img/banner/1613806031615_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:27:14.405",
            "createId": null,
            "updateDate": "2021-02-20T16:27:14.405",
            "updateId": null,
            "id": 5,
            "title": "배너5",
            "categoryId": "GD",
            "link": "localhost:8080",
            "btnTitle": "보러가기",
            "imgPath": "/resources/static/img/banner/1613806034377_테스트.png",
            "imgName": "테스트.png"
        }
    ],
    "youtubeList": [
        {
            "createDate": "2021-02-20T16:26:42.734",
            "createId": null,
            "updateDate": "2021-02-20T16:26:42.734",
            "updateId": null,
            "id": 15,
            "uploadDate": "2021-02-20",
            "title": "유튜브15",
            "contents": "열다섯번째 유튜브",
            "link": "localhost:8080/youtube",
            "imgPath": "/resources/static/img/youtube/1613806002709_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:26:31.683",
            "createId": null,
            "updateDate": "2021-02-20T16:26:31.683",
            "updateId": null,
            "id": 14,
            "uploadDate": "2021-02-20",
            "title": "유튜브14",
            "contents": "열네번째 유튜브",
            "link": "localhost:8080/youtube",
            "imgPath": "/resources/static/img/youtube/1613805991650_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:26:20.178",
            "createId": null,
            "updateDate": "2021-02-20T16:26:20.178",
            "updateId": null,
            "id": 13,
            "uploadDate": "2021-02-20",
            "title": "유튜브13",
            "contents": "열세번째 유튜브",
            "link": "localhost:8080/youtube",
            "imgPath": "/resources/static/img/youtube/1613805980150_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:26:12.622",
            "createId": null,
            "updateDate": "2021-02-20T16:26:12.622",
            "updateId": null,
            "id": 12,
            "uploadDate": "2021-02-20",
            "title": "유튜브12",
            "contents": "열두번째 유튜브",
            "link": "localhost:8080/youtube",
            "imgPath": "/resources/static/img/youtube/1613805972599_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:26:06.371",
            "createId": null,
            "updateDate": "2021-02-20T16:26:06.371",
            "updateId": null,
            "id": 11,
            "uploadDate": "2021-02-20",
            "title": "유튜브11",
            "contents": "열한번째 유튜브",
            "link": "localhost:8080/youtube",
            "imgPath": "/resources/static/img/youtube/1613805966349_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:25:56.892",
            "createId": null,
            "updateDate": "2021-02-20T16:25:56.892",
            "updateId": null,
            "id": 10,
            "uploadDate": "2021-02-20",
            "title": "유튜브10",
            "contents": "열번째 유튜브",
            "link": "localhost:8080/youtube",
            "imgPath": "/resources/static/img/youtube/1613805956865_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:25:49.679",
            "createId": null,
            "updateDate": "2021-02-20T16:25:49.679",
            "updateId": null,
            "id": 9,
            "uploadDate": "2021-02-20",
            "title": "유튜브9",
            "contents": "아홉번째 유튜브",
            "link": "localhost:8080/youtube",
            "imgPath": "/resources/static/img/youtube/1613805949656_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:25:24.382",
            "createId": null,
            "updateDate": "2021-02-20T16:25:24.382",
            "updateId": null,
            "id": 8,
            "uploadDate": "2021-02-20",
            "title": "유튜브8",
            "contents": "여덟번째 유튜브",
            "link": "localhost:8080/youtube",
            "imgPath": "/resources/static/img/youtube/1613805924360_테스트.png",
            "imgName": "테스트.png"
        },
        {
            "createDate": "2021-02-20T16:25:14.196",
            "createId": null,
            "updateDate": "2021-02-20T16:25:14.196",
            "updateId": null,
            "id": 7,
            "uploadDate": "2021-02-20",
            "title": "유튜브7",
            "contents": "일곱번째 유튜브",
            "link": "localhost:8080/youtube",
            "imgPath": "/resources/static/img/youtube/1613805914174_테스트.png",
            "imgName": "테스트.png"
        }
    ],
    "programList": [
        {
            "id": 3,
            "title": "원데이 클래스: 크로스핏3",
            "coach": "김코치",
            "classAddress": "강남역 10번출구 00빌딩 5층",
            "classDate": "2021.03.13 - 2021.04.10",
            "classTime": "토요일 오후 2시, 일요일 오후 3시",
            "price": 55000.00,
            "mainYn": "Y",
            "thumbnailFile": null,
            "thumbnailImgPath": null,
            "thumbnailImgName": null,
            "detailFile": null,
            "detailImgPath": null,
            "detailImgName": null,
            "optionList": null
        }
    ],
    "columnsList": [
        {
            "createDate": "2021-02-20T16:37:33.887",
            "createId": null,
            "updateDate": "2021-02-20T16:37:33.887",
            "updateId": null,
            "id": 15,
            "title": "컬럼15",
            "contents": "컬럼15 입니다.",
            "imgPath": "/resources/static/img/columns/1613806653853_테스트.png",
            "imgName": "1613806653853_테스트.png",
            "mainYn": "N"
        },
        {
            "createDate": "2021-02-20T16:37:26.868",
            "createId": null,
            "updateDate": "2021-02-20T16:37:26.868",
            "updateId": null,
            "id": 14,
            "title": "컬럼14",
            "contents": "컬럼14 입니다.",
            "imgPath": "/resources/static/img/columns/1613806646835_테스트.png",
            "imgName": "1613806646835_테스트.png",
            "mainYn": "N"
        },
        {
            "createDate": "2021-02-20T16:37:20.957",
            "createId": null,
            "updateDate": "2021-02-20T16:37:20.957",
            "updateId": null,
            "id": 13,
            "title": "컬럼13",
            "contents": "컬럼13 입니다.",
            "imgPath": "/resources/static/img/columns/1613806640926_테스트.png",
            "imgName": "1613806640926_테스트.png",
            "mainYn": "N"
        },
        {
            "createDate": "2021-02-20T16:37:15.169",
            "createId": null,
            "updateDate": "2021-02-20T16:37:15.169",
            "updateId": null,
            "id": 12,
            "title": "컬럼12",
            "contents": "컬럼12 입니다.",
            "imgPath": "/resources/static/img/columns/1613806635133_테스트.png",
            "imgName": "1613806635133_테스트.png",
            "mainYn": "N"
        },
        {
            "createDate": "2021-02-20T16:37:10.746",
            "createId": null,
            "updateDate": "2021-02-20T16:37:10.746",
            "updateId": null,
            "id": 11,
            "title": "컬럼11",
            "contents": "컬럼11 입니다.",
            "imgPath": "/resources/static/img/columns/1613806630710_테스트.png",
            "imgName": "1613806630710_테스트.png",
            "mainYn": "N"
        },
        {
            "createDate": "2021-02-20T16:37:02.158",
            "createId": null,
            "updateDate": "2021-02-20T16:37:02.158",
            "updateId": null,
            "id": 10,
            "title": "컬럼10",
            "contents": "컬럼10 입니다.",
            "imgPath": "/resources/static/img/columns/1613806622122_테스트.png",
            "imgName": "1613806622122_테스트.png",
            "mainYn": "N"
        },
        {
            "createDate": "2021-02-20T16:36:56.385",
            "createId": null,
            "updateDate": "2021-02-20T16:36:56.385",
            "updateId": null,
            "id": 9,
            "title": "컬럼9",
            "contents": "컬럼9 입니다.",
            "imgPath": "/resources/static/img/columns/1613806616350_테스트.png",
            "imgName": "1613806616350_테스트.png",
            "mainYn": "N"
        },
        {
            "createDate": "2021-02-20T16:36:50.854",
            "createId": null,
            "updateDate": "2021-02-20T16:36:50.854",
            "updateId": null,
            "id": 8,
            "title": "컬럼8",
            "contents": "컬럼8 입니다.",
            "imgPath": "/resources/static/img/columns/1613806610820_테스트.png",
            "imgName": "1613806610820_테스트.png",
            "mainYn": "N"
        },
        {
            "createDate": "2021-02-20T16:36:44.614",
            "createId": null,
            "updateDate": "2021-02-20T16:36:44.614",
            "updateId": null,
            "id": 7,
            "title": "컬럼7",
            "contents": "컬럼7 입니다.",
            "imgPath": "/resources/static/img/columns/1613806604579_테스트.png",
            "imgName": "1613806604579_테스트.png",
            "mainYn": "N"
        }
    ]
}
```