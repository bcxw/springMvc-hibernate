Ext.define('app.menu.MenuTreeGrid',{
	extend: 'Ext.tree.Panel',
	alias: 'widget.menuTreeGrid',
	requires:["app.menu.MenuModel","app.menu.MenuController"],
	
	viewModel:"menuModel",
	controller:'menuController',
	bind:{store:'{menuTreeGridStore}'},
	name:"menuTreeGrid",
	closable:true,
	rootVisible:false,
	columnLines:true,
	allowDeselect:true,
	
	tbar:[{
		text:"新增",
		icon:'images/icon/add.png',
		handler:"insertMenu"
	},{
		text:"修改",
		icon:'images/icon/update.png',
		handler:"updateMenu"
	},{
		text:"删除",
		icon:'images/icon/delete.png',
		handler:"deleteMenu"
	}],
	columns: [{
		xtype: 'treecolumn',
		text:'菜单名称',
		sortable:false,
		dataIndex: 'text',
		width:200
	},{
		text:'上级菜单',
		width:150,
		dataIndex: 'parentId',
		sortable: false,
		renderer:function(value,metaData,record){
			return record.data.parentName
		}
	},{
		text:'图标',
		width:200,
		dataIndex: 'icon',
		sortable: false
	},{
		text:'地址',
		width:250,
		sortable: false,
		dataIndex: 'uri'
	},{
		text: '排序',
		width:80,
		sortable: false,
		dataIndex: 'sort'
	}]
});