Ext.application({
	name : 'app',
	autoCreateViewport: true,
	launch:function(){
		Ext.QuickTips.init();

		/** 默认给所有请求加csrf **/
		var csrf=Ext.query("meta[name='csrf']")[0];
		window.csrf_headerName=csrf.attributes['headerName'].value;
		window.csrf_parameterName=csrf.attributes['parameterName'].value;
		window.csrf_token=csrf.attributes['token'].value;
		
		Ext.override(Ext.data.Connection,{
			createRequest: function(options, requestOptions) {
				/** 添加csrf **/
				options.headers=options.headers?options.headers:{};
				options.headers[csrf_headerName]=csrf_token;
				
				/** ext 6.0 **/
				var me = this,
	            type = options.type || requestOptions.type,
	            request;
	        
		        // If request type is not specified we have to deduce it 
		        if (!type) {
		            type = me.isFormUpload(options) ? 'form' : 'ajax';
		        }
		        
		        // if autoabort is set, cancel the current transactions 
		        if (options.autoAbort || me.getAutoAbort()) {
		            me.abort();
		        }
		        
		        // It is possible for the original options object to be mutated if somebody 
		        // had overridden Connection.setOptions method; it is also possible that such 
		        // override would do a sensible thing and mutate outgoing requestOptions instead. 
		        // So we have to pass *both* to the Request constructor, along with the set 
		        // of defaults potentially set on the Connection instance. 
		        // If it looks ridiculous, that's because it is; things we have to do for 
		        // backward compatibility... 
		        request = Ext.Factory.request({
		            type: type,
		            owner: me,
		            options: options,
		            requestOptions: requestOptions,
		            ownerConfig: me.getConfig()
		        });
		        
		        me.requests[request.id] = request;
		        me.latestId = request.id;
		        
		        return request;
			}
		});
		
	}
});
