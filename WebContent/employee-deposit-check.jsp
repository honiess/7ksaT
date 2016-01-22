<!--Author: Xiangfei Dong-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Deposit Check</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
  <!-- nav bar-->
  <jsp:include page="customer-header.jsp" />

  <div class="container-fluid">
    <div class="row-fluid">
      <!--side bar-->
      <div class="col-sm-3">
        <jsp:include page="employee-manage-customers-sidebar.jsp" />
      </div>

      <!--content-->
      <div class="col-sm-9">
        <!--current path-->
        <div>
          <ul class="breadcrumb">
            <li><a href="#"> <i class="icon-home"></i>Home</a></li>
            <li class="active">Deposit Check</li>
          </ul>
        </div>

        <!--error panel-->
        <c:if test="${not empty errors}">
          <div>
            <div class="panel panel-danger">
              <div class="panel-heading">
                <h3 class="panel-title">Warning!</h3>
              </div>
              <div class="panel-body">
                <p>${errors}</p>
                <a href="#">Return</a>
              </div>
            </div>
          </div>
        </c:if>

        <!--success panel-->
        <c:if test="${not empty success}">
          <div>
            <div class="panel panel-success">
              <div class="panel-heading">
                <h3 class="panel-title">Success!</h3>
              </div>
              <div class="panel-body">
                <p>${success}</p>
                <a href="#">Return</a>
              </div>
            </div>
          </div>
        </c:if>

        <!--deposit check-->
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">Deposit Check</h3>
          </div>
          <div class="panel-body">
            <h5 class="text-info">Balance: $ 3297.76<br><br></h5>
            <form class="form-inline" role="form" method="post" action="employee_deposit_check.do">
              <label for="balance">Customer ID</label>
              <input type="text" class="form-control" name="id" />
              <label for="balance">Amount $</label>
              <input type="text" class="form-control" name="amount" />
              <button type="submit" class="btn btn-default">Submit</button>
            </form>
          </div>
        </div>

        <!--user list-->
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">Customer List</h3>
          </div>
          <div class="panel-body">
            <table class="table">
              <thead>
                <tr>
                  <th>ID</th><th>Username</th><th>Name</th><th></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1</td><td>cmudxf</td><td>Xiangfei Dong</td><td><a href="#">Deposit</a></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div> 
      </div>
    </div>
  </div>

  <!--footer-->
  <jsp:include page="footer.jsp" />

</body>
</html>