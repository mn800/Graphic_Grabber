<?php
require_once ('connectvars.php');
$dbc = new mysqli(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);
$params = $_POST;

$list = sprintf("SELECT PId FROM %s WHERE %s = '%s';", $params['p0'], $params['p1'], $params['p2']);

$result = $dbc->query($list);

$PIds = array();

while($row = $result->fetch_assoc()){
    $name = $row['PId'];
    array_push($PIds,$name);
}
//print_r($PIds);

$store = array();

// Retrieves the images from the given PIds
foreach($PIds as $PId=>$value){
    $list = sprintf("SELECT PName,PType,Pic FROM Pictures WHERE PId = '%s'",$value);
    $result = $dbc->query($list);
    $row = $result->fetch_assoc();
    
    array_push($store, sprintf("%s:%s:%s\n",$row['PName'],$row['PType'],base64_encode($row['Pic'])));
}

foreach($store as $img){
    printf("%s",$img);
}

$result -> free_result();
$dbc->close();
?>