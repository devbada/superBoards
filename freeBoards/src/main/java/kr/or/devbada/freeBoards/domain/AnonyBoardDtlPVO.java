package kr.or.devbada.freeBoards.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 사용자 파라미터 클래스
 * @author minam.cho
 * @since August 03, 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AnonyBoardDtlPVO extends AnonyBoardDtlDVO {
    private String replyTargetBdId; // 답글 대상 bdId
}
