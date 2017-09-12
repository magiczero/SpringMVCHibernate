<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<script>
	var olddata = {};
</script>
<div class="tabs-vertical-env tabs-vertical-bordered">
	<ul class="nav tabs-vertical">
		<c:forEach items="${items }" var="item" varStatus="status">
			<c:set var="ciId" value="${item.ciId.toString() }" />
			<c:if test="${status.index==0}">
				<li class="active"><a href="#ci-${item.id }" data-toggle="tab">${cis[ciId].name }</a></li>
			</c:if>
			<c:if test="${status.index>0}">
				<li><a href="#ci-${item.id }" data-toggle="tab">${cis[ciId].name }</a></li>
			</c:if>
		</c:forEach>
	</ul>
	<c:if test="${items.size()>0 }">
	<div class="tab-content">
		<c:forEach items="${items }" var="item" varStatus="s">
			<script>
				<c:if test="${not empty item.oldValue}" >
					olddata['${item.ciId}'] = ${item.oldValue};
				</c:if>
			</script>
			<c:if test="${s.index==0}">
				<div class="tab-pane active" id="ci-${item.id}">
			</c:if>
			<c:if test="${s.index>0}">
				<div class="tab-pane" id="ci-${item.id}">
			</c:if>
			<c:set var="propertiesName" value="${item.propertiesName.split(\",\") }"></c:set>
			<c:forEach items="${item.properties.split(\",\") }" var="property" varStatus="status"> 
			<div class="row-form clearfix">
            	<div class="col-md-4">${propertiesName[status.index] } ：</div>
            	<div class="col-md-8">
            		<span class="text-success" id="${item.ciId}_${property}_old"></span>
            	</div>
           </div>
           </c:forEach> 	
		</div>
		</c:forEach>
	</div>
	</c:if>
</div>	

<script>
$(document).ready(function(){
	
	// 显示旧数据
	for(var d in olddata)
	{
		$.each(olddata[d], function(k,v){
			$("#"+d+"_"+k+"_old").html(v);
		});
	}
	
});

</script>