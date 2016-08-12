/**
 * 基础viewModel，其他界面都用这个viewModel
 */
Ext.define('app.view.ViewModel', {
	extend : 'Ext.app.ViewModel',
	alias : 'viewmodel.viewModel',
	constructor: function(config){
		this.callParent(arguments);
		if(this.url){
			Ext.Ajax.request({
				scope:this,
				url:this.url,
				success: function(response){
					var data=Ext.decode(response.responseText).data;
					this.setData(data);
					if(data&&data.systemName){
						document.title=data.systemName;
					}
				}
			});
		}
	}
});