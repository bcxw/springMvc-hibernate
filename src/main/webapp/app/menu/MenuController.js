Ext.define('app.menu.MenuController', {
	extend: 'Ext.app.ViewController',
	alias : 'controller.menuController',
	insertMenu:function(button){
		var tree=button.up("treepanel");
		var rowEditing=tree.getPlugin("menuTreeGridRowediting");
		
		if(!rowEditing.editing){
			var parentNode=tree.getSelection();
			parentNode=parentNode&&parentNode.length>0?parentNode[0]:tree.store.root;
			parentNode.expand(false,function(){
				var sortNumber=parentNode.lastChild&&parentNode.lastChild.data.sort?(parseInt(parentNode.lastChild.data.sort)+10):10;
				sortNumber=parseInt(sortNumber/10)*10;
				var newNode=parentNode.appendChild({parentId:parentNode.data.id,parentName:parentNode.data.text,saved:false,sort:sortNumber,leaf:true});
				parentNode.expand();//没有子节点的新增会展不开的问题
				rowEditing.startEdit(newNode);
			});
		}else{
			Ext.MessageBox.show({
				title:lang('系统提示'),
				msg: lang('请先完成当前编辑的数据'),
				buttons: Ext.MessageBox.OK,
				icon: Ext.MessageBox.WARNING
			});
		}
		
	},
	updateMenu:function(button){
		var tree=button.up("treepanel");
		var nodes=tree.getSelection();
		if(nodes&&nodes.length>0){
			var rowEditing=tree.getPlugin("menuTreeGridRowediting");
			rowEditing.startEdit(nodes[0]);
		}else{
			Ext.MessageBox.show({
				title:lang("System prompt"),
				msg: lang('Please select the data rows you want to edit'),
				buttons: Ext.MessageBox.OK,
				icon: Ext.MessageBox.WARNING
			});
		}
	},
	deleteMenu:function(button){
		var treeRecords=button.up("menuTreeGrid").getSelection();
		if(treeRecords&&treeRecords.length>0){
			var record=treeRecords[0];
			if(record.hasChildNodes()){
				Ext.MessageBox.show({
					title:lang("System prompt"),
					msg: lang('This menu contains the sub menu, can not be deleted'),
					buttons: Ext.MessageBox.OK,
					icon: Ext.MessageBox.WARNING
				});
			}else{
				Ext.MessageBox.confirm(lang("System prompt"),lang("Are you sure you want to delete this data"),function(btn){
					if(btn=="yes"){
						//保存菜单
						Ext.Ajax.request({
							url:'menuAction/deleteMenu.action',
							params:{id:record.data.id},
							success:function(response){
								var result=Ext.decode(response.responseText);
								if(result.success){
									record.remove();
									Ext.toast({html:result.message,align:'b',stickOnClick:false});
								}else{
									Ext.MessageBox.show({
										title:'系统提示',
										msg:result.message,
										buttons: Ext.MessageBox.OK,
										icon: Ext.MessageBox.WARNING
									});
								}
								
							},
							failure:function(response){
								Ext.MessageBox.show({
									title:'系统提示',
									msg:response.responseText,
									buttons: Ext.MessageBox.OK,
									icon: Ext.MessageBox.ERROR
								});
							}
						});
					}
				});
			}
		}else{
			Ext.MessageBox.show({
				title:lang("System prompt"),
				msg:lang("Please select the data row to delete"),
				buttons: Ext.MessageBox.OK,
				icon: Ext.MessageBox.WARNING
			});
		}
	},
	editMenu:function( editor, context, eOpts ){
		//检查parentId不能是自己
		if(context.record.data.id==context.record.data.parentId){
			Ext.MessageBox.show({
				title:lang('系统提示'),
				msg:lang("不能把自己设置成自己的父菜单"),
				buttons: Ext.MessageBox.OK,
				icon: Ext.MessageBox.WARNING
			});
			context.grid.getPlugin("menuTreeGridRowediting").startEdit(context.record);
			return false;
		}
		
		
		//将父菜单放入菜单数据中
		var parentColumn;
		Ext.Array.each(context.grid.columns,function(column, index, countriesItSelf){
			if(column.dataIndex=="parentId"){
				parentColumn=column;
				return false;
			}
		});
		var menuParentEditor=editor.getEditingContext( context.record, parentColumn );
		var parentComboboxRecord=menuParentEditor.value==0?menuParentEditor.store.root:menuParentEditor.store.findRecord("id",menuParentEditor.value);
		context.record.set("parentName",parentComboboxRecord.data.text);
		context.record.set("parentId",parentComboboxRecord.data.id);
		
		
		//保存菜单
		Ext.Ajax.request({
			url:'menuAction/saveMenu.action',
			params:Ext.apply(context.record.data,{id:(context.record.data.saved==false?"":context.record.data.id)}),
			success:function(response){
				var result=Ext.decode(response.responseText);
				if(result.success){
					
					//替换成保存后的节点，直接setid不好用
					var parentNode=context.record.parentNode;
					var newNodeData=context.record.data;
					context.record.remove();
					var newNode=parentNode.appendChild(Ext.apply(newNodeData,{id:result.data.id,saved:true}));
					newNode.commit();
					
					context.grid.selModel.select(parentNode);
					
					
					
					//如果修改了父菜单就将此菜单移位
					if(context.record.parentNode.id!=context.record.parentId){
						var newRecord=context.record;
						var newParentRecord=context.record.data.parentId==0?context.grid.store.root:context.grid.store.findRecord("id",context.record.data.parentId);
						if(newParentRecord&&newParentRecord.hasChildNodes()){
							newParentRecord.appendChild(newRecord,false,true);
						}else{
							context.record.parentNode.removeChild(context.record);
						}
						
					}
					

					
					Ext.toast({html:result.message,align:'b',stickOnClick:false});
				}else{
					Ext.MessageBox.show({
						title:lang('System prompt'),
						msg:result.message,
						buttons: Ext.MessageBox.OK,
						icon: Ext.MessageBox.WARNING
					});
					context.grid.getPlugin("menuTreeGridRowediting").startEdit(context.record);
				}
				
			},
			failure:function(response){
				Ext.MessageBox.show({
					title:lang('System prompt'),
					msg:response.responseText,
					buttons: Ext.MessageBox.OK,
					icon: Ext.MessageBox.ERROR
				});
			}
		});
	},
	canceledit:function( editor, context, eOpts ){
		if(context.record.data.saved==false){
			context.record.remove();
		}
	},
	beforeedit:function( editor, context, eOpts ){
		if(editor.editing){
			Ext.MessageBox.show({
				title:lang('系统提示'),
				msg:lang('请先完成当前正在编辑的数据'),
				buttons: Ext.MessageBox.OK,
				icon: Ext.MessageBox.WARNING
			});
			return false;
		}
	}
});