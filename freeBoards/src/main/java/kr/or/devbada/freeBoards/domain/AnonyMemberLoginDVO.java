package kr.or.devbada.freeBoards.domain;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 로그인 기록 Domain Class
 * @author minam.cho
 * @since August 03, 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AnonyMemberLoginDVO {
    private String loginKey;
    private String memId;
    private String successYn;
    private Date loginDt; 
    private String remoteIp;
}
