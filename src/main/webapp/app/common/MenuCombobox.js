Ext.require(['extjs.packages.ux.classic.src.TreePicker']);
Ext.define('app.common.MenuCombobox',{
	extend: 'Ext.ux.TreePicker',
	alias: ['widget.menuCombobox'],
	
	//height设置程差不多，因为让下拉框的滚动条变得可用，否则下拉框滚动条不显示。
	minPickerHeight:249,
	maxPickerHeight:251,
	displayField: 'text',
	autoScroll:true,
	store:Ext.create("Ext.data.TreeStore",{
		root:{
			id:0,
			text:lang("root menu"),
			name:lang("root menu"),
			expanded:true
		},
		defaultRootProperty:"data",
		fields: [{
			name:'id'
		},{
			name:'text'
		}],
		proxy: {
			type:'ajax',
			url:'menuAction/getChildrenMenu.action'
		}	
	})
});