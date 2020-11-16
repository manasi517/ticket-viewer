<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<%@ include file="common/pagination.jspf"%>

<div class="container">
	<c:if test="${null==ticket_list || ticket_list.size()==0}">
		<h4 style="color: red">*You don't have any ticket</h4>
	</c:if>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>Ticket List</h3>
		</div>
		<div class="panel-body">
			<div class="pager">
				<table class="table table-striped">
					<thead>
						<tr>
							<th width="25%">ID</th>
							<th width="25%">SUBJECT</th>
							<th width="25%">STATUS</th>
							<th width="25%">PRIORITY</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ticket_list}" var="todo">
							<tr>
								<td>${todo.id}</td>
								<td>${todo.subject}</td>
								<td>${todo.status}</td>
								<td>${todo.priority}</td>
								<td><a type="button" class="btn btn-success"
									href="/ticketDetails?id=${todo.id}&url=${todo.url}">Details</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<c:if test="${!isPrevAllowedLocally && prevPageAvailable}">
					<a type="button" class="btn btn-success previous"
						href="/tickets?page=${pageid-1}&url=${session.getAttribute('wrapper').previous_page}">Previous</a>
				</c:if>
				<c:if test="${isPrevAllowedLocally && prevPageAvailable}">
					<a type="button" class="btn btn-success previous"
						href="ticket-list.jsp?page=${pageid-1}">Previous</a>
				</c:if>
				<c:if test="${isLastPage && nextPageAvailable}">
					<a type="button" class="btn btn-success next"
						href="/tickets?page=${pageid+1}&url=${session.getAttribute('wrapper').next_page}">Next</a>
				</c:if>
				<c:if test="${!isLastPage && nextPageAvailable}">
					<a type="button" class="btn btn-success next"
						href="ticket-list.jsp?page=${pageid+1}">Next</a>
				</c:if>
			</div>
		</div>
	</div>
</div>
