<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
	      得点管理システム
	</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="mo-4">
		  <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
		  <form action="StudentUpdateExecute.action" method="get">

		  			<div class="col-2">
					    <label class="form-label">科目コード</label>
					    <input type="text" class="form-control " id="student-f1-select" name="f1" style="border: none;" value="${entYear}" readonly>
					</div>

		  			<div class="col-2">
				        <label class="form-label">学生番号</label>
				        <input type="text" class="form-control" name="f2" value="${no}" readonly style="border: none;">
					</div>

		  			<div class="col-11">
    					<label class="form-label" for="student-f3-input">氏名</label>
    					<input type="text"  class="form-control" id="student-f3-input"name="f3" placeholder="氏名を入力してください"<c:if test="${name!=null}">value="${name}"</c:if>>
					</div>
					<c:if test="${errors.get('f3')!=null}"><div class="col-16"><font color="FFD500">${errors.get("f3")}</font></div></c:if>



		  			<div class="col-11">
		  				<label class="form-label" for="student-f4-select">クラス</label>
		  				<select class="form-select" id="student-f4-select" name="f4">
		  					<option value="0">--------</option>
		  					<c:forEach var="num" items="${class_num_set}">
		  						<%--現在のnumと選択されていたf4が一致していた場合selectedを追記 --%>
		  						<option value="${num}"<c:if test="${num==f4}">selected</c:if>>${num}</option>
		  					</c:forEach>
		  				</select>
		  			</div>
		  			<c:if test="${errors.get('f4')!=null}"><div class="col-16"><font color="FFD500">${errors.get("f4")}</font></div></c:if>

		  				<br>


		  			<div class="col-2 ">
					    <!-- ラベル -->
					    <label class="form-check-label" for="student-f3-check">在学中</label>

					    <!-- チェックボックス -->
					    <input class="form-check-input" type="checkbox" id="student-f3-check" name="f5" value="t" <c:if test="${!empty f5}">checked</c:if>>
					</div>


		  			<br>


		  			<div class="col-2 text-left">
		  				<button class="btn btn-secondary bg-primary">変更</button>
		  			</div>
		  			<a class="nav-item px-2" href="Menu.action">戻る</a>
		  </form>
		</section>
	</c:param>
</c:import>