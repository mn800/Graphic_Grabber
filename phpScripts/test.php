<?php
$mysqli = new mysqli("localhost","dipie111_test","T3st1@3","dipie111_GraphicGrabber");
$list = sprintf("SELECT %s FROM %s;", $_POST['param1'], $_POST['param2']);
$result = $mysqli->query($list);
$row = $result->fetch_assoc();
printf("%s %s\n",$row["PId"],$row["Tag"]);
$result -> free_result();
$mysqli->close();
?>