Ext.define('app.common.MenuCombobox', {
    extend: 'Ext.ux.TreePicker',
    alias:'widget.menuCombobox',
	requires:["app.menu.MenuModel"],
	
	viewModel:"menuModel",
	
	bind:{store:'{menuTreeGridStore}'}
});