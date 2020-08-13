/**
 * @license Copyright (c) 2003-2020, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 * @author minam.cho
 * @since July 23, 2020
 */

CKEDITOR.editorConfig = function( config ) {
	config.toolbar = [
		{ name: 'styles', items: [ 'Styles', 'Font', 'FontSize' ] },
		{ name: 'colors', items: [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', 'TextColor', 'BGColor' ] },
//		{ name: 'clipboard', items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ] },
//		{ name: 'editing', items: [ 'Find', 'Replace', '-', 'SelectAll', '-', 'Scayt' ] },
		{ name: 'basicstyles', items: [ '-', 'RemoveFormat' , 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl' ] },
//		{ name: 'insert', items: [ 'Image', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak', 'Iframe' ] },
		{ name: 'links', items: [ 'Link', 'Unlink', 'Anchor' ] },
//		{ name: 'document', items: [ 'Source', '-', 'Preview', 'Print', '-', 'Templates' ] },
		{ name: 'tools', items: [ 'ShowBlocks', 'Maximize' ] }
		
	],
	config.height = 500;     // 500 pixels height .
	//config.extraPlugins = 'uploadimage';
	//config.uploadUrl = '/ckEditorImageUpload.do?path=ckEditorImages';
	config.disallowedContent = 'script; *[on*]';
	//config.filebrowserImageUploadUrl = '/ckEditorImageUpload.do?path=ckEditorImages';
	config.extraAllowedContent = 'style';
	config.font_names="돋움체;굴림체;바탕체;맑은 고딕;궁서체; sans-serif;" + CKEDITOR.config.font_names;
	config.skin = 'bootstrapck,' + CKEDITOR.basePath+ 'skins/bootstrapck/'; 
};
