<?php
require_once ('connectvars.php');

$dbc = new mysqli(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);
$params = $_POST;

// Decodes the Base64 String of the image
$imgbin = base64_decode($params['p3']);

$null = null;
$sql = "INSERT INTO Pictures VALUES (?,?,?,?)";
$stmt = $dbc->prepare($sql);

// Need other queries to insert tags and artist into their tables

$stmt->bind_param("sssb", $params['p0'], $params['p1'], $params['p2'], $null);

// Sends the binary image
$stmt->send_long_data(3, $imgbin);

$stmt->execute();

$stmt->close();
$dbc->close();
?>