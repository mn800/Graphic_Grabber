<?php
require_once ('connectvars.php');

$dbc = new mysqli(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);
$params = $_POST;

// Image will be uploaded encoded in Base64
$image = base64_decode(params['p5']);

// Still have to look into how to insert the image into the table
$list = sprintf("INSERT INTO %s VALUES (%s,%s,%s,%s);", $params['p1'], $params['p2'],$param['p3'], $param['p4'], $image);

$result = $dbc->query($list);

$result -> free_result();
$dbc->close();
?>