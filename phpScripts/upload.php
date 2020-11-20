<?php
require_once ('connectvars.php');

$dbc = new mysqli(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);
$params = $_POST;

// Decodes the Base64 String of the image
$imgbin = base64_decode($params['p3']);
unset($params['p3']);

$PId = $params['p0'];
unset($params['p0']);

$null = null;
$sql = "INSERT INTO Pictures VALUES (?,?,?,?)";
$stmt = $dbc->prepare($sql);
$stmt->bind_param("sssb", $PId, $params['p1'], $params['p2'], $null);
// Sends the binary image
$stmt->send_long_data(3, $imgbin);
$stmt->execute();

unset($params['p1']);
unset($params['p2']);
// Need other queries to insert tags and artist into their tables
$sql = "INSERT INTO Artists VALUES (?,?)";
$stmt = $dbc->prepare($sql);
$stmt->bind_param("ss",$PId,$params['p4']);
$stmt->execute();
unset($params['p4']);
$sql = "INSERT INTO Tags VALUES (?,?)";
$stmt = $dbc->prepare($sql);
$stmt->bind_param("ss",$PId,$Tag);
foreach ($params as $Tags=>$value){
    $Tag = $value;
    $stmt->execute();
}
$stmt->close();
$dbc->close();
?>