Ext.define('app.menu.MenuEditorWindow',{
	extend: 'Ext.window.Window',
	alias: 'widget.menuEditorWindow',
	requires:["app.common.IconCombobox","app.common.MenuCombobox"],
	
	autoShow:true,
	modal:true,
	layout:"fit",
	buttonAlign:'center',
	buttons: [{
		text: '保存'
	}],
	items:[{
		margin:10,
		xtype:"form",
		border:false,
		items:[{
			layout:'column',
			border:false,
			items:[{
				columnWidth: 0.25,
				border:false,
				items:[{
					xtype:'textfield',
					labelAlign:'right',
					fieldLabel: '菜单名称',
					width:'100%'
				}]
			},{
				columnWidth: 0.25,
				border:false,
				items:[{
					xtype:'menuCombobox',
					labelAlign:'right',
					fieldLabel: '上级菜单',
					width:'100%'
				}]
			},{
				columnWidth: 0.25,
				border:false,
				items:[{
					xtype:'iconCombobox',
					labelAlign:'right',
					fieldLabel: '图标',
					width:'100%'
				}]
			},{
				columnWidth: 0.25,
				border:false,
				items:[{
					xtype:'textfield',
					labelAlign:'right',
					fieldLabel: '排序',
					width:'100%'
				}]
			}]
		},{
			xtype:'textfield',
			labelAlign:'right',
			fieldLabel: '地址',
			width:'100%'
		}]
	}]
});
