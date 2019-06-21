<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 表单验证 jQuery Validation</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>库存添加</h5>
                    </div>
                    <div class="ibox-content">
                        <p>库存添加，请做好记录</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>编辑</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/admin/resource/editnewinput">
                        	<input type="hidden" id="id" name="id" value="${addedinputResource.id}">

                            <div class="form-group">
                                <label class="col-sm-3 control-label">库存名称：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" class="form-control" type="text" value="${addedinputResource.name}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">单价：</label>
                                <div class="col-sm-8">
                                    <input id="price" name="price" class="form-control" type="text" value="${addedinputResource.price}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">库存类型：</label>
                                <div class="col-sm-8">
                                	<select name="type" class="form-control">
                                		<option value="0" <#if resource.type == 0>selected="selected"</#if>>西药品</option>
                                		<option value="1" <#if resource.type == 1>selected="selected"</#if>>医疗机械</option>
                                		<option value="2" <#if resource.type == 2>selected="selected"</#if>>中药品</option>
                                	</select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">本次入库数量：</label>
                                <div class="col-sm-8">
                                    <input id="num" name="num" class="form-control" value="${addedinputResource.num}" placeholder="填入数字">
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-3 control-label">状态：</label>
                                <div class="col-sm-8">
                                	<select name="isHide" class="form-control">
                                		<option value="0" <#if resource.locked == 0>selected="selected"</#if>>待审核</option>
                                		<option value="1" <#if resource.locked == 1>selected="selected"</#if>>已批准</option>
                                	</select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">描述：</label>
                                <div class="col-sm-8">
                                    <input id="description" name="description" class="form-control" value="${addedinputResource.description}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">提交</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>


    <!-- 全局js -->
    <script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

    <!-- jQuery Validation plugin javascript-->
    <script src="${ctx!}/assets/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/layer/laydate/laydate.js"></script>
    <script type="text/javascript">
    $(document).ready(function () {

	    $("#frm").validate({
    	    rules: {
    	    	name: {
    	        required: true,
    	        minlength: 4,
    	    	maxlength: 20
    	      },
    	      	sourceKey: {
    	        required: true,
    	        minlength: 4,
    	    	maxlength: 40
    	      },
    	      	type: {
    	        required: true
    	      },
    	      	sourceUrl: {
    	        required: true
    	      },
    	      	level: {
    	        required: true,
    	        number:true
    	      },
    	      	sort: {
    	      	number:true,
    	        required: true
    	      },
    	      	icon: {
    	        maxlength: 40
    	      },
    	      	isHide: {
    	        required: true
    	      },
    	      	description: {
    	        required: true,
    	        maxlength: 40
    	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx!}/admin/resource/editnewinput",
   	    		   data: $(form).serialize(),
   	    		   success: function(msg){
	   	    			layer.msg(msg.message, {time: 2000},function(){
	   						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   						parent.layer.close(index); 
	   					});
   	    		   }
   	    		});
            } 
    	});
    });
    </script>

</body>

</html>
