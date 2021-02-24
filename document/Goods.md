# Goods API

### 전체 굿즈 조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectGoodsList
PATH : /api/goods/all/{page}
```
* 결과 구조
```
[
    {
        "id": 아이디(JPA생성),
        "name": 굿즈 이름
        "price": 가격,
        "mainYn": 메인 노출 여부,
        "thumbnailFile": 썸네일 이미지(null로 넘어옴),
        "thumbnailImgPath": 썸네일 이미지 경로,
        "thumbnailImgName": 썸네일 이미지 이름,
        "detailFile": 상세 이미지(null로 넘어옴),
        "detailImgPath": 상세 이미지 경로,
        "detailImgName": 상세 이미지 이름,
        "optionList": [
            {
                "id": 옵션 아이디(JPA생성),
                "colorAndSize": 색상 & 사이즈,
                "inventory": 재고,
                "extraPrice": 추가 금액
            },
            ...
        ]
    },
    {
        "id": 아이디(JPA생성),
        "name": 굿즈 이름
        "price": 가격,
        "mainYn": 메인 노출 여부,
        "thumbnailFile": 썸네일 이미지(null로 넘어옴),
        "thumbnailImgPath": 썸네일 이미지 경로,
        "thumbnailImgName": 썸네일 이미지 이름,
        "detailFile": 상세 이미지(null로 넘어옴),
        "detailImgPath": 상세 이미지 경로,
        "detailImgName": 상세 이미지 이름,
        "optionList": [
            {
                "id": 옵션 아이디(JPA생성),
                "colorAndSize": 색상 & 사이즈,
                "inventory": 재고,
                "extraPrice": 추가 금액
            },
            ...
        ]
    },
    ...
]
```
* 결과 예시
```
[
    {
        "id": 1,
        "name": "반팔 티셔츠",
        "price": 25000.00,
        "mainYn": "N",
        "optionList": [
            {
                "id": 1,
                "colorAndSize": "White S",
                "inventory": 10,
                "extraPrice": 0.00
            },
            {
                "id": 2,
                "colorAndSize": "White M",
                "inventory": 10,
                "extraPrice": 0.00
            },
            {
                "id": 3,
                "colorAndSize": "White L",
                "inventory": 10,
                "extraPrice": 0.00
            }
        ]
    },
    {
        "id": 2,
        "name": "맨투맨",
        "price": 40000.00,
        "mainYn": "N",
        "optionList": [
            {
                "id": 4,
                "colorAndSize": "Black S",
                "inventory": 10,
                "extraPrice": 0.00
            },
            {
                "id": 5,
                "colorAndSize": "Black M",
                "inventory": 10,
                "extraPrice": 0.00
            },
            {
                "id": 6,
                "colorAndSize": "Black L",
                "inventory": 10,
                "extraPrice": 0.00
            }
        ]
    },
    ...
]
```

### 굿즈 상세
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectGoodsDetail
PATH : /api/goods/detail/{id}
```
* 결과 구조
```
{
        "id": 아이디(JPA생성),
        "name": 굿즈 이름
        "price": 가격,
        "mainYn": 메인 노출 여부,
        "thumbnailFile": 썸네일 이미지(null로 넘어옴),
        "thumbnailImgPath": 썸네일 이미지 경로,
        "thumbnailImgName": 썸네일 이미지 이름,
        "detailFile": 상세 이미지(null로 넘어옴),
        "detailImgPath": 상세 이미지 경로,
        "detailImgName": 상세 이미지 이름,
        "optionList": [
            {
                "id": 옵션 아이디(JPA생성),
                "colorAndSize": 색상 & 사이즈,
                "inventory": 재고,
                "extraPrice": 추가 금액
            },
            ...
        ]
    }
```
* 결과 예시
```
{
        "id": 1,
        "name": "반팔 티셔츠",
        "price": 25000.00,
        "mainYn": "N",
        "optionList": [
            {
                "id": 1,
                "colorAndSize": "White S",
                "inventory": 10,
                "extraPrice": 0.00
            },
            {
                "id": 2,
                "colorAndSize": "White M",
                "inventory": 10,
                "extraPrice": 0.00
            },
            {
                "id": 3,
                "colorAndSize": "White L",
                "inventory": 10,
                "extraPrice": 0.00
            }
        ]
    }
```

### 굿즈 등록
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST
METHOD NAME: insertGoods
PATH : /api/admin/goods/new
=== PARAM 설명 ===
{
        "id": 아이디(JPA생성),
        "name": 굿즈 이름
        "price": 가격,
        "mainYn": 메인 노출 여부,
        "optionList": [
            {
                "id": 옵션 아이디(JPA생성),
                "colorAndSize": 색상 & 사이즈,
                "inventory": 재고,
                "extraPrice": 추가 금액
            },
            ...
        ]
}
```
* 파라미터 예시
```
{
    "name": "반팔 티셔츠",
    "price": "25000",
    "mainYn": "N",
    "optionList": [
        {
            "colorAndSize": "White S",
            "inventory": "10",
            "extraPrice": "0"
        },
        {
            "colorAndSize": "White M",
            "inventory": "10",
            "extraPrice": "0"
        },
        {
            "colorAndSize": "White L",
            "inventory": "10",
            "extraPrice": "0"
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

### 굿즈 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: PUT
METHOD NAME: updateGoods
PATH : /api/admin/goods/update/{id}
=== PARAM 설명 ===
{
        "id": 굿즈 아이디(JPA 생성)
        "name": 굿즈 이름
        "price": 가격,
        "mainYn": 메인 노출 여부,
        "optionList": [
            {
                "id": 옵션 아이디(JPA생성),
                "colorAndSize": 색상 & 사이즈,
                "inventory": 재고,
                "extraPrice": 추가 금액
            },
            ...
        ]
}
```
* 파라미터 예시
```
{
    "id": 1,
    "name": "반팔티",
    "price": "25000",
    "mainYn": "N",
    "optionList": [
        {
            "id": 1,
            "colorAndSize": "White S",
            "inventory": "17",
            "extraPrice": "0"
        },
        {
            "id": 2,
            "colorAndSize": "White M",
            "inventory": "20",
            "extraPrice": "0"
        },
        {
            "id": 3,
            "colorAndSize": "White L",
            "inventory": "30",
            "extraPrice": "0"
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

### 굿즈 삭제
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: DELETE
METHOD NAME: deleteGoods
PATH : /api/admin/goods/delete
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