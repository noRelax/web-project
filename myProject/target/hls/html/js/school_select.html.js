/**
 * Created by wusong on 2015/5/20.
 */
//ҳ����س�ʼ��?
function init() {
    //��Ӱ����ƶ�ʱ��?
    $("#goalSchool").keyup(function () {
//����б�?
        $("#goalSchoolList").empty();
        $("#jsplan").jScrollPane(function(){
            autoReinitialise:true
        });
        var top = $("#goalSchool").offset().top;
        var left = $("#goalSchool").offset().left;
        $("#jsplan").show();
        $("#jsplan").css({"left":left+"px","top":top+"px"});
        var goalSchoolName=$("#goalSchool").val();
        $.ajax({
            url:'../hfjy/loaderSchool',
            dataType:'json',
            type:'post',
            data:{
            	'type':'1',
            	'schoolName':goalSchoolName
            },
            async:false,
            success:function(resource){
            	var $data=resource.data;
            	alert($data);
            }
        });
    });
}
