<?php
require_once ('connectvars.php');

// Changed access to this to keep if the saem throughout all scripts
$dbc = new mysqli(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);
$params = $_POST;

$list = sprintf("SELECT %s FROM %s;", $params['p1'], $params['p2']);

$result = $dbc->query($list);

// Will eventaully make another query to send the Image back instead of just the tags
while($row = $result->fetch_assoc()){
    printf("%s %s\n",$row['PId'], $row['Tag']);
}

$result -> free_result();
$dbc->close();
?>