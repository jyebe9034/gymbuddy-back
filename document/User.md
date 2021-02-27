# User API

### 인증번호 메일 발송
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST
METHOD NAME: sendAuthNumber
PATH : /api/userApi/sendAuthNumber
PARAM : Map<String, Object>
=== PARAM 설명 ===
name : 이름
email : 이메일
```
* 결과구조
```
{
    authNum: 인증번호
    successYn": 성공여부
    authId: 아이디(JPA 생성)
}
```
* 결과 예시
```
{
    "authNum": "Z2XB1Y",
    "successYn": "Y",
    "authId": 1
}
{
    "msg": "이미 가입된 이메일입니다.",
    "successYn": "N"
}
```

### 인증번호 일치 확인
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST
METHOD NAME: checkAuthNum
PATH : /api/userApi/checkAuthNum
PARAM : Map<String, Object>
=== PARAM 설명 ===
id : 아이디(JPA생성)
email : 이메일
authNum: 인증번호 6자리(대문자, 숫자 조합 6자리)
```
* 결과구조
```
{
    "result": 일치 여부(Y OR N)
}
```
* 결과 예시
```
{
    "result": "Y"
}
{
    "result": "N"
}
```

### 임시 비밀번호 메일 발송
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST
METHOD NAME: sendOnetimePassword
PATH : /api/userApi/onetimePw
PARAM : Map<String, Object>
=== PARAM 설명 ===
id : 아이디
name : 이름
email : 이메일
```
* 결과구조
```
{
    "onetimePw": 임시 비밀번호(대문자, 숫자 조합 10자리)
    "successYn": 임시 비밀번호 발급 여부(Y OR N)
}
```
* 결과 예시
```
{
    "onetimePw": "QOTQ307GL2",
    "successYn": "Y"
}
{
    "msg": "가입된 회원정보가 없습니다. \n 다시 시도해주세요",
    "successYn": "N"
}
```

### 회원가입
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST
METHOD NAME: sendAuthNumber
PATH : /api/userApi/join
PARAM : Map<String, Object>
=== PARAM 설명 ===
identity : 회원아이디
email : 이메일
password : 비밀번호
name : 이름
phone : 전화번호
zipcode : 우편번호
street1 : 주소
street2 : 상세주소
agreeYn : 광고 동의 여부
```
* 결과구조
```
{
    "id": 아이디(JPA가 생성한 아이디),
    "successYn": 회원가입 성공여부
}
```
* 결과 예시
```
{
    "id": 2,
    "successYn": "Y"
}
```
```
{
    "msg": "이미 사용중인 아이디입니다.",
    "successYn": "N"
}
```
```
{
    "msg": "이미 가입된 이메일입니다.",
    "successYn": "N"
}
```

### 로그인
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST
METHOD NAME: sendAuthNumber
PATH : /api/userApi/login
PARAM : Map<String, Object>
=== PARAM 설명 ===
identity : 회원아이디
password : 비밀번호
```
* 결과구조
```
{
    "identity": 회원 아이디
    "jwt-token": 발행된 토큰
    "adminYn": 관리자 여부(Y or N)
    "name": 이름
}
```
* 결과 예시
```
{
    "identity": "왜이래", (이건 가데이터가 잘못 들어가 있는거에요ㅎㅎ아이디는 영문과 숫자 조합만 가능합니다!)
    "jwt-token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqaWh5ZS50MDIyMUBnbWFpbC5jb20iLCJyb2xlcyI6W10sImlhdCI6MTYxNDA4NDc0NCwiZXhwIjoxNjE0MDg2NTQ0fQ.Uf5luhR_fixgkMFI9rIw8eeXREbixX-aHn6AymXzylI",
    "adminYn": "Y",
    "name": "김운동"
}
```

### 로그아웃
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST(POST로 보내면 될 것 같긴 한데..만약 안되면 알려주세요!)
PATH : /api/userApi/logout
** 로그아웃 시 추가적으로 필요한 파라미터는 없음. 다만 header에 jwt-token이 있어야 할 것 같음..
```

### 전체 회원정보 갯수조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectUserTotalCount
PATH: /api/userApi/totalCount
PARAM: 없음
```
* 결과구조
```

```
* 결과 에시
```

```

### 전체 회원정보 조회(관리자)
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectUserList
PATH : /api/userApi/all/{page}
```
* 결과구조
```
[
    {
        "id": 아이디,
        "grade": 회원등급,
        "identity": 회원아이디,
        "email": 이메일,
        "password": 비밀번호(null로 넘어옴),
        "name": 이름,
        "phone": 전화번호,
        "zipcode": 우편번호(null로 넘어옴),
        "street1": 주소(null로 넘어옴),
        "street2": 상세 주소(null로 넘어옴),
        "agreeYn": 광고 동의 여부
    }
]
```
* 결과 예시
```
[
    {
        "id": 1,
        "grade": "NORMAL",
        "identity": "test",
        "email": "jyebe9034@gmail.com",
        "password": null,
        "name": "김운동",
        "phone": "010-1234-1234",
        "zipcode": null,
        "street1": null,
        "street2": null,
        "agreeYn": "Y"
    },
    {
        "id": 2,
        "grade": "NORMAL",
        "identity": "test2",
        "email": "jihye.t0221@gmail.com",
        "password": null,
        "name": "김운동",
        "phone": "010-1234-1234",
        "zipcode": null,
        "street1": null,
        "street2": null,
        "agreeYn": "Y"
    }
]
```

### 회원 상세 정보 조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME: selectUserDetail
PATH : /api/userApi/detail/{id}
```
* 결과구조
```
{
    "id": 아이디,
    "grade": 회원등급,
    "identity": 회원아이디,
    "email": 이메일,
    "password": 비밀번호(null로 넘어옴),
    "name": 이름,
    "phone": 전화번호,
    "zipcode": 우편번호(null로 넘어옴),
    "street1": 주소(null로 넘어옴),
    "street2": 상세 주소(null로 넘어옴),
    "agreeYn": 광고 동의 여부
}
```
* 결과 예시
```
{
    "id": null,
    "grade": null,
    "identity": "test",
    "email": "jyebe9034@gmail.com",
    "password": null,
    "name": "김운동",
    "phone": "010-1234-1234",
    "zipcode": "12345",
    "street1": "서울시 광화문로 123",
    "street2": "201호",
    "agreeYn": "Y"
}
```

### 회원 정보 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: PUT
METHOD NAME: updateUser
PATH : /api/userApi/update/{id}
PARAM : Map<String, Object>
=== PARAM 설명 ===
name : 이름
password : 비밀번호
phone : 전화번호
zipcode : 우편번호
street1 : 주소
street2 : 상세주소
agreeYn : 광고 동의 여부
```
* 결과구조
```
{
    "result": 결과(true OR false로 넘어옴)
}
```
* 결과 예시
```
{
    "result": true
}
```

### 회원 탈퇴
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: DELETE
METHOD NAME: signOut
PATH : /api/userApi/delete/{id}
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
