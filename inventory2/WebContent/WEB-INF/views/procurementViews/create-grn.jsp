<!DOCTYPE html>
<html>
<head>
  <title>GRN Form</title>
  <style>
    table {
      border-collapse: collapse;
      width: 100%;
    }
    table, th, td {
      border: 1px solid black;
      padding: 5px;
    }
    
    /* Styling for Add button in the purchasedItemsTable */
    #purchasedItemsTable button {
      background-color: #4CAF50;
      color: white;
      border: none;
      padding: 5px 10px;
      border-radius: 3px;
      cursor: pointer;
    }
    .filters{
		display:flex;
		    justify-content: space-evenly;

	}
    /* Styling for Delete button in the grnListTable */
    #grnListTable button {
      background-color: #FF0000;
      color: white;
      border: none;
      padding: 5px 10px;
      border-radius: 3px;
      cursor: pointer;
    }
    
    /* Styling for createGRN button */
    #createGRNButton {
      background-color: #2ECC71;
      color: white;
      border: none;
      padding: 10px 20px;
      border-radius: 5px;
      font-size: 16px;
      cursor: pointer;
      margin: 0 auto;
      display: block;
    }
    
    /* Center align the button container */
    .button-container {
      text-align: center;
    }
    
    /* Center align the table headings */
    th {
      text-align: center;
    }
	#bonusId,#currentReceivedId{
	width:40px;
	}
	#grnListTable{
	margin-left:200px;
	width:900px;
	}
	
	#purchasedItemsTable{
	margin-left:200px;
	width:900px;
	}
  </style>
</head>



<script>

function getTableData() {
	  const table = document.getElementById('grnListTable');

	  const tableData = [];
	  const headerRow = table.rows[0];

	  // Iterate through each row of the table
	  for (let i = 1; i < table.rows.length; i++) {
	    const row = table.rows[i];
	    const rowData = {};

	    // Iterate through each cell of the row excluding the "product_name" column
	    for (let j = 0; j < row.cells.length - 1; j++) {
	      if (j == 0 || j == 1 || j == 5 || j == 6) {
	        // Check if the cell contains an input tag or plain text
	        const cell = row.cells[j];
	        const cellHeader = headerRow.cells[j].id; // Use textContent to get the header

	        if (cell.querySelector("input")) {
	          rowData[cellHeader] = cell.querySelector("input").value; // Get input value
	        } else {
	          rowData[cellHeader] = cell.textContent.trim(); // Get plain text content
	        }
	      }
	    }

	    // Add row data to the tableData array
	    tableData.push(rowData);
	  }
	  const jsonData = {};
	  jsonData["purchaseId"] = document.getElementById("purchaseId").value;
	  jsonData["productsList"] = tableData;

	  return jsonData;
	}

function createGRN()
{
	var data = getTableData();
	const jsonData = JSON.stringify(data);
	console.log(jsonData);
 	$.ajax({
		url:"makeGrn",
		method:"post",
		data:{"jsonData":jsonData},
		success:function(page)
		{
			console.log("Success");
		}
	
	});
 }



</script>






<body>
  <!-- #f2f2f2 -->
  <div style="background-color: #1d952070; padding: 20px; border-radius: 5px; box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);">
    <h2 style="font-size: 24px; margin-bottom: 10px;" align="center">Create GRN</h2><br>
	<div class="filters">
    <div style="width:45%">
	<label for="vendorId" style="font-weight: bold; font-size: 16px; display: block; margin-bottom: 5px;">Vendor ID:</label>
	<select id="vendorId" style="padding: 10px; font-size: 14px; border-radius: 5px; border: 1px solid #ccc; width: 100%; margin-bottom: 10px;">
      <option value="vendor1">Vendor 1</option>
      <option value="vendor2">Vendor 2</option>
      <option value="vendor3">Vendor 3</option>
    </select>
	</div>
	<div style="width:45%">
    <label for="purchaseOrderDate" style="font-weight: bold; font-size: 16px; display: block; margin-bottom: 5px;">Purchase Order Date:</label>
    <input type="date" id="purchaseOrderDate" style="padding: 10px; font-size: 14px; border-radius: 5px; border: 1px solid #ccc; width: 100%; margin-bottom: 10px;">
    </div>
	</div>
	<br>
	<div  style="display:flex;    justify-content: center;">
	<div style="width:45%">
    <label for="purchaseId" style="font-weight: bold; font-size: 16px; display: block; margin-bottom: 5px;">Purchase ID:</label>
    <select id="purchaseId" style="padding: 10px; font-size: 14px; border-radius: 5px; border: 1px solid #ccc; width: 100%; margin-bottom: 10px;">
      <option value="purchase1">Purchase 1</option>
      <option value="purchase2">Purchase 2</option>
      <option value="purchase3">Purchase 3</option>
    </select>
	</div>
	</div>
  </div>

  <br><br>
  <h3 align="center">Purchased Items</h3>
  <table id="purchasedItemsTable">
    <thead>
      <tr>
        <!--<th>S.No</th>-->
        <th>Product ID</th>
        <th>Batch No</th>
        <th>Ordered Quantity</th>
        <th>Received Quantity (till Now)</th>
        <th>Cost</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <!--<td>1</td>-->
        <td>Product 1</td>
        <td>Batch 1</td>
        <td>10</td>
        <td>5</td>
        <td>$50</td>
        <td><button onclick="addToGRNList(this)">Add</button></td>
      </tr>
      <tr>
        <!--<td>2</td>-->
        <td>Product 2</td>
        <td>Batch 2</td>
        <td>20</td>
        <td>15</td>
        <td>$100</td>
        <td><button onclick="addToGRNList(this)">Add</button></td>
      </tr>
    </tbody>
  </table>
  <br><br>
  <h3 align="center">GRN List</h3>
  <table id="grnListTable">
    <thead>
      <tr>
        <!--<th>S.No</th>-->
        <th id="productId">Product ID</th>
        <th id="batchNo">Batch No</th>
        <th>Ordered Quantity</th>
        <th>Received Quantity (till Now)</th>
        <th>Cost</th>
        <th id="currentRecievedQuantity">current Received Quantity </th>
        <th id="bonus">bonus</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
    </tbody>
  </table>
  <br><br>
  <div class="button-container">
    <button id="createGRNButton" onclick="createGRN()">Create GRN</button>
  </div>

  <script>
    function addToGRNList(button) {
      var row = button.parentNode.parentNode;
      var table = document.getElementById("grnListTable");
      var newRow = table.tBodies[0].insertRow(-1);

      for (var i = 0; i < row.cells.length + 2; i++) {
        var newCell = newRow.insertCell(i);
        if (i === row.cells.length + 1) {
          newCell.innerHTML = '<button onclick="deleteFromGRNList(this)">Delete</button>';
        } else if (i === 5 ) {
          newCell.innerHTML = '<input id="currentReceivedId" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">';
		  
        
		} else if (i === 6 ) {
          newCell.innerHTML = '<input id="bonusId" type="number" min="0" value="0" oninput="this.value = this.value.replace(/[^0-9]/g, \'\')">';
		}else {
          newCell.innerHTML = row.cells[i].innerText;
        }
      }
      row.remove();
    }

    function deleteFromGRNList(button) {
      var row = button.parentNode.parentNode;
      var table = document.getElementById("purchasedItemsTable");
      var originalRow = document.createElement("tr");

      // Extract the data from the row being deleted
      var rowData = [];
      for (var i = 0; i < row.cells.length - 3; i++) {
        rowData.push(row.cells[i].innerHTML);
      }

      // Create cells in the original row and populate with the extracted data
      for (var j = 0; j < rowData.length; j++) {
        var newCell = originalRow.insertCell(j);
        newCell.innerHTML = rowData[j];
      }
      var newCell = originalRow.insertCell(rowData.length);
      newCell.innerHTML = '<button onclick="addToGRNList(this)">Add</button>';

      // Append the original row to the purchasedItemsTable
      table.tBodies[0].appendChild(originalRow);

      // Remove the row from the grnListTable
      row.remove();
    }

    
  </script>
</body>
</html>
