Ext.define('app.view.ViewController', {
	extend: 'Ext.app.ViewController',
	alias : 'controller.viewController',
	logoff:function(){
		Ext.MessageBox.confirm('系统提示', '您确定要注销登录吗？',function(btn){
			if(btn=="yes"){
				Ext.Ajax.request({
					url:'loginAction/logoff.action'
				});
				window.location.href="login.jsp";
			}

		});
	}
});