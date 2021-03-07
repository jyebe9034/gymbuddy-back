# BI API

### BI 조회
* 미션에서 활동기록과 BI를 포함하여 전체 조회함.

### BI 웹 등록
* 등록은 백엔드에서 등록해줌. (수정만 가능)

### BI 모바일 등록
* 등록은 백엔드에서 등록해줌. (수정만 가능)

### BI 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD : PUT
METHOD NAME: updateMember
PATH : /api/admin/businessIdentity/update/{id}
PARAM : Map<String, Object>
=== PARAM 설명 ===
id: 1 - 웹, 2 - 모바일
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

### BI 삭제
* BI는 삭제할 수 없고 수정만 가능함. (사진은 업데이트 시 삭제됨.)