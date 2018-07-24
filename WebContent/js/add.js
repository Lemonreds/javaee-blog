function sort_click(btn){
 	//获取点击按钮的内容
 	var value = btn.value;
 	//获取分类输入框	
 	var sort = document.getElementById("sort");
 	//设置分类值
 	sort.value=value;

 }
 function tags_click(btn){	
	//获取点击按钮的内容
	var value = btn.value;
	//获取标签输入框	
	var tags = document.getElementById("tags");
	//获取标签已有的值	
	var tags_value = tags.value;
	//判断是否已经包含
	if(tags_value.indexOf(value) > -1)return ;
	//新增新的值
	tags.value = tags_value+" "+value;
 }
