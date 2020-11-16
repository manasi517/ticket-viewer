<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
<h3>Opps something went wrong! Please try again or contact Support!</h3>
<div class="dropdown">
  <a>Show Details</a>
  <div class="dropdown-content">
  <p>Message: ${error.errDesc }</p>
  <p>Error Code: ${error.errCode }</p>
  <p>Time: ${error.timestamp }</p>
  </div>
</div>
</div>
