Ext.define('app.view.Viewport', {
    extend: 'Ext.container.Viewport',
	
	requires:["app.view.ViewModel","app.view.ViewController","app.main.MainPanel"],
	viewModel:{type:"viewModel",url:"configAction/getConfig.action"},
	controller:'viewController',
	
	layout: 'border',
    items: [{
        region: 'north',
        height:80,
		layout:"hbox",
		border:false,
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
		useArrows:true,
		store:{
			autoLoad:true,
			type:"tree",
			sorters:'sort',
			defaultRootProperty:"data",//按照data属性读取节点，默认是children
			fields: [{
				name:'id'
			},{
				name:'parentId'
			},{
				name:'parentName'
			},{
				name:'text'
			},{
				name:'uri'
			},{
				name:'icon'
			},{
				name:'sort'
			}],
			proxy: {
				type:'ajax',
				url:'menuAction/getMenuTree.action'
			}
		},
		//bind:{store:'{menuTreeStore}'},//以后要修改程自己的store，要有权限的
		rootVisible:false,
		root: {
			id:"root",
			text:lang("root menu"),
			name:lang("root menu"),
			expanded: true
		},
		listeners:{
			itemexpand:function( node, eOpts ){
				if(node.id=="root"&&node.firstChild)node.firstChild.expand();
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