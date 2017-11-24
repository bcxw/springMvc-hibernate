Ext.define('app.common.MenuCombobox', {
    extend: 'Ext.ux.TreePicker',
    alias:'widget.menuCombobox',
	requires:["app.menu.MenuModel"],
	
	displayField:'text',
	store:Ext.create("Ext.data.TreeStore",{
		autoLoad:true,
		type:"tree",
		sorters:'sort',
		defaultRootProperty:"data",//按照data属性读取节点，默认是children
		root:{
			id:"root",
			text:"顶级菜单",
			name:"顶级菜单"
		},
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
			name:'icon'
		},{
			name:'sort'
		}],
		proxy: {
			type:'ajax',
			url:'menuAction/getMenuTree.action'
		}     
	})
});