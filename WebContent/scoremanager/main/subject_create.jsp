<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
	      得点管理システム
	</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="mo-4">
		  <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
		  <form action="SubjectCreateExecute.action" method="get">


		  			<div class="col-11">
		  				<label class="form-label"for="subject-f1-select">科目コード</label>
		  				<input type="text"  class="form-control" id="subject-f1-input"name="f1" placeholder="科目コードを入力してください"<c:if test="${no!=null}">value="${no}"</c:if>>
		  			</div>
		  			<c:if test="${errors.get('f1')!=null}"><div class="col-16"><font color="FFD500">${errors.get("f1")}</font></div></c:if>


		  			<div class="col-11">
    					<label class="form-label" for="subject-f2-input">科目名</label>
    					<input type="text"  class="form-control" id="subject-f2-input"name="f2" placeholder="科目名を入力してください"<c:if test="${no!=null}">value="${no}"</c:if>>
					</div>
					<c:if test="${errors.get('f2')!=null}"><div class="col-16"><font color="FFD500">${errors.get("f2")}</font></div></c:if>

		  			<div class="col-2 text-left">
		  				<button class="btn btn-secondary" id="filter-button">登録</button>
		  			</div>
		  			<a class="nav-item px-2" href="Menu.action">戻る</a>
		  </form>
		</section>
	</c:param>
</c:import>