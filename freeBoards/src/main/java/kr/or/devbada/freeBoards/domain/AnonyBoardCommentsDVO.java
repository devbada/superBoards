package kr.or.devbada.freeBoards.domain;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 게시글 상세 댓글 Domain Class
 * 
 * @author minam.cho
 * @since August 12, 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AnonyBoardCommentsDVO {
	private int id; // 고유키
	private int parent; // 대댓글을 위한 상위키
	private Date created; // 생성일
	private Date modified; // 수정일
	private String content; // 내용
	private String attachments; // 첨부정보 (will not use)
	private String pings; // 댓글언급사용자확인
	private String creator; // 작성자
	private String fullname; // 작성자: 전체이름
	private String profilePictureUrl; // 프로필 이미지 URL
	private String isNew; // 새 게시물 여부
	private String createdByAdmin; // 관리자가 작성여부
	private String createdByCurrentUser; // 사용자가 작성여부
	private int upvoteCount; // 좋아요 수
	private String userHasUpvoted; // 좋아요 참여여부
	
	private String bdId; // 관련게시물 KEY
}
