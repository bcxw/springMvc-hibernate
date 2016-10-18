Ext.define('app.common.MenuCombobox', {
    extend: 'Ext.form.field.Picker',
    alias:'widget.menuCombobox',
	requires:["app.menu.MenuModel"],
	
	viewModel:"menuModel",
	
	targetPanelName:null,//如果是在form里面就是当前form,如果是在grid中就是grid的名称
	displayField:'parentName',//要把grid中的这个值显示在combox,和下拉框的显示无关
	
	editable: false,
    createPicker: function() {
		var me = this,
		picker = new Ext.tree.Panel({
			bind:{store:'{menuTreeGridStore}'},
			floating: true,
			shadow: false,
			maxHeight:300,
			scrollable:"y",
			listeners: {
				itemclick:function(cmp, record, nodeItem, index, e, eOpts ){
					me.setValue(record.data.id);
					me.setRawValue(record.data.text);
					me.fireEvent('select', me, record);
					me.collapse();
				}
			},
			listeners:{
				
			}
		});
        return picker;
    },
	setValue:function(value){
		console.log(value);
	},
	initComponent: function() {
        console.log(this.getViewModel());
		console.log(this.getViewModel().getStore("menuTreeGridStore"));
        this.callParent();
    }
});