<!DOCTYPE html>
<html>
<head>
	<title>CDB - Edit Computer</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="utf-8">
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="css/font-awesome.css" rel="stylesheet" media="screen">
	<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                    	<c:choose>
                    		<c:when test="${ not empty invalidComputerIdException }">
                    			<c:out value="ERROR: invalid computer"/>
                    		</c:when>
                    		<c:otherwise>
                    			<c:out value="id: ${ dtoComputerEdit.id }"/>
                    		</c:otherwise>
                    	</c:choose>
                    </div>
                    <h1>Edit Computer</h1>
					<h4><i>Change any field you like</i></h4>
					<h1></h1>
                    <form action="editComputer" method="POST">
                        <input type="hidden" value="${ dtoComputerEdit.id }" id="id" name="id"/> <!-- TODO: Change this value with the computer id -->
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" name="computerName" placeholder="Computer Name" value="${ dtoComputerEdit.name }" required>
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" id="introduced" name="introduced" placeholder="Introduced date" value="${ dtoComputerEdit.introduced }">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date" value="${ dtoComputerEdit.discontinued }">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId">
                                    
                                    <c:choose>
	                                    <c:when test="${not empty dtoComputerEdit.companyName}">
	                                    	<option value="${dtoComputerEdit.companyId}"><c:out value="${dtoComputerEdit.companyName}"/></option>
	                                    </c:when>
	                                    <c:otherwise>
	                                    	<option value="">--</option>
	                                    </c:otherwise>
	                                </c:choose>
                                    
                                    <c:forEach items="${listDTOCompany}" var="dtoCompany">
                                    	<option value="${dtoCompany.id}"><c:out value=" ${dtoCompany.name}" /></option>
                                    </c:forEach>
                                    
                                </select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Edit" class="btn btn-primary">
                            or
                            <a href="dashboard" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    
    <c:out value="${ inputException }" />
</body>
</html>