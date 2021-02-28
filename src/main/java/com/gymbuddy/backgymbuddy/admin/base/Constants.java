package com.gymbuddy.backgymbuddy.admin.base;

/**
 * API(ajax call) 관련 상수 Class
 */
public final class Constants {

    /** 관리자만 접근 가능한 API 경로 PREFIX */
    public static final String ADMIN_AUTH = "/api/admin";

    /** 회원만 접근 가능한 API 경로 PREFIX */
    public static final String USER_AUTH = "/api/user";

    /** 회원가입 및 로그인과 같은 회원 관리를 위한 API 경로 PREFIX */
    public static final String ADMIN_USER_API = ADMIN_AUTH + "/userApi";

    /** 회원가입 및 로그인과 같은 회원 관리를 위한 API 경로 PREFIX */
    public static final String USER_API = "/api/userApi";

    /** 사용자 쪽 메인화면 API 경로 PREFIX */
    public static final String MAIN_PREFIX = "/api/main";

    /** 관리자 메인 배너 API 경로 PREFIX */
    public static final String ADMIN_BANNER_PREFIX = ADMIN_AUTH + "/banner";

    /** 메인 배너 API 경로 PREFIX */
    public static final String BANNER_PREFIX = "/api/banner";

    /** 관리자 BI API 경로 PREFIX */
    public static final String ADMIN_BI_PREFIX = ADMIN_AUTH + "/businessIdentity";

    /** BI API 경로 PREFIX */
    public static final String BI_PREFIX = "/api/businessIdentity";

    /** 관리자 장바구니 API 경로 PREFIX */
    public static final String ADMIN_CART_PREFIX = ADMIN_AUTH + "/cart";

    /** 회원 장바구니 API 경로 PREFIX */
    public static final String USER_CART_PREFIX = USER_AUTH + "/cart";

    /** 관리자 컬럼 API 경로 PREFIX */
    public static final String ADMIN_COLUMN_PREFIX = ADMIN_AUTH + "/column";

    /** 컬럼 API 경로 PREFIX */
    public static final String COLUMN_PREFIX = "/api/column";

    /** 관리자 컬럼 작성자 API 경로 PREFIX */
    public static final String ADMIN_COLUMN_WRITER_PREFIX = ADMIN_AUTH + "/columnWriter";

    /** 컬럼 작성자 API 경로 PREFIX */
    public static final String COLUMN_WRITER_PREFIX = "/api/columnWriter";

    /** 관리자 자주묻는질문 API 경로 PREFIX */
    public static final String ADMIN_FQ_PREFIX = ADMIN_AUTH + "/frequencyQuestion";

    /** 자주묻는질문 API 경로 PREFIX */
    public static final String FQ_PREFIX = "/api/frequencyQuestion";

    /** 관리자 굿즈 API 경로 PREFIX */
    public static final String ADMIN_GOODS_PREFIX = ADMIN_AUTH + "/goods";

    /** 굿즈 API 경로 PREFIX */
    public static final String GOODS_PREFIX = "/api/goods";

    /** 관리자 활동 기록 API 경로 PREFIX */
    public static final String ADMIN_HISTORY_PREFIX = ADMIN_AUTH + "/history";

    /** 활동 기록 API 경로 PREFIX */
    public static final String HISTORY_PREFIX = "/api/history";

    /** 관리자 멤버 API 경로 PREFIX */
    public static final String ADMIN_MEMBER_PREFIX = ADMIN_AUTH + "/member";

    /** 멤버 API 경로 PREFIX */
    public static final String MEMBER_PREFIX = "/api/member";

    /** 관리자 미션과 비전 API 경로 PREFIX */
    public static final String ADMIN_MISSION_PREFIX = ADMIN_AUTH + "/mission";

    /** 미션과 비전 API 경로 PREFIX */
    public static final String MISSION_PREFIX = "/api/mission";

    /** 관리자 대외뉴스 API 경로 PREFIX */
    public static final String ADMIN_NEWS_PREFIX = ADMIN_AUTH + "/news";

    /** 대외뉴스 API 경로 PREFIX */
    public static final String NEWS_PREFIX = "/api/news";

    /** 뉴스레터 API 경로 PREFIX */
    public static final String NEWSLETTER_PREFIX = "/api/newsLetter";

    /** 관리자 공지사항 API 경로 PREFIX */
    public static final String ADMIN_NOTICE_PREFIX = ADMIN_AUTH + "/notice";

    /** 공지사항 API 경로 PREFIX */
    public static final String NOTICE_PREFIX = "/api/notice";

    /** 관리자 주문 API 경로 PREFIX */
    public static final String ADMIN_ORDER_PREFIX = ADMIN_AUTH + "/order";

    /** 회원 주문 API 경로 PREFIX */
    public static final String USER_ORDER_PREFIX = USER_AUTH + "/order";

    /** 관리자 프로그램 API 경로 PREFIX */
    public static final String ADMIN_PROGRAM_PREFIX = ADMIN_AUTH + "/program";

    /** 프로그램 API 경로 PREFIX */
    public static final String PROGRAM_PREFIX = "/api/program";

    /** 관리자 1:1 문의 API 경로 PREFIX */
    public static final String ADMIN_QUESTION_PREFIX = ADMIN_AUTH + "/question";

    /** 회원 1:1 문의 API 경로 PREFIX */
    public static final String USER_QUESTION_PREFIX = USER_AUTH + "/question";

    /** 관리자 후기 API 경로 PREFIX */
    public static final String ADMIN_REVIEW_PREFIX = ADMIN_AUTH + "/review";

    /** 후기 API 경로 PREFIX */
    public static final String REVIEW_PREFIX = "/api/review";

    /** 관리자 약관 API 경로 PREFIX */
    public static final String ADMIN_TERM_PREFIX = ADMIN_AUTH + "/term";

    /** 약관 API 경로 PREFIX */
    public static final String TERM_PREFIX = "/api/term";

    /** 관리자 유튜브 API 경로 PREFIX */
    public static final String ADMIN_YOUTUBE_PREFIX = ADMIN_AUTH + "/youtube";

    /** 유튜브 API 경로 PREFIX */
    public static final String YOUTUBE_PREFIX = "/api/youtube";
}
