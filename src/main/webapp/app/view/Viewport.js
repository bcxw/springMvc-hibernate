Ext.define('app.view.Viewport', {
    extend: 'Ext.container.Viewport',
	
	requires:["app.view.ViewModel","app.menu.MenuModel","app.view.ViewController","app.main.MainPanel"],
	viewModel:{type:"menuModel",url:"configAction/getConfig.action"},
	controller:'viewController',
	
	layout: 'border',
    items: [{
        region: 'north',
        height:80,
		layout:"hbox",
		bodyStyle:{"background":"url(images/main/banner.jpg)","background-size":"100% 100%"},
		defaults:{
			border:false,
			height:80,
			bodyStyle:{"background":"none"},
			style:{"background":"none"}
		},
		items: [{
			xtype:"image",
			src:'images/main/logo.png'
		},{
			xtype:'tbspacer',
			flex:1
		},{
			xtype:'toolbar',
			defaults:{
				scale: 'medium',
				height:31,
				style:{"background":"#FFF"}
			},
			items:[{
				tooltip:lang("Current user"),
				icon:"images/icon/user.png",
				bind: {text:"{userName}"},
			},{
				tooltip:lang("Log off"),
				icon:"images/icon/off.png",
				handler:"logoff"
			}]
		}]
    },{
        region: 'center',
		xtype:"tabpanel",
		items:[{xtype:"mainPanel"}]
    },{
        region: 'west',
		xtype:"treepanel",
		title:lang("Menu navigation"),
		icon:"images/icon/menu_white.png",
        width:200,
		collapsible:true,
        split:true,
		rootVisible:false,
		useArrows:true,
		bind:{store:'{menuTreeGridStore}'},
		listeners:{
			itemexpand:function( node, eOpts ){
				if(node.id==0&&node.firstChild)node.firstChild.expand();
			},
			itemclick:function( cmp, record, node, index, e, eOpts ){
				var uri=record.data.uri;
				if(uri&&uri.indexOf("app.")==0){
					var tabPanel=cmp.up("viewport").down("tabpanel");
					var panel=tabPanel.down("[uri='"+uri+"']");
					if(!panel){
						panel=Ext.create(uri,{title:record.data.text,icon:record.data.icon,uri:uri});
						tabPanel.add(panel);
					}
					tabPanel.setActiveTab(panel);
				}
				 
			}
		}
    }]
});