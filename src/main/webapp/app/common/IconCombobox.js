Ext.define('app.common.IconCombobox', {
    extend: 'Ext.form.field.Picker',
    alias:'widget.iconCombobox',
	
	store:  {
		autoLoad: true,
		fields: ["name","url"],
		proxy: {
			type:'ajax',
			url:'menuAction/getIcons.action',
			reader: {
				 type: 'json',
				 rootProperty: 'data'
			 }
		}
	},
	
    createPicker: function() {
        var me = this,
		picker = new Ext.panel.Panel({
			floating: true,
			shadow: false,
			maxHeight:300,
			scrollable:"y",
			items:[{
				xtype:"dataview",
				itemSelector: 'div.thumb-wrap',
				overItemCls:'x-grid-item-over',
				store:me.store,
				tpl:[
					'<tpl for=".">',
						'<div style="margin:2px;padding:2px;width:30px;overflow:hidden;text-align:center;float:left;cursor:pointer;" class="thumb-wrap">',
						  '<img src="{url}" />',
						  '<br/><span>{name}</span>',
						'</div>',
					'</tpl>'
				],
                listeners: {
					scope:me,
                    select:function( cmp, record, The, eOpts ){
						me.setValue(record.data.url);
						me.fireEvent('select', me, record);
						me.collapse();
					}
                }
			}]
		});
        return picker;
    }

});





