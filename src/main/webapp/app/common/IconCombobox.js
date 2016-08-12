Ext.define('app.common.IconCombobox', {
    extend: 'Ext.form.field.Picker',
    alias:'widget.iconCombobox',
    
    createPicker: function() {
        var me = this,
		picker = new Ext.panel.Panel({
			items:[{
				xtype:"image",
				src:"images/icon/add.png"
			}]
		});
        return picker;
    },

});

