/**
 * 게시글 쓰기 스크립트
 * @author minam.cho
 * @since August 09, 2020
 */
var BoardList = BoardList  || (function(){
	var DataAction = {

		/**
		 * 데이터 테이블 검색
		 */
		selectBoardListSearch : function() {
			$("#tableList").DataTable().search($("input[type='search']").val()).draw();
		}
	};
	
	var fn = {
		initEvents: function(){
			
			// TODO 검색 버튼 기능 추가
			$("input[type='search']").on("keydown", function(evt) {
				if (evt.keyCode == 13) {
					DataAction.selectBoardListSearch();
				}
			});
			
			$("#buttonWrite").on("click", function(){
				location.href="/public/boardWrite.do?bmId="+$('#bmId').val();
			});
			
		},

		initDataTable: function() {
			var $tableList = $('#tableList');
			$tableList.DataTable({
				"processing" : true,
				"serverSide" : true,
				"scrollX": true,
				"sDom": 't p',
				"language": {
					"url": "/resources/lib/datatables/i18n/Korean.lang"
				},
				"ajax": {
					"url": "/public/selectBoardListAjax.do",
					"dataSrc": "resultDataList",
					"data": function(data){
						for (var i = 0; i < data.columns.length; i++) {
							column = data.columns[i];
							column.searchRegex = column.search.regex;
							column.searchValue = column.search.value;
							delete(column.search);
						}
						// INFO 데이터 search 파라미터 지정
						var table = $tableList.DataTable();
						var info = table.page.info();
						
						data["page"] = info.page + 1;
						data["bmId"] = $('#bmId').val();
					}
				},
				columns: [
					{ id:"1", data: "bdId" },
					{ id:"2", data: "title", render:function(value,b,c) {
						if ( value ) {
							var replacedLeftBlank = value.split(' ').join('&nbsp;');
							return '<a href="javascript:BoardList.FN.openDetailView('+ c.bdId + ');">' + replacedLeftBlank + '</a>';
						} else {
							return value;
						}
					}},
					{ id:"3", data: "regId", render:function(value,b,rowData) {
						/**
						 * POST_OFFICE	칭찬 우체국
							GREEN_FAMILY	초록 Family
							NOTICE	공지사항
							CURTURAL_PROPOSAL	재단 문화 제안
							HOT_ISSUE	사내 핫 이슈
							COMMENT_COUNSELING	댓글 상담실
							Q_A	Q&A
						 */
						var bmId = $('#bmId').val();
						switch(bmId) {
							case "NOTICE":
							case "POST_OFFICE":
							case "GREEN_FAMILY":
								return rowData.name;

							default : return rowData.nickName;
						}

					}},
					{ id:"4", data: "readCnt" },
					{ id:"5", data: "regDt", render:function(a,b,c,d){
						return moment(a).format('YYYY/MM/DD H:m:s');
					} }
				],
				columnDefs: [{
					targets: "_all",
					orderable: false
				}],
				pageLength: 10,
				pagingType : "full_numbers",
				bPaginate: true,
				bLengthChange: true,
				lengthMenu : [ [ 1, 3, 5, 10, -1 ], [ 1, 3, 5, 10, "All" ] ],
				responsive: true
			});
		},
		
		reset: function() {
			$('#tableList').DataTable().clear().draw(false)
			
		},
		
		/**
		 * 게시글 상세보기 페이지로 이동
		 */
		openDetailView: function(bdId) {
			location.href = '/public/boardDetail.do?bdId=' + bdId +'&bmId=' + $("#bmId").val();
		}
	};
	
	return {
		"FN" : fn, "DataAction": DataAction
	};
	
})();

$(function(){
	$(document).ready(function(){
		BoardList.FN.initEvents();
		BoardList.FN.initDataTable();
	});
});