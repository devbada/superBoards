package kr.or.devbada.freeBoards.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 사용자 result 클래스
 * 
 * @author minam.cho
 * @since August 03, 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AnonyBoardDtlRVO extends AnonyBoardDtlDVO {
    private String name;     // 작성자 실명
    private String nickName; // 작성자 별명
}
