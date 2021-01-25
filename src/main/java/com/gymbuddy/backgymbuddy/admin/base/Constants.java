package com.gymbuddy.backgymbuddy.admin.base;

/**
 * API(ajax call) 관련 상수 Class
 */
public final class Constants {

    /** 관리자만 접근 가능한 API 경로 PREFIX */
    public static final String ADMIN_AUTH = "/api/admin";

    /** 회원만 접근 가능한 API 경로 PREFIX */
    public static final String USER_AUTH = "/api/user";

    /** 메인 배너 API 경로 PREFIX */
    public static final String BANNER_PREFIX = "/api/banner";

    /** BI API 경로 PREFIX */
    public static final String BI_PREFIX = "/api/businessIdentity";

    /** 관리자 장바구니 API 경로 PREFIX */
    public static final String ADMIN_CART_PREFIX = ADMIN_AUTH + "/cart";

    /** 회원 장바구니 API 경로 PREFIX */
    public static final String USER_CART_PREFIX = USER_AUTH + "/cart";

    /** 컬럼 API 경로 PREFIX */
    public static final String COLUMN_PREFIX = "/api/column";

    /** 컬럼 작성자 API 경로 PREFIX */
    public static final String COLUMN_WRITER_PREFIX = "/api/columnWriter";

    /** 자주묻는질문 API 경로 PREFIX */
    public static final String FQ_PREFIX = "/api/frequencyQuestion";

    /** 굿즈 API 경로 PREFIX */
    public static final String GOODS_PREFIX = "/api/goods";

    /** 활동 기록 API 경로 PREFIX */
    public static final String HISTORY_PREFIX = "/api/history";

    /** 멤버 API 경로 PREFIX */
    public static final String MEMBER_PREFIX = "/api/member";

    /** 미션과 비전 API 경로 PREFIX */
    public static final String MISSION_PREFIX = "/api/mission";

    /** 대외뉴스 API 경로 PREFIX */
    public static final String NEWS_PREFIX = "/api/news";

    /** 뉴스레터 API 경로 PREFIX */
    public static final String NEWSLETTER_PREFIX = "/api/newsLetter";

    /** 공지사항 API 경로 PREFIX */
    public static final String NOTICE_PREFIX = "/api/notice";

    /** 주문 API 경로 PREFIX */
    public static final String ORDER_PREFIX = "/api/order";

    /** 프로그램 API 경로 PREFIX */
    public static final String PROGRAM_PREFIX = "/api/program";

    /** 관리자 1:1 문의 API 경로 PREFIX */
    public static final String ADMIN_QUESTION_PREFIX = ADMIN_AUTH + "/question";

    /** 회원 1:1 문의 API 경로 PREFIX */
    public static final String USER_QUESTION_PREFIX = USER_AUTH + "/question";

    /** 후기 API 경로 PREFIX */
    public static final String REVIEW_PREFIX = "/api/review";

    /** 약관 API 경로 PREFIX */
    public static final String TERM_PREFIX = "/api/term";

    /** 유튜브 API 경로 PREFIX */
    public static final String YOUTUBE_PREFIX = "/api/youtube";
}
