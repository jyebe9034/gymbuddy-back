# History API

### 활동기록 조회
* 미션에서 활동기록과 BI를 포함하여 전체 조회함.

### 활동기록 등록
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : POST
METHOD NAME: insertHistory
PATH : /api/admin/history/new
PARAM : Map<String, Object>
=== PARAM 설명 ===
{
    "historyDate": 활동기록 날짜,
    "contents": 활동기록 내용
}
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

### 활동기록 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : PUT
METHOD NAME: updateHistory
PATH : /api/admin/history/update
PARAM : List<HistoryDto>
수정하는 활동기록을 포함하여 모든 활동기록 리스트를 넘기고, 새로운 활동기록은 아이디를 제외하고 날짜와 내용을 넘긴다.
[
    {
        "id": 아이디(JPA 생성),
        "historyDate": 활동기록 날짜,
        "contents": 활동기록 내용
    },
    {
        "id": 아이디(JPA 생성),
        "historyDate": 활동기록 날짜,
        "contents": 활동기록 내용
    },
    ...
]
```
* 파라미터 예시
```
[
    {
        "id": 1,
        "historyDate": "2020년 10월",
        "contents": "활동기록1"
    },
    {
        "id": 2,
        "historyDate": "2020년 12월",
        "contents": "활동기록2"
    },
    {
        "id" : 3,
        "historyDate": "2021년 02월",
        "contents": "활동기록3"
    }
    ...
]
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