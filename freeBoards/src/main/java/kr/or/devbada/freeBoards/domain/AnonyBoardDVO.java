package kr.or.devbada.freeBoards.domain;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 게시판 구분 (=마스터) Domain Class
 * 
 * @author minam.cho
 * @since August 04, 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AnonyBoardDVO {
    private String bmId; // 게시판 일련번호
    private String nm; // 게시판 이름
    private String authCd; // 글쓰기권한
    private String listCd; // 리스트 형태
    private String replyYn; // 답글 활성화
    private String commentYn; // 댓글 활성화
    private String regId; // 등록자
    private String updId; // 수정자
    private Date regDt; // 등록일시
    private Date updDt; // 수정일시
    private String topCd; // 게시판 상단
    private String topHtml; // 게시판 상단 HTML
    private String picNm; // 게시판 이미지 제목
    private String boardDesc; // 게시판 설명
    private String attchFileYn; // 첨부파일기능
    private int attchFileSize; // 첨부파일용량
    private String useYn; // 게시판활성 여부
    private String categoryCd; // 카테고리 코드
    private String url; // 게시판URL
    private String agreeYn; // 추천활성화
    private String emailYn; // 이메일필드 활성화
    private String titleImg; // 제목이 이미지인지
    private String supportYn; // 후원 프로그램 연동 여부
    private String template; // 게시판 템플릿
    private String scrapYn; // 게시판 스크랩 사용여부
    private String csmYn; // 평점사용유무
    private String snsYn; // SNS 사용유무
}
