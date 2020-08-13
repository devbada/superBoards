package kr.or.devbada.freeBoards.entity;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 데이터 테이블 (data tables) 관련 파라미터
 * @author minam.cho
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DatatablesParams {
	private int page; // - Current page index (zero based - i.e. the first page is 0)
	private int pages; // - Total number of pages
	private int end; // - Display index for the last record shown on the current page
	private int recordsDisplay; // - Data set length once the current search criteria has been applied.
	
	private int recordsTotal;		// 게시글의 전체 수
	private int recordsFiltered;	// 데이터 테이블 내에서 검색했을 경우 필터 된 게시글의 수
	
	private int draw; 
	private int start = 1;
	private int length;

	private Map<SearchCriterias, String> search;

	private List<Map<ColumnCriterias, String>> columns;

	private List<Map<OrderCriterias, String>> order;

	public enum SearchCriterias {
		value, regex
	}

	public enum OrderCriterias {
		column, dir
	}

	public enum ColumnCriterias {
		data, name, searchable, orderable, searchValue, searchRegex
	}
}
