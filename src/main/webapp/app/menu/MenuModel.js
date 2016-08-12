Ext.define('app.menu.MenuModel',{
	extend:'app.view.ViewModel',
	alias:'viewmodel.menuModel',
	stores:{
		menuTreeGridStore:{
			type:"tree",
			root:{
				id:0,
				text:lang("root menu"),
				name:lang("root menu"),
				expanded:true
			},
			sorters:'sort',
			defaultRootProperty:"data",
			fields: [{
				name:'id'
			},{
				name:'parentId'
			},{
				name:'parentName'
			},{
				name:'text'
			},{
				name:'uri'
			},{
				name:'iconCls'
			},{
				name:'sort'
			}],
			proxy: {
				type:'ajax',
				url:'menuAction/getChildrenMenu.action'
			}
		}
	}
});