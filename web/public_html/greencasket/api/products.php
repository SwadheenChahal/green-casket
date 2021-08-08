<?
require_once __DIR__ . '../../dbs/myphpcon.php';
require_once __DIR__ . '../../utility/globals.php';

 $for=$_POST['for'];
 if(isset($_POST['text'])) $text=$_POST['text'];

 
 if($for==$for_products)
 $query="SELECT * FROM `products` ORDER BY RAND()";
 else if ($for==$for_search)
 $query="select * from `products` where product_title like '%$text%' order by RAND()";
 
 
 
 if ($result = mysqli_query($dbhandle,$query))
{
$json_response = array();
while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
	$rows['ProductsData'][] = $row;
}
echo json_encode($rows);
}
else
{
   	echo "Error: " . $query . " " . mysqli_error($dbhandle);
}


?>