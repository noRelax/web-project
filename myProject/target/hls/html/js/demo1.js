/**
 * Created by wusong on 2015/5/20.
 */
//js初始化加载

function init(){
	console.log("加载页面初始化");
	contentInit();
}

//页面初始化，加载指定的html文件到指定标签中
function contentInit(){
    $.ajax({
        url:'index.html',
        type:'get',
        dataType:'html',
        async:'false',
        success:function(data){
        $(".page-box").append(data);
        }
    });
}
$(function(){
	init();
})