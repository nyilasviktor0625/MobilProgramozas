<?php

include 'DatabaseConfig.php' ;
 
 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 
 $name = $_POST['name'];
 $prod_cat = $_POST['prod_cat'];
 $weight = $_POST['weight'];
 $VAT = $_POST['VAT'];
 $gross_price = $_POST['gross_price'];
 $net_price = $_POST['net_price'];
 $customs_regime = $_POST['customs_regime'];
 $purchasing_date = $_POST['purchasing_date'];
 $barcode = $_POST['barcode'];
 $qty = $_POST['qty'];

 $Sql_Query = "insert into zz_inventory (Product_name,Product_category,Weight,VAT,Gross_Price,Net_Price,customs_regime,Integrated_Price,Selling_Price,Expire_Date,Purchasing_date,Bar_code,Quantity) values ('$name','$prod_cat','$weight','$VAT','$gross_price','$net_price','$customs_regime','n\a','$purchasing_date','$barcode','$qty')";
 
 if(mysqli_query($con,$Sql_Query)){
 
 echo 'Data Submit Successfully';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($con);
?>