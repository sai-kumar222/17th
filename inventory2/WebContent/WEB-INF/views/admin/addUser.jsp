<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
    <style>
        .center {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
    </style>
    <script>
    function showSuccessMessage() {
        var userId = document.getElementById("userId").value;
    	var userName = document.getElementById("userName").value;
        var userPassword= document.getElementById("userPassword").value;
        var userType = document.getElementById("userType").value;
        var status= document.getElementById("status").value;

        var confirmationMessage =
        	"User Id:"+ userId +
            "\User Name: " + userName +
            "\nUser Password: " + userPassword +
            "\nUser Type: " + userType +
            "\nUserStatus: " + status +
            "\n\nAre you sure to add this User?";

        return confirm(confirmationMessage);
    }
</script>
        <div class="center">
        <div class="col-md-6">
            <h2 class="mb-4">User Form</h2>
            <form onsubmit="return showSuccessMessage()" action="saveUser" method="POST">
                <div class="form-group">
                    <label for="userId">User ID:</label>
                    <input type="text" class="form-control" id="userId" name="userId" placeholder="Enter User ID" required>
                </div>
                <div class="form-group">
   					 <label for="password">Password:</label>
    				<input type="password" class="form-control" id="userPassword" name="userPassword" pattern="^(?=.*\d)(?=.*[A-Z])(?=.*\W)(?=.*[a-z]).{8,}$" title="Password must start with a capital letter, contain at least one lowercase letter, one uppercase letter, one special symbol, and one number" required>
				</div>
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" id="userName" name="userName" placeholder="Enter Username" required>
                </div>
                <div class="form-group">
                    <label for="userType">User Type:</label>
                    <select class="form-control" id="userType" name="userType">
                        <option value="procurement">Procurement</option>
                        <option value="inventory">Inventory</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="Status">User Status:</label>
                    <select class="form-control" id="Status" name="status">
                        <option value="active">Active</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        

