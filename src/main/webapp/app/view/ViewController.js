Ext.define('app.view.ViewController', {
	extend: 'Ext.app.ViewController',
	alias : 'controller.viewController',
	logoff:function(){
		Ext.MessageBox.confirm('系统提示', '您确定要注销登录吗？',function(btn){
			if(btn=="yes"){
				var csrf=Ext.query("meta[name='csrf']")[0];
				var parameterName=csrf.attributes['parameterName'].value;
				var token=csrf.attributes['token'].value;
				Ext.create({
					xtype:"form",
					standardSubmit:true,
					url:"logout",
					items:[{
						xtype:"hidden",
						name:parameterName,
						value:token
					}]
				}).submit();
			}

		});
	}
	
});