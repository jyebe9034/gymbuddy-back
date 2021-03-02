# User API

### 회원가입시 인증번호 메일 발송
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

### 비밀번호 찾기시 인증번호 메일 발송
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST
METHOD NAME: sendAuthNumberForPw
PATH : /api/userApi/sendAuthNumberForPw
PARAM : Map<String, Object>
=== PARAM 설명 ===
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
    "authNum": "6G1W4Z",
    "successYn": "Y",
    "authId": 28
}
{
    ** 가입된 이메일이 아닌 경우
    "msg": "회원정보가 없습니다.",
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
id : 아이디(JPA생성) -> 인증번호 발급시 리턴된 authId값을 넘겨주면 됌
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
METHOD NAME: login
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
    "id": 아이디(JPA 생성)
}
```
* 결과 예시
```
{
    "identity": "gymbuddy",
    "adminYn": "N",
    "jwt-token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqeWViZTkwMzRAZ21haWwuY29tIiwicm9sZXMiOltdLCJpYXQiOjE2MTQ0ODMyMDAsImV4cCI6MTYxNDQ4NTAwMH0.SCYDOyCZBRZEF7rbNdKIdCvA48dyBtLrvJwFoooP9Pw",
    "name": "김버디",
    "id": 4
}
```

### 로그아웃
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST(POST로 보내면 될 것 같긴 한데..만약 안되면 알려주세요!)
PATH : /api/user/logout
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
PATH : /api/userApi/all/{page}/{grade}
=== PARAM 설명 ===
page: 페이지 번호
grade: 회원 등급 [전체: all, 관리자: admin, 일반회원: normal]
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
        "id": 5,
        "grade": "NORMAL",
        "identity": "test",
        "email": "test123@gmail.com",
        "password": null,
        "name": "테스트",
        "phone": "010-1234-1234",
        "zipcode": null,
        "street1": null,
        "street2": null,
        "agreeYn": "Y"
    },
    {
        "id": 4,
        "grade": "NORMAL",
        "identity": "gymbuddy",
        "email": "jyebe9034@gmail.com",
        "password": null,
        "name": "김버디",
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
    "id": 5,
    "grade": "NORMAL",
    "identity": "test",
    "email": "test123@gmail.com",
    "password": null,
    "name": "테스트",
    "phone": "010-1234-1234",
    "zipcode": "12345",
    "street1": "서울시 광화문로 123",
    "street2": "201호",
    "agreeYn": "Y"
}
```

### 회원 등급 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: PUT
METHOD NAME: updateGrade
PATH : /api/userApi/updateGrade
PARAM : Map<String, Object>
=== PARAM 설명 ===
grade: 회원 등급 [전체: all, 관리자: admin, 일반회원: normal]
idList: [아이디, 아이디, ...]
```
* 결과구조
```
{
    "result": 결과여부
}
```
* 결과 예시
```
{
    "result": "success"
    *** 현재 모든 결과값을 성공으로 보내고 있음.
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

### 회원 삭제
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: DELETE
METHOD NAME: deleteUser
PATH : /api/admin/userApi/delete
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