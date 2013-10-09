<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container">
	<div class="row">
		<div class="span12">
			<form action="<%=request.getContextPath()%>/vm/add/submit"
				class="form-inline" name="add_vm" method="POST"
				onsubmit="return valid()">
				<fieldset>
					<legend>虚拟机申请</legend>
				</fieldset>

				<label>CPU：</label> <label id="radio-group"><input
					type="radio" name="cpu" value="1" checked><span>1核</span></label> <label
					id="radio-group"><input type="radio" name="cpu" value="2"><span>2核</span></label>
				<label id="radio-group"><input type="radio" name="cpu"
					value="4"><span>4核</span></label> <label id="radio-group"><input
					type="radio" name="cpu" value="8"><span>8核</span></label>
				<hr>

				<label>内存：</label> <label id="radio-group"><input
					type="radio" name="memory" value="1" checked><span>1G</span></label>
				<label id="radio-group"><input type="radio" name="memory"
					value="2"><span>2G</span></label> <label id="radio-group"><input
					type="radio" name="memory" value="4"><span>4G</span></label> <label
					id="radio-group"><input type="radio" name="memory"
					value="8"><span>8G</span></label> <label id="radio-group"><input
					type="radio" name="memory" value="16"><span>16G</span></label>
				<hr>

				<label>硬盘：</label> <input name="storage" type="text"
					data-slider="true" data-slider-highlight="true"
					data-slider-range="100,2000" data-slider-step="100" />&nbsp;G
				<hr>

				<label>带宽：</label> <input name="boardWidth" type="text"
					data-slider="true" data-slider-highlight="true"
					data-slider-range="1,100" data-slider-step="1"
					data-slider-snap="true" />&nbsp;Mbps
				<hr>

				<label>操作系统：</label> <label id="radio-group"><input
					type="radio" name="os" value="Ubuntu Server 12.04 LTS" checked><span>Ubuntu
						Server 12.04 LTS</span></label>
				<hr>


				<div class="control-group" id="div_name">
					<label>名称：</label> <input type="text" placeholder="Name" id="name"
						name="name" /> <span class="help-inline" id="help_name"></span>
				</div>
				<button type="submit" class="btn btn-success">提交</button>

			</form>
		</div>
	</div>
</div>

<script type="text/javascript">
	$("[data-slider]").each(function() {
		var input = $(this);
		$("<span>").addClass("output").insertAfter($(this));
	}).bind("slider:ready slider:changed", function(event, data) {
		$(this).nextAll(".output:first").html(data.value);
	});

	function valid() {
		var name = $('#name').val().trim();
		if (name == "") {
			$('#div_name').removeClass('control-group');
			$('#div_name').addClass('control-group error');
			document.getElementById('help_name').innerHTML = "请输入虚拟机名称";
			return false;
		}
		return true;
	}
</script>