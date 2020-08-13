package kr.or.devbada.freeBoards.domain;

import java.util.Date;

import kr.or.devbada.freeBoards.entity.DatatablesParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 게시글 상세 Domain Class
 * 
 * @author minam.cho
 * @since August 04, 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AnonyBoardDtlDVO extends DatatablesParams {
	private String bdId; // 게시물 일련번호
	private String bmId; // 게시판 일련번호
	private String parentBdId; // 상위 게시물 일련번호
	private String title; // 게시물제목
	private String regId; // 작성자 아이디
	private String grp; // 게시물 그룹
	private String refDepth; // 게시물 Depth
	private String noticeYn; // 공지설정
	private int agreeCnt; // 찬성수
	private int oppositeCnt; // 반대수
	private int readCnt; // 조회수
	private String thumbnailImg; // 썸네일 이미지
	private String content; // 내용
	private Date regDt; // 등록일시
	private Date updDt; // 수정일시
	private String regIp; // 등록자 IP
	private String delYn; // 삭제여부
	private Date delDt; // 삭제일시
	private String mainContent; // 내용
	private String mainYn; // 메인노출여부
	private String useYn; // 게시여부
	private int isId; // 후원기업 일련번호
	private String titleHead; // 말머리
	private String locationCd; // 지역코드
	private String attchFile; // 첨부파일
	private String attchFileName; // 첨부파일명
	private String agreeState; // 후원 프로그램 연동 여부
	private String password; // 비회원 게시물을 위한 게시물 비밀번호
	private String replyContent; // 답변 내용
	private String refNo; // 게시물 참조 번호
	private String guestEmail; // 비로그인 작성자 이메일
	private String guestTel; // 비로그인 작성자 전화번호
	private String email; // 작성자 이메일
	private String tel; // 작성자 전화번호
	private String memorialDate; // 기념일자
	private String memorialCd; // 기념일종류
	private String foreignFlag; // 해외 구분
	private String thumbnailImg2; // 썸네일이미지2
	private String mobDisplayYn; // 모바일 웹 메인노출여부
	private String holdDate; // 개최일자
	private String frontYn; // 사용자 입력여부
	private String summary; // 요약
	private String tags; // 태그
	private String startDate; // 시작일자
	private String endDate; // 종료일자
	private int likeCnt; // 좋아요
	private String thumbnailImgAlt; // 썸네일 이미지 대체텍스트
	private String showRegDate; // 등록일자
	private String noticeYear; // 연도(공시년도, 등의 4자리 사용할만한 연도 사용자 입력)

	private String updId; // 수정자
	private String updIp; // 수정 IP
}
