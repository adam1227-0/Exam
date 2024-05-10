<%--学生一覧 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">

	<c:param name="title">

	      得点管理システム

	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">

		<section class="mo-4">

		  <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生管理</h2>

		  <div class="my-2 text-end px-4">

		  		<a href="StudentCreate.action">新規登録</a>

		  </div>

		  <form action="" method="get">

		  		<div class="row border mx-3 mb-3 py-2 align-items-center rounded " id="filter" style="margin-top: 20px;">


		  			<div class="col-2 text-center">

		  				<label class="form-label"for="student-f1-select">科目情報</label>

		  			</div>



		  			<div class="col-2">

		  				<label class="form-label"for="student-f1-select">入学年度</label>

		  				<select class="form-select"id="student-f1-select"name="f1">

		  					<option value="0">-------</option>

		  					<c:forEach var="year" items="${ent_year_set}">

		  						<%--現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>

		  						<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>

		  					</c:forEach>

		  				</select>

		  			</div>


		  			<div class="col-2">

		  				<label class="form-label" for="student-f2-select">クラス</label>

		  				<select class="form-select" id="student-f2-select" name="f2">

		  					<option value="0">--------</option>

		  					<c:forEach var="num" items="${class_num_set}">

		  						<%--現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>

		  						<option value="${num}"<c:if test="${num==f2}">selected</c:if>>${num}</option>

		  					</c:forEach>

		  				</select>

		  			</div>

		  			<div class="col-4">

		  				<label class="form-label" for="student-f2-select">科目</label>

		  				<select class="form-select" id="student-f3-select" name="f3">

		  					<option value="0">--------</option>

		  					<c:forEach var="num" items="<%--&{} --%>">

		  						<%--現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>

		  						<option value="${num}"<c:if test="${num==f3}">selected</c:if>>${num}</option>

		  					</c:forEach>

		  				</select>

		  			</div>




		  			<div class="col-2 text-center">

		  				<button class="btn btn-secondary" id="filter-button">検索</button>

		  			</div>

		  			<div class="mt-2 text-warning">${ errors.get("f1")}</div>
					<div class="container">
		  				<div class="col-12 border-bottom text-center"></div>
		  			</div>



		  			<div class="col-2 text-center">

		  				<label class="form-label">学生情報</label>

		  			</div>



		  			<div class="col-4">
    					<label class="form-label" for="student-f5-input">学生番号</label>
    					<input type="text"  class="form-control " id="student-f5-input"name="f5" placeholder="学生番号を入力してください"<c:if test="${name!=null}">value="${name}"</c:if>>
					</div>

					<div class="col-2 text-center">

		  				<button class="btn btn-secondary" id="filter-button">検索</button>

		  			</div>



		  		</div>

		  </form>

		  <c:choose>

		  		<c:when test="${students.size()>0}">

		  			<div>検索結果:${ students.size()}件</div>

		  			<table class="table table-hover">

		  				<tr>

		  					<th>入学年度</th>
		  					<th>クラス</th>

		  					<th>学生番号</th>

		  					<th>氏名</th>



		  					<th>1回目</th>

		  					<th>2回目</th>

		  					<th></th>

		  				</tr>

		  				<c:forEach var ="student" items="${students}">

		  				<tr>
		  					<td>${student.entYear}</td>
		  					<td>${student.classNum}</td>

		  					<td>${student.no}</td>

		  					<td>${student.name}</td>


		  					<td>
		  						<%--在学フラグが立っている場合「〇」それ以外は「✖」を表示 --%>
		  						<c:choose>
		  							<c:when test="${student.isAttend()}">
		  							 	〇
		  							</c:when>
		  							<c:otherwise>
		  								✖
		  							</c:otherwise>
		  						</c:choose>
		  				    </td>


		  				   <td>
		  						<%--在学フラグが立っている場合「〇」それ以外は「✖」を表示 --%>
		  						<c:choose>
		  							<c:when test="${student.isAttend()}">
		  							 	〇
		  							</c:when>
		  							<c:otherwise>
		  								✖
		  							</c:otherwise>
		  						</c:choose>
		  				    </td>



		  				</tr>

		  				</c:forEach>

		  			</table>

		  		</c:when>

		  		<c:otherwise>

		  			<div>学生情報が存在しませんでした</div>

		  		</c:otherwise>

		  </c:choose>

		</section>


	</c:param>

</c:import>