# 컬럼 작성자 API

### 전체 칼럼 작성자 목록 조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : GET
PATH : /api/columnWriter/all
PARAM : 없음
```
* 결과 구조
```
[
    {
        "id": "칼럼 작성자 아이디(JPA 생성)",
        "name": "칼럼 작성자 이름",
        "job": "칼럼 작성자 직업",
        "contents": "칼럼 작성자에 대한 설명"
    }
]
```
* 결과 예시
```
[
    {
        "id": "1",
        "name": "김작가",
        "job": "작가",
        "contents": "김작가는 작가로 활동한 지 2년이 되었다."
    }
]
```
