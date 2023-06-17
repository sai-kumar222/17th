<html>
<head>
	<style>
	.prnClass
	{
	position:relative;
		left:500px;
	 font-size: 18px;
         font-weight: bold;
         color: #333;
         text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
	}
	.prnfilterClass
	{
	position:relative;
	left:1040px;

	}
	.prnfilterClass2
	{
	position:relative;
	left:1020px;
	
	
	}
	.prnfilterClass1
	{
	position:relative;
	left:460px;
	
	}
	</style>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
    
</head>

<body>
    <h1 align="center">PRN Data</h1>
	<br><br>
		<label class="prnClass"></label>
	<input type="date" id="returnDate" class="prnClass">
	
	<label class="prnClass">Vendor Id</label>
	<select id="vendorId" class="prnClass" >
		<option >20001</option>
	</select>

	<label class="prnClass">Grn value</label>
	<select id="prnvalue" class="prnClass" >
		<option>50000</option>
		
	</select>
	<br><br>
	<label class="prnClass">Select PRN ID:</label>
	<select id="prnId" class="prnClass" align="center" >
		
	</select>
	<br>
	


	
	<br><br>
	<label class="prnfilterClass2">Vendor ID</label>
	<input type="text" value="678" id="vid" name="vid" class="prnfilterClass2">
	
	<label class="prnfilterClass1">Return Date</label>
	<input type="text"  id="rdt" name="rdt" class="prnfilterClass1">
	<br>
	<table class="table bg-white rounded shadow-sm  table-hover" id="dataTable">
            <thead>
                <tr>
                    <th scope="col" width="50">#</th>
                    <th scope="col">GRN Id</th>
									<th scope="col">Product Id</th>
									<th scope="col">Batch No</th>
									<th scope="col">Quantity</th>
									<th scope="col">Cost</th>
									<th scope="col">Reason</th>
                                </tr>
                            </thead>
                            <tbody id="dataBody">
                                <tr>
                                    
									
                                </tr>
                            </tbody>
                        </table><br><br>
		<label class="prnfilterClass2">Total Value</label>
	<input type="text" value="678" id="tv" name="tv" class="prnfilterClass2">
</body>
<script>
$(document).ready(function(){
var returnDate=$("#returnDate").val();
var vendorId=$("#vendorId").val();
var prnvalue=$("#prnvalue").val();
$("#returnDate").change(tk);
$("#vendorId").click(tk);
$("#prnvalue").click(tk);
var prnId=$("#prnId");
$("#prnId").click(products);
function tk(){
	var returnDate=$("#returnDate").val();


$.ajax({
	            
                 url: "getPurchaseReturnsList",
                 method:"GET",
                	    data: {
                	    	"purchase_return_date":returnDate,
                	    	"vendor_id":vendorId,
                	    	"grn_value":prnvalue
                	    	
                	    },
                 success: function(response) {
                	 console.log(response);
                	 var PurchasesId= $("#prnId");
                	 response.forEach(function(optionval)
                     		{
                     		  var newop=$("<option></option>");
                     		  newop.val(optionval.purchase_return_id);
                     		  newop.text(optionval.purchase_return_id);
                     		  PurchasesId.append(newop);
                     		  
                     		});
                	 
                    
                 }
             });



};
});
function products()
{
$.ajax({
    
    url: "getPurchaseReturnProducts",
    method:"GET",
   	    data: {
   	    	"purchase_return_id":1001,
   	    	
   	    },
    success: function(response) {
    	console.log(this.url);
    	console.log("hi");
    	console.log(response);
    	var table = document.getElementById("dataTable");
        var tbody = document.getElementById("dataBody");
        $("#dataBody").html("");
       

        // Iterate over the data and create table rows
   
        response.forEach(function (row) {
            // Create a new row
            var newRow = document.createElement("tr");
            var firstCell = document.createElement("td");
            firstCell.textContent=" ";
            // Create and populate cells for each column
            var grnIdCell = document.createElement("td");
            grnIdCell.textContent = row.grnId;

            var productIdCell = document.createElement("td");
            productIdCell.textContent = row.productId;

            var batchNoCell = document.createElement("td");
            batchNoCell.textContent = row.batchNo;

            var quantityCell = document.createElement("td");
            quantityCell.textContent = row.quantity;

            var costCell = document.createElement("td");
            costCell.textContent = row.cost;

            var reasonCell = document.createElement("td");
            reasonCell.textContent = row.reason;
            
            $("#rdt").text("2023-06-14");
            $("#rdt").val("2023-06-14");
            $("#vid").text("20001");
            $("#vid").val("20001");
            $("#prnvalue").text("50000");
            
            $("#prnvalue").val("50000");
            
            // Append cells to the row
            newRow.appendChild(firstCell);
            newRow.appendChild(grnIdCell);
            newRow.appendChild(productIdCell);
            newRow.appendChild(batchNoCell);
            newRow.appendChild(quantityCell);
            newRow.appendChild(costCell);
            newRow.appendChild(reasonCell);

            // Append the row to the table body
            tbody.appendChild(newRow);
        });
       
    }
});
}

</script>


</html>