<%-- 科目一覧 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
	      得点管理システム
	</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="mo-4">
		  <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>

		  			<table class="table table-hover">
		  				<tr>
		  					<th>入学年度</th>
		  					<th>学生番号</th>
		  					<th>氏名</th>
		  					<th>クラス</th>
		  					<th>1回</th>
		  					<th>2回</th>
		  					<th></th>
		  				</tr>
		  				<c:forEach var ="subject" items="${subjects}">
		  				<tr>
		  					<td>${student.entYear}</td>
		  					<td>${test.student_No}</td>
		  					<td>${student.name}</td>
		  					<td>${test.student_No}</td>

		  					<td class="test_no">
		  						<%--点数が入っていない場合は - を表示--%>
		  						<c:choose>
		  							<c:when test="${test.no()}">
		  							 	${test.no}
		  							</c:when>
		  							<c:otherwise>
		  								-
		  							</c:otherwise>
		  						</c:choose>
		  				    </td>
		  				</tr>
		  				</c:forEach>
		  			</table>
		</section>
	</c:param>
</c:import>