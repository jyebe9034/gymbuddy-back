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
=== PARAM 설명 ===
수정하는 활동기록들의 리스트를 넘긴다.
*** 수정 시에는 모든 값을 넘길 필요는 없음. 수정할 값만 넘겨도 수정 가능함.
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

### 활동기록 삭제
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : DELETE
METHOD NAME: deleteHistory
PATH : /api/admin/history/delete
*** id는 JPA에서 생성된 값으로 목록에서 가지고 있는 값을 넘겨주면 됨.
PARAM : List<Integer>
=== PARAM 설명 ===
* 아이디 배열을 넘긴다.
```
* 파라미터 구조
```
[아이디, 아이디, 아이디] 
```
* 결과 예시
```
{
    "result": "success"
    *** 현재 모든 결과값을 성공으로 보내고 있음.
}
```