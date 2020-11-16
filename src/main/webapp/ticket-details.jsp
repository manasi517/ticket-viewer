<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>Ticket Details</h3>
		</div>
		<div class="panel-body">
			<div class="row" style="background-color: #f9f9f9">
				<div class="col-xs-3">
					<h4>ID</h4>
					<span>${ticket.id}</span>
				</div>
				<div class="col-xs-3">
					<h4>STATUS</h4>
					<span>${ticket.status}</span>
				</div>
				<div class="col-xs-3">
					<h4>SUBJECT</h4>
					<span>${ticket.subject}</span>
				</div>
				<div class="col-xs-3">
					<h4>TYPE</h4>
					<span>${ticket.type}</span>
				</div>
			</div>
			
			<div class="row" style="background-color: #f9f9f9">
				<div class="col-xs-3">
					<h4>PRIORITY</h4>
					<span>${ticket.priority}</span>
				</div>
				<div class="col-xs-3">
					<h4>CREATED ON</h4>
					<span>${ticket.created_at}</span>
				</div>
				<div class="col-xs-3">
					<h4>UPDATED ON</h4>
					<span>${ticket.updated_at}</span>
				</div>
				<div class="col-xs-3">
					<h4>REQUESTOR ID</h4>
					<span>${ticket.requester_id}</span>
				</div>
			</div>
			
			<div class="row" style="background-color: #f9f9f9">
				<div class="col-xs-10">
					<h4>DESCRIPTION</h4>
					<p>${ticket.description}</p>
				</div>
			</div>
		</div>
	</div>

</div>
